package android.app.job;

import android.os.Parcel;

/** What a running job's JobService gets: the job's id and extras, why it stops, and its queued work. */
public class JobParameters implements android.os.Parcelable {
  public static final int STOP_REASON_UNDEFINED = 0, STOP_REASON_CANCELLED_BY_APP = 1, STOP_REASON_PREEMPT = 2, STOP_REASON_TIMEOUT = 3, STOP_REASON_DEVICE_STATE = 4,
      STOP_REASON_CONSTRAINT_BATTERY_NOT_LOW = 5, STOP_REASON_CONSTRAINT_CHARGING = 6, STOP_REASON_CONSTRAINT_CONNECTIVITY = 7, STOP_REASON_CONSTRAINT_DEVICE_IDLE = 8,
      STOP_REASON_CONSTRAINT_STORAGE_NOT_LOW = 9, STOP_REASON_QUOTA = 10, STOP_REASON_BACKGROUND_RESTRICTION = 11, STOP_REASON_APP_STANDBY = 12, STOP_REASON_USER = 13,
      STOP_REASON_SYSTEM_PROCESSING = 14, STOP_REASON_ESTIMATED_APP_LAUNCH_TIME_CHANGED = 15;
  final JobInfo mJob;
  final String mNamespace;
  final boolean mOverrideDeadlineExpired;
  int mStopReason = STOP_REASON_UNDEFINED;
  /** the scheduler's record of this run (work queue, finishing) */
  final Object mRun;
  JobParameters(JobInfo job, String namespace, boolean overrideDeadlineExpired, Object run) { mJob = job; mNamespace = namespace; mOverrideDeadlineExpired = overrideDeadlineExpired; mRun = run; }
  public int getJobId() { return mJob.getId(); }
  public String getJobNamespace() { return mNamespace; }
  public int getStopReason() { return mStopReason; }
  public android.os.PersistableBundle getExtras() { return mJob.getExtras(); }
  public android.os.Bundle getTransientExtras() { return mJob.getTransientExtras(); }
  public android.content.ClipData getClipData() { return mJob.getClipData(); }
  public int getClipGrantFlags() { return mJob.getClipGrantFlags(); }
  public boolean isExpeditedJob() { return mJob.isExpedited(); }
  public boolean isUserInitiatedJob() { return mJob.isUserInitiated(); }
  public boolean isOverrideDeadlineExpired() { return mOverrideDeadlineExpired; }
  /** content-URI triggers do not fire here */
  public android.net.Uri[] getTriggeredContentUris() { return null; }
  public String[] getTriggeredContentAuthorities() { return null; }
  /** the network a network-constrained job may use: none is exposed as a Network object here */
  public android.net.Network getNetwork() { return null; }
  public JobWorkItem dequeueWork() { return a2w.JobSchedulerImpl.dequeueWork(this); }
  public void completeWork(JobWorkItem work) { a2w.JobSchedulerImpl.completeWork(this, work); }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel dest, int flags) { mJob.writeToParcel(dest, flags); dest.writeString(mNamespace); dest.writeInt(mOverrideDeadlineExpired ? 1 : 0); dest.writeInt(mStopReason); }
  public static final android.os.Parcelable.Creator<JobParameters> CREATOR = new android.os.Parcelable.Creator<JobParameters>() {
    public JobParameters createFromParcel(Parcel in) {
      JobParameters p = new JobParameters(JobInfo.CREATOR.createFromParcel(in), in.readString(), in.readInt() != 0, null);
      p.mStopReason = in.readInt();
      return p;
    }
    public JobParameters[] newArray(int size) { return new JobParameters[size]; }
  };
  /** web: the job (for the scheduler) */
  public JobInfo a2wJob() { return mJob; }
  public void a2wSetStopReason(int reason) { mStopReason = reason; }
  public Object a2wRun() { return mRun; }
  public static JobParameters a2wCreate(JobInfo job, String namespace, boolean deadlineExpired, Object run) { return new JobParameters(job, namespace, deadlineExpired, run); }
}
