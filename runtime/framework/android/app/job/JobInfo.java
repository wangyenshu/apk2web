package android.app.job;

import android.content.ClipData;
import android.content.ComponentName;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.os.Parcel;
import android.os.PersistableBundle;

/** A job for JobScheduler: the JobService to run, its constraints, timing, retry policy and extras (as the platform's, with its checks). */
public class JobInfo implements android.os.Parcelable {
  public static final int NETWORK_TYPE_NONE = 0, NETWORK_TYPE_ANY = 1, NETWORK_TYPE_UNMETERED = 2, NETWORK_TYPE_NOT_ROAMING = 3, NETWORK_TYPE_CELLULAR = 4, NETWORK_TYPE_METERED = 4;
  public static final int NETWORK_BYTES_UNKNOWN = -1;
  public static final long DEFAULT_INITIAL_BACKOFF_MILLIS = 30000L, MAX_BACKOFF_DELAY_MILLIS = 5 * 60 * 60 * 1000L;
  public static final int BACKOFF_POLICY_LINEAR = 0, BACKOFF_POLICY_EXPONENTIAL = 1;
  public static final int PRIORITY_MIN = 100, PRIORITY_LOW = 200, PRIORITY_DEFAULT = 300, PRIORITY_HIGH = 400, PRIORITY_MAX = 500;
  private static final long MIN_PERIOD_MILLIS = 15 * 60 * 1000L, MIN_FLEX_MILLIS = 5 * 60 * 1000L, MIN_BACKOFF_MILLIS = 10 * 1000L;
  public static final long getMinPeriodMillis() { return MIN_PERIOD_MILLIS; }
  public static final long getMinFlexMillis() { return MIN_FLEX_MILLIS; }

  final int jobId;
  final ComponentName service;
  final PersistableBundle extras;
  final Bundle transientExtras;
  final ClipData clipData;
  final int clipGrantFlags, priority, networkType, backoffPolicy;
  final boolean requireCharging, requireBatteryNotLow, requireDeviceIdle, requireStorageNotLow, isPeriodic, isPersisted, expedited, userInitiated, importantWhileForeground, prefetch;
  final TriggerContentUri[] triggerContentUris;
  final long triggerContentUpdateDelay, triggerContentMaxDelay, networkDownloadBytes, networkUploadBytes, minimumNetworkChunkBytes;
  final long minLatencyMillis, maxExecutionDelayMillis, intervalMillis, flexMillis, initialBackoffMillis;
  final NetworkRequest networkRequest;
  final java.util.Set<String> debugTags;
  final String traceTag;

