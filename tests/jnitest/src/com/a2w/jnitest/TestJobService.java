package com.a2w.jnitest;

public class TestJobService extends android.app.job.JobService {
  static final StringBuilder log = new StringBuilder();
  @Override public boolean onStartJob(android.app.job.JobParameters params) {
    log.append("start").append(params.getJobId()).append(params.getExtras().getString("k"));
    jobFinished(params, false);
    return true;
  }
  @Override public boolean onStopJob(android.app.job.JobParameters params) { log.append(" stop"); return false; }
  @Override public void onDestroy() { log.append(" destroyed"); super.onDestroy(); }
}
