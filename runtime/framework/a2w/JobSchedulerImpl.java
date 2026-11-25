package a2w;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * JobScheduler for the page: a job runs (its JobService's onStartJob on the main thread) once its minimum latency has
 * passed and its constraints hold, or at its deadline regardless; periodic jobs run again each interval; a job that
 * asks to be rescheduled (jobFinished, onStopJob) waits its back-off. Constraints: network needs the browser to be
 * online; charging, battery-not-low and storage-not-low hold; device-idle and content-URI triggers never do (only a
 * deadline runs such a job). Jobs time out after 10 minutes, as on Android. Jobs live while the page does.
 */
public final class JobSchedulerImpl extends JobScheduler {
  static final long TIMEOUT_MILLIS = 10 * 60 * 1000L;
  static final HashMap<String, JobSchedulerImpl> NAMESPACES = new HashMap<>();
  final String mNamespace;
  final HashMap<Integer, Job> mJobs = new HashMap<>();
  static Handler sHandler;
  @org.teavm.jso.JSBody(script = "return typeof navigator === 'undefined' || navigator.onLine !== false;")
  static native boolean online();
  static Handler h() { if (sHandler == null) sHandler = new Handler(Looper.getMainLooper()); return sHandler; }

  public JobSchedulerImpl() { this(null); }
  JobSchedulerImpl(String namespace) { mNamespace = namespace; NAMESPACES.put(namespace == null ? "" : namespace, this); }

  /** a scheduled job and its run */
  final class Job implements Runnable {
    final JobInfo info;
    final ArrayList<JobWorkItem> work = new ArrayList<>(), delivered = new ArrayList<>();
    long earliest, deadline;
    int failures;
    JobParameters params;
    android.app.Service service;
    boolean stopping, enqueued;
    final Runnable timeout = () -> stop(JobParameters.STOP_REASON_TIMEOUT);
    Job(JobInfo info) { this.info = info; }
    void plan(long now, boolean afterRun) {
      if (info.isPeriodic()) {
        long interval = info.getIntervalMillis(), flex = info.getFlexMillis();
        earliest = afterRun ? now + interval - flex : now;
        deadline = afterRun ? now + interval : now + interval;
      } else {
        earliest = now + info.getMinLatencyMillis();
        deadline = info.getMaxExecutionDelayMillis() > 0 ? now + info.getMaxExecutionDelayMillis() : Long.MAX_VALUE;
      }
      arm();
    }
    void arm() {
      h().removeCallbacks(this);
      if (params != null) return;
      long now = System.currentTimeMillis();
      long when = constraintsMet() ? earliest : deadline;
      if (when == Long.MAX_VALUE) { h().postDelayed(this, 60000); return; }   // look again: the network may come back
      h().postDelayed(this, Math.max(0, when - now));
    }
    boolean constraintsMet() {
      if (info.isRequireDeviceIdle() || info.getTriggerContentUris() != null) return false;
      if (info.getRequiredNetwork() != null || info.getNetworkType() != JobInfo.NETWORK_TYPE_NONE) return online();
      return true;
    }
    public void run() {
      if (params != null || mJobs.get(info.getId()) != this) return;
      long now = System.currentTimeMillis();
      boolean met = constraintsMet();
      if (!(met && now >= earliest) && now < deadline) { arm(); return; }
      start(!info.isPeriodic() && info.getMaxExecutionDelayMillis() > 0 && now >= deadline);
    }
    void start(boolean deadlineExpired) {
      String cls = info.getService().getClassName();
      android.app.Service s = ActivityThread.jobService(cls);
      if (s == null) { android.util.Log.w("JobScheduler", "Unable to start job " + info + ": no service " + cls); mJobs.remove(info.getId()); return; }
      service = s;
      params = JobParameters.a2wCreate(info, mNamespace, deadlineExpired, this);
      stopping = false;
      h().postDelayed(timeout, TIMEOUT_MILLIS);
      boolean ongoing;
      try { ongoing = callStart(s, params); } catch (Throwable t) { Host.uncaught(t); ongoing = false; }
      if (!ongoing && params != null) finished(false);
    }
    void stop(int reason) {
      if (params == null || stopping) return;
      stopping = true;
      params.a2wSetStopReason(reason);
      boolean reschedule;
      try { reschedule = callStop(service, params); } catch (Throwable t) { Host.uncaught(t); reschedule = false; }
      boolean cancelled = reason == JobParameters.STOP_REASON_CANCELLED_BY_APP;
      end();
      if (!cancelled) after(reschedule);
    }
    void finished(boolean reschedule) {
      if (params == null) return;
      end();
      after(reschedule);
    }
    private void end() {
      h().removeCallbacks(timeout);
      params = null;
      ActivityThread.releaseJobService(info.getService().getClassName());
      service = null;
      // work handed out but not completed is delivered again
      for (int i = delivered.size() - 1; i >= 0; i--) work.add(0, delivered.get(i));
      delivered.clear();
    }
    private void after(boolean reschedule) {
      if (mJobs.get(info.getId()) != this) return;
      long now = System.currentTimeMillis();
      if (reschedule) {
        failures++;
        long backoff = info.getInitialBackoffMillis();
        long delay = info.getBackoffPolicy() == JobInfo.BACKOFF_POLICY_LINEAR ? backoff * failures : backoff << Math.min(failures - 1, 20);
        delay = Math.min(delay, JobInfo.MAX_BACKOFF_DELAY_MILLIS);
        earliest = now + delay;
        deadline = info.isPeriodic() ? earliest + info.getFlexMillis() : Long.MAX_VALUE;
        arm();
      } else if (info.isPeriodic()) { failures = 0; plan(now, true); }
      else if (!work.isEmpty()) { failures = 0; plan(now, false); }
      else mJobs.remove(info.getId());
    }
  }
  private static boolean callStart(android.app.Service s, JobParameters p) {
    if (s instanceof android.app.job.JobService) return ((android.app.job.JobService) s).onStartJob(p);
    android.util.Log.w("JobScheduler", s.getClass().getName() + " is not a JobService");
    return false;
  }
  private static boolean callStop(android.app.Service s, JobParameters p) {
    return s instanceof android.app.job.JobService && ((android.app.job.JobService) s).onStopJob(p);
  }

  // ---------------------------------------------------------------- the JobService side (JobParameters, JobService.jobFinished)
  public static void jobFinished(JobParameters params, boolean needsReschedule) {
    Object run = params.a2wRun();
    if (run instanceof Job) { Job j = (Job) run; h().post(() -> { if (j.params == params) j.finished(needsReschedule); }); }
  }
  public static JobWorkItem dequeueWork(JobParameters params) {
    Object run = params.a2wRun();
    if (!(run instanceof Job)) return null;
    Job j = (Job) run;
    if (j.work.isEmpty()) return null;
    JobWorkItem w = j.work.remove(0);
    j.delivered.add(w);
    w.a2wDelivered();
    return w;
  }
  public static void completeWork(JobParameters params, JobWorkItem work) {
    Object run = params.a2wRun();
    if (!(run instanceof Job)) return;
    Job j = (Job) run;
    if (!j.delivered.remove(work)) throw new IllegalArgumentException("Given work is not active: " + work);
    // all work done and none left: the job ends (as the platform stops it)
    if (j.delivered.isEmpty() && j.work.isEmpty() && j.info.getId() >= 0 && enqueued(j)) h().post(() -> { if (j.params == params && j.work.isEmpty() && j.delivered.isEmpty()) j.finished(false); });
  }
  private static boolean enqueued(Job j) { return j.enqueued; }

  // ---------------------------------------------------------------- JobScheduler
  private void check(JobInfo job) {
    ComponentName c = job.getService();
    android.content.Intent i = new android.content.Intent().setComponent(c);
    if (Intents.resolveOne(Intents.SERVICES, i) == null) throw new IllegalArgumentException("No such service " + c);
  }
  @Override public int schedule(JobInfo job) {
    check(job);
    Job old = mJobs.get(job.getId());
    if (old != null) { mJobs.remove(job.getId()); h().removeCallbacks(old); if (old.params != null) old.stop(JobParameters.STOP_REASON_CANCELLED_BY_APP); }
    Job j = new Job(job);
    mJobs.put(job.getId(), j);
    j.plan(System.currentTimeMillis(), false);
    return RESULT_SUCCESS;
  }
  @Override public int enqueue(JobInfo job, JobWorkItem work) {
    check(job);
    if (job.isPersisted()) throw new IllegalArgumentException("Can't enqueue work for persisted jobs");
    Job j = mJobs.get(job.getId());
    if (j == null || !j.info.equals(job)) {
      ArrayList<JobWorkItem> keep = j != null ? new ArrayList<>(j.work) : new ArrayList<>();
      if (j != null) { h().removeCallbacks(j); if (j.params != null) j.stop(JobParameters.STOP_REASON_CANCELLED_BY_APP); }
      j = new Job(job);
      j.work.addAll(keep);
      mJobs.put(job.getId(), j);
      j.enqueued = true;
      j.work.add(work);
      j.plan(System.currentTimeMillis(), false);
    } else {
      j.enqueued = true;
      j.work.add(work);
      if (j.params == null) j.arm();
    }
    return RESULT_SUCCESS;
  }
  @Override public void cancel(int jobId) {
    Job j = mJobs.remove(jobId);
    if (j == null) return;
    h().removeCallbacks(j);
    if (j.params != null) j.stop(JobParameters.STOP_REASON_CANCELLED_BY_APP);
  }
  @Override public void cancelAll() { for (Integer id : new ArrayList<>(mJobs.keySet())) cancel(id); }
  @Override public void cancelInAllNamespaces() { for (JobSchedulerImpl s : new ArrayList<>(NAMESPACES.values())) s.cancelAll(); }
  @Override public List<JobInfo> getAllPendingJobs() { ArrayList<JobInfo> l = new ArrayList<>(); for (Job j : mJobs.values()) l.add(j.info); return l; }
  @Override public JobInfo getPendingJob(int jobId) { Job j = mJobs.get(jobId); return j == null ? null : j.info; }
  @Override public java.util.Map<String, List<JobInfo>> getPendingJobsInAllNamespaces() {
    HashMap<String, List<JobInfo>> m = new HashMap<>();
    for (JobSchedulerImpl s : NAMESPACES.values()) if (!s.mJobs.isEmpty()) m.put(s.mNamespace, s.getAllPendingJobs());
    return m;
  }
  @Override public int getPendingJobReason(int jobId) {
    Job j = mJobs.get(jobId);
    if (j == null) return PENDING_JOB_REASON_INVALID_JOB_ID;
    if (j.params != null) return PENDING_JOB_REASON_EXECUTING;
    if (j.info.isRequireDeviceIdle()) return PENDING_JOB_REASON_CONSTRAINT_DEVICE_IDLE;
    if (j.info.getTriggerContentUris() != null) return PENDING_JOB_REASON_CONSTRAINT_CONTENT_TRIGGER;
    if (!j.constraintsMet()) return PENDING_JOB_REASON_CONSTRAINT_CONNECTIVITY;
    if (System.currentTimeMillis() < j.earliest) return PENDING_JOB_REASON_CONSTRAINT_MINIMUM_LATENCY;
    return PENDING_JOB_REASON_UNDEFINED;
  }
  @Override public boolean canRunUserInitiatedJobs() { return true; }
  @Override public JobScheduler forNamespace(String namespace) {
    if (namespace == null) throw new NullPointerException("namespace cannot be null");
    if (namespace.isEmpty()) throw new IllegalArgumentException("namespace cannot be empty");
    JobSchedulerImpl s = NAMESPACES.get(namespace);
    return s != null ? s : new JobSchedulerImpl(namespace);
  }
  @Override public String getNamespace() { return mNamespace; }
}