  private JobInfo(Builder b) {
    jobId = b.mJobId; service = b.mJobService; extras = b.mExtras.deepCopy(); transientExtras = b.mTransientExtras.deepCopy(); clipData = b.mClipData;
    clipGrantFlags = b.mClipGrantFlags; priority = b.mPriority; networkType = b.mNetworkType; backoffPolicy = b.mBackoffPolicy;
    requireCharging = b.mRequiresCharging; requireBatteryNotLow = b.mRequiresBatteryNotLow; requireDeviceIdle = b.mRequiresDeviceIdle;
    requireStorageNotLow = b.mRequiresStorageNotLow; isPeriodic = b.mIsPeriodic; isPersisted = b.mIsPersisted; expedited = b.mExpedited;
    userInitiated = b.mUserInitiated; importantWhileForeground = b.mImportantWhileForeground; prefetch = b.mPrefetch;
    triggerContentUris = b.mTriggerContentUris.isEmpty() ? null : b.mTriggerContentUris.toArray(new TriggerContentUri[0]);
    triggerContentUpdateDelay = b.mTriggerContentUpdateDelay; triggerContentMaxDelay = b.mTriggerContentMaxDelay;
    networkDownloadBytes = b.mNetworkDownloadBytes; networkUploadBytes = b.mNetworkUploadBytes; minimumNetworkChunkBytes = b.mMinimumNetworkChunkBytes;
    minLatencyMillis = b.mMinLatencyMillis; maxExecutionDelayMillis = b.mMaxExecutionDelayMillis; intervalMillis = b.mIntervalMillis; flexMillis = b.mFlexMillis;
    initialBackoffMillis = b.mInitialBackoffMillis; networkRequest = b.mNetworkRequest; debugTags = new java.util.TreeSet<>(b.mDebugTags); traceTag = b.mTraceTag;
  }
  public int getId() { return jobId; }
  public PersistableBundle getExtras() { return extras; }
  public Bundle getTransientExtras() { return transientExtras; }
  public ClipData getClipData() { return clipData; }
  public int getClipGrantFlags() { return clipGrantFlags; }
  public ComponentName getService() { return service; }
  public int getPriority() { return priority; }
  public boolean isRequireCharging() { return requireCharging; }
  public boolean isRequireBatteryNotLow() { return requireBatteryNotLow; }
  public boolean isRequireDeviceIdle() { return requireDeviceIdle; }
  public boolean isRequireStorageNotLow() { return requireStorageNotLow; }
  public TriggerContentUri[] getTriggerContentUris() { return triggerContentUris; }
  public long getTriggerContentUpdateDelay() { return triggerContentUpdateDelay; }
  public long getTriggerContentMaxDelay() { return triggerContentMaxDelay; }
  @Deprecated public int getNetworkType() { return networkType; }
  public NetworkRequest getRequiredNetwork() { return networkRequest; }
  public long getEstimatedNetworkDownloadBytes() { return networkDownloadBytes; }
  public long getEstimatedNetworkUploadBytes() { return networkUploadBytes; }
  public long getMinimumNetworkChunkBytes() { return minimumNetworkChunkBytes; }
  public long getMinLatencyMillis() { return minLatencyMillis; }
  public long getMaxExecutionDelayMillis() { return maxExecutionDelayMillis; }
  public boolean isPeriodic() { return isPeriodic; }
  public boolean isPersisted() { return isPersisted; }
  public long getIntervalMillis() { return intervalMillis; }
  public long getFlexMillis() { return flexMillis; }
  public long getInitialBackoffMillis() { return initialBackoffMillis; }
  public int getBackoffPolicy() { return backoffPolicy; }
  public java.util.Set<String> getDebugTags() { return java.util.Collections.unmodifiableSet(debugTags); }
  public String getTraceTag() { return traceTag; }
  public boolean isExpedited() { return expedited; }
  public boolean isUserInitiated() { return userInitiated; }
  @Deprecated public boolean isImportantWhileForeground() { return importantWhileForeground; }
  public boolean isPrefetch() { return prefetch; }
  @Override public boolean equals(Object o) {
    if (!(o instanceof JobInfo)) return false;
    JobInfo j = (JobInfo) o;
    return jobId == j.jobId && java.util.Objects.equals(service, j.service) && priority == j.priority && networkType == j.networkType && backoffPolicy == j.backoffPolicy
        && requireCharging == j.requireCharging && requireBatteryNotLow == j.requireBatteryNotLow && requireDeviceIdle == j.requireDeviceIdle
        && requireStorageNotLow == j.requireStorageNotLow && isPeriodic == j.isPeriodic && isPersisted == j.isPersisted && expedited == j.expedited
        && userInitiated == j.userInitiated && prefetch == j.prefetch && minLatencyMillis == j.minLatencyMillis && maxExecutionDelayMillis == j.maxExecutionDelayMillis
        && intervalMillis == j.intervalMillis && flexMillis == j.flexMillis && initialBackoffMillis == j.initialBackoffMillis
        && java.util.Arrays.equals(triggerContentUris, j.triggerContentUris) && java.util.Objects.equals(debugTags, j.debugTags) && java.util.Objects.equals(traceTag, j.traceTag);
  }
  @Override public int hashCode() { return java.util.Objects.hash(jobId, service, priority, networkType, isPeriodic, intervalMillis, minLatencyMillis, maxExecutionDelayMillis); }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel out, int flags) {
    out.writeInt(jobId); out.writeString(service.flattenToString()); out.writePersistableBundle(extras); out.writeBundle(transientExtras);
    out.writeInt(priority); out.writeInt(networkType); out.writeInt(backoffPolicy); out.writeInt(clipGrantFlags);
    out.writeBooleanArray(new boolean[] {requireCharging, requireBatteryNotLow, requireDeviceIdle, requireStorageNotLow, isPeriodic, isPersisted, expedited, userInitiated, importantWhileForeground, prefetch});
    out.writeLongArray(new long[] {triggerContentUpdateDelay, triggerContentMaxDelay, networkDownloadBytes, networkUploadBytes, minimumNetworkChunkBytes,
        minLatencyMillis, maxExecutionDelayMillis, intervalMillis, flexMillis, initialBackoffMillis});
    out.writeTypedArray(triggerContentUris, flags);
    out.writeStringList(new java.util.ArrayList<>(debugTags)); out.writeString(traceTag);
  }
  public static final android.os.Parcelable.Creator<JobInfo> CREATOR = new android.os.Parcelable.Creator<JobInfo>() {
    public JobInfo createFromParcel(Parcel in) {
      Builder b = new Builder(in.readInt(), ComponentName.unflattenFromString(in.readString()));
      PersistableBundle e = in.readPersistableBundle(); if (e != null) b.mExtras = e;
      Bundle t = in.readBundle(); if (t != null) b.mTransientExtras = t;
      b.mPriority = in.readInt(); b.mNetworkType = in.readInt(); b.mBackoffPolicy = in.readInt(); b.mClipGrantFlags = in.readInt();
      boolean[] f = new boolean[10]; in.readBooleanArray(f);
      b.mRequiresCharging = f[0]; b.mRequiresBatteryNotLow = f[1]; b.mRequiresDeviceIdle = f[2]; b.mRequiresStorageNotLow = f[3]; b.mIsPeriodic = f[4];
      b.mIsPersisted = f[5]; b.mExpedited = f[6]; b.mUserInitiated = f[7]; b.mImportantWhileForeground = f[8]; b.mPrefetch = f[9];
      long[] l = new long[10]; in.readLongArray(l);
      b.mTriggerContentUpdateDelay = l[0]; b.mTriggerContentMaxDelay = l[1]; b.mNetworkDownloadBytes = l[2]; b.mNetworkUploadBytes = l[3]; b.mMinimumNetworkChunkBytes = l[4];
      b.mMinLatencyMillis = l[5]; b.mMaxExecutionDelayMillis = l[6]; b.mIntervalMillis = l[7]; b.mFlexMillis = l[8]; b.mInitialBackoffMillis = l[9];
      b.mHasEarlyConstraint = b.mMinLatencyMillis > 0; b.mHasLateConstraint = b.mMaxExecutionDelayMillis > 0 || b.mIsPeriodic;
      TriggerContentUri[] uris = in.createTypedArray(TriggerContentUri.CREATOR);
      if (uris != null) for (TriggerContentUri u : uris) b.mTriggerContentUris.add(u);
      java.util.ArrayList<String> tags = new java.util.ArrayList<>(); in.readStringList(tags); b.mDebugTags.addAll(tags);
      b.mTraceTag = in.readString();
      return new JobInfo(b);
    }
    public JobInfo[] newArray(int size) { return new JobInfo[size]; }
  };
  @Override public String toString() { return "(job:" + jobId + "/" + service.flattenToShortString() + ")"; }

  /** A content URI whose changes trigger a job. */
  public static final class TriggerContentUri implements android.os.Parcelable {
    public static final int FLAG_NOTIFY_FOR_DESCENDANTS = 1 << 0;
    private final android.net.Uri mUri;
    private final int mFlags;
    public TriggerContentUri(android.net.Uri uri, int flags) { mUri = java.util.Objects.requireNonNull(uri); mFlags = flags; }
    public android.net.Uri getUri() { return mUri; }
    public int getFlags() { return mFlags; }
    @Override public boolean equals(Object o) { return o instanceof TriggerContentUri && mUri.equals(((TriggerContentUri) o).mUri) && mFlags == ((TriggerContentUri) o).mFlags; }
    @Override public int hashCode() { return mUri.hashCode() ^ mFlags; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel out, int flags) { out.writeString(mUri.toString()); out.writeInt(mFlags); }
    public static final android.os.Parcelable.Creator<TriggerContentUri> CREATOR = new android.os.Parcelable.Creator<TriggerContentUri>() {
      public TriggerContentUri createFromParcel(Parcel in) { return new TriggerContentUri(android.net.Uri.parse(in.readString()), in.readInt()); }
      public TriggerContentUri[] newArray(int size) { return new TriggerContentUri[size]; }
    };
  }

  public static final class Builder {
    final int mJobId;
    final ComponentName mJobService;
    PersistableBundle mExtras = PersistableBundle.EMPTY;
    Bundle mTransientExtras = Bundle.EMPTY;
    ClipData mClipData;
    int mClipGrantFlags, mPriority = PRIORITY_DEFAULT, mNetworkType, mBackoffPolicy = BACKOFF_POLICY_EXPONENTIAL;
    NetworkRequest mNetworkRequest;
    long mNetworkDownloadBytes = NETWORK_BYTES_UNKNOWN, mNetworkUploadBytes = NETWORK_BYTES_UNKNOWN, mMinimumNetworkChunkBytes = NETWORK_BYTES_UNKNOWN;
    boolean mRequiresCharging, mRequiresBatteryNotLow, mRequiresDeviceIdle, mRequiresStorageNotLow, mIsPersisted, mIsPeriodic, mExpedited, mUserInitiated, mImportantWhileForeground, mPrefetch;
    boolean mHasEarlyConstraint, mHasLateConstraint, mBackoffPolicySet;
    final java.util.ArrayList<TriggerContentUri> mTriggerContentUris = new java.util.ArrayList<>();
    long mTriggerContentUpdateDelay = -1, mTriggerContentMaxDelay = -1, mMinLatencyMillis, mMaxExecutionDelayMillis, mIntervalMillis, mFlexMillis;
    long mInitialBackoffMillis = DEFAULT_INITIAL_BACKOFF_MILLIS;
    final java.util.HashSet<String> mDebugTags = new java.util.HashSet<>();
    String mTraceTag;
    public Builder(int jobId, ComponentName jobService) { mJobService = jobService; mJobId = jobId; }
    public Builder addDebugTag(String tag) { mDebugTags.add(validateTag(tag)); return this; }
    public Builder removeDebugTag(String tag) { mDebugTags.remove(tag); return this; }
    private static String validateTag(String tag) {
      java.util.Objects.requireNonNull(tag);
      String t = tag.trim();
      if (t.isEmpty() || t.length() > 127) throw new IllegalArgumentException("Tags must be non-empty and at most 127 characters");
      return t;
    }
    public Builder setPriority(int priority) { mPriority = priority; return this; }
    public Builder setExtras(PersistableBundle extras) { mExtras = extras; return this; }
    public Builder setTransientExtras(Bundle extras) { mTransientExtras = extras; return this; }
    public Builder setClipData(ClipData clip, int grantFlags) { mClipData = clip; mClipGrantFlags = grantFlags; return this; }
    public Builder setRequiredNetworkType(int networkType) { mNetworkType = networkType; mNetworkRequest = null; return this; }
    public Builder setRequiredNetwork(NetworkRequest networkRequest) { mNetworkRequest = networkRequest; mNetworkType = networkRequest == null ? NETWORK_TYPE_NONE : NETWORK_TYPE_ANY; return this; }
    public Builder setEstimatedNetworkBytes(long downloadBytes, long uploadBytes) { mNetworkDownloadBytes = downloadBytes; mNetworkUploadBytes = uploadBytes; return this; }
    public Builder setMinimumNetworkChunkBytes(long chunkSizeBytes) {
      if (chunkSizeBytes != NETWORK_BYTES_UNKNOWN && chunkSizeBytes <= 0) throw new IllegalArgumentException("Minimum chunk size must be positive");
      mMinimumNetworkChunkBytes = chunkSizeBytes; return this;
    }
    public Builder setRequiresCharging(boolean requiresCharging) { mRequiresCharging = requiresCharging; return this; }
    public Builder setRequiresBatteryNotLow(boolean batteryNotLow) { mRequiresBatteryNotLow = batteryNotLow; return this; }
    public Builder setRequiresDeviceIdle(boolean requiresDeviceIdle) { mRequiresDeviceIdle = requiresDeviceIdle; return this; }
    public Builder setRequiresStorageNotLow(boolean storageNotLow) { mRequiresStorageNotLow = storageNotLow; return this; }
    public Builder addTriggerContentUri(TriggerContentUri uri) { mTriggerContentUris.add(uri); return this; }
    public Builder setTriggerContentUpdateDelay(long durationMs) { mTriggerContentUpdateDelay = durationMs; return this; }
    public Builder setTriggerContentMaxDelay(long durationMs) { mTriggerContentMaxDelay = durationMs; return this; }
    public Builder setPeriodic(long intervalMillis) { return setPeriodic(intervalMillis, intervalMillis); }
    public Builder setPeriodic(long intervalMillis, long flexMillis) {
      long minPeriod = getMinPeriodMillis();
      if (intervalMillis < minPeriod) { android.util.Log.w("JobInfo", "Requested interval " + intervalMillis + "ms for job " + mJobId + " is too small; raising to " + minPeriod + "ms"); intervalMillis = minPeriod; }
      long minFlex = Math.max(intervalMillis * 5 / 100, getMinFlexMillis());
      if (flexMillis < minFlex) flexMillis = minFlex;
      mIsPeriodic = true; mIntervalMillis = intervalMillis; mFlexMillis = flexMillis; mHasEarlyConstraint = mHasLateConstraint = true;
      return this;
    }
    public Builder setMinimumLatency(long minLatencyMillis) { mMinLatencyMillis = minLatencyMillis; mHasEarlyConstraint = true; return this; }
    public Builder setOverrideDeadline(long maxExecutionDelayMillis) { mMaxExecutionDelayMillis = maxExecutionDelayMillis; mHasLateConstraint = true; return this; }
    public Builder setBackoffCriteria(long initialBackoffMillis, int backoffPolicy) {
      if (initialBackoffMillis < MIN_BACKOFF_MILLIS) initialBackoffMillis = MIN_BACKOFF_MILLIS;
      mBackoffPolicySet = true; mInitialBackoffMillis = initialBackoffMillis; mBackoffPolicy = backoffPolicy; return this;
    }
    public Builder setExpedited(boolean expedited) { mExpedited = expedited; return this; }
    public Builder setUserInitiated(boolean userInitiated) { mUserInitiated = userInitiated; return this; }
    @Deprecated public Builder setImportantWhileForeground(boolean importantWhileForeground) { mImportantWhileForeground = importantWhileForeground; return this; }
    public Builder setPrefetch(boolean prefetch) { mPrefetch = prefetch; return this; }
    public Builder setPersisted(boolean isPersisted) { mIsPersisted = isPersisted; return this; }
    public Builder setTraceTag(String traceTag) { mTraceTag = traceTag; return this; }
    /** the platform's checks: a job needs a constraint, periodic jobs have no latency or deadline, and so on */
    public JobInfo build() {
      if (mBackoffPolicySet && mRequiresDeviceIdle) throw new IllegalArgumentException("An idle mode job will not respect any back-off policy, so calling setBackoffCriteria with setRequiresDeviceIdle is an error.");
      if (mIsPeriodic) {
        if (mMaxExecutionDelayMillis != 0L) throw new IllegalArgumentException("Can't call setOverrideDeadline() on a periodic job.");
        if (mMinLatencyMillis != 0L) throw new IllegalArgumentException("Can't call setMinimumLatency() on a periodic job");
        if (!mTriggerContentUris.isEmpty()) throw new IllegalArgumentException("Can't call addTriggerContentUri() on a periodic job");
      }
      if (mIsPersisted) {
        if (!mTriggerContentUris.isEmpty()) throw new IllegalArgumentException("Can't call addTriggerContentUri() on a persisted job");
        if (!mTransientExtras.isEmpty()) throw new IllegalArgumentException("Can't call setTransientExtras() on a persisted job");
        if (mClipData != null) throw new IllegalArgumentException("Can't call setClipData() on a persisted job");
      }
      if (mExpedited) {
        if (mHasEarlyConstraint) throw new IllegalArgumentException("An expedited job cannot have a time delay");
        if (mHasLateConstraint) throw new IllegalArgumentException("An expedited job cannot have a deadline");
        if (mIsPeriodic) throw new IllegalArgumentException("An expedited job cannot be periodic");
        if (mRequiresCharging || mRequiresDeviceIdle || mRequiresBatteryNotLow || mRequiresStorageNotLow || !mTriggerContentUris.isEmpty())
          throw new IllegalArgumentException("An expedited job can only have network and storage-not-low constraints");
      }
      if (!mHasEarlyConstraint && !mHasLateConstraint && !mRequiresCharging && !mRequiresDeviceIdle && !mRequiresBatteryNotLow && !mRequiresStorageNotLow
          && mNetworkRequest == null && mNetworkType == NETWORK_TYPE_NONE && mTriggerContentUris.isEmpty() && !mExpedited && !mUserInitiated)
        throw new IllegalArgumentException("You're trying to build a job with no constraints, this is not allowed.");
      if (mJobService == null) throw new IllegalArgumentException("You're trying to build a job with no service");
      return new JobInfo(this);
    }
  }
}
