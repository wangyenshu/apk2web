package android.app.job;

import android.content.Intent;
import android.os.IBinder;

/** A service that runs scheduled jobs: onStartJob on the main thread, jobFinished when asynchronous work ends. */
public abstract class JobService extends android.app.Service {
  public static final String PERMISSION_BIND = "android.permission.BIND_JOB_SERVICE";
  public static final int JOB_END_NOTIFICATION_POLICY_DETACH = 0, JOB_END_NOTIFICATION_POLICY_REMOVE = 1;
  private JobServiceEngine mEngine;
  public JobService() {}
  private JobServiceEngine engine() {
    if (mEngine == null) mEngine = new JobServiceEngine(this) {
      public boolean onStartJob(JobParameters params) { return JobService.this.onStartJob(params); }
      public boolean onStopJob(JobParameters params) { return JobService.this.onStopJob(params); }
      @Override public void onNetworkChanged(JobParameters params) { JobService.this.onNetworkChanged(params); }
    };
    return mEngine;
  }
  public final IBinder onBind(Intent intent) { return engine().getBinder(); }
  public final void jobFinished(JobParameters params, boolean wantsReschedule) { engine().jobFinished(params, wantsReschedule); }
  public abstract boolean onStartJob(JobParameters params);
  public abstract boolean onStopJob(JobParameters params);
  public void onNetworkChanged(JobParameters params) {}
  public final void updateEstimatedNetworkBytes(JobParameters params, long downloadBytes, long uploadBytes) {}
  public final void updateEstimatedNetworkBytes(JobParameters params, JobWorkItem jobWorkItem, long downloadBytes, long uploadBytes) {}
  public final void updateTransferredNetworkBytes(JobParameters params, long downloadBytes, long uploadBytes) {}
  public final void updateTransferredNetworkBytes(JobParameters params, JobWorkItem item, long downloadBytes, long uploadBytes) {}
  public final void setNotification(JobParameters params, int notificationId, android.app.Notification notification, int jobEndNotificationPolicy) {
    engine().setNotification(params, notificationId, notification, jobEndNotificationPolicy);
  }
}
