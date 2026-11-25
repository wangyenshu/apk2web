package android.app.job;

/** The bridge between the scheduler and a service that runs jobs (JobService uses one; a custom Service may too). */
public abstract class JobServiceEngine {
  private final android.app.Service mService;
  private final android.os.Binder mBinder = new android.os.Binder();
  public JobServiceEngine(android.app.Service service) { mService = service; }
  public final android.os.IBinder getBinder() { return mBinder; }
  public abstract boolean onStartJob(JobParameters params);
  public abstract boolean onStopJob(JobParameters params);
  public void jobFinished(JobParameters params, boolean needsReschedule) { a2w.JobSchedulerImpl.jobFinished(params, needsReschedule); }
  public void onNetworkChanged(JobParameters params) {}
  public void updateTransferredNetworkBytes(JobParameters params, JobWorkItem item, long downloadBytes, long uploadBytes) {}
  public void updateEstimatedNetworkBytes(JobParameters params, JobWorkItem item, long downloadBytes, long uploadBytes) {}
  public void setNotification(JobParameters params, int notificationId, android.app.Notification notification, int jobEndNotificationPolicy) {
    android.app.NotificationManager nm = (android.app.NotificationManager) mService.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
    if (nm != null && notification != null) nm.notify(notificationId, notification);
  }
}
