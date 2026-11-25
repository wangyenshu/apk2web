package android.app.job;

import android.content.Intent;
import android.os.Parcel;
import android.os.PersistableBundle;

/** A unit of work queued for a job (JobScheduler.enqueue), handed to its JobService by JobParameters.dequeueWork. */
public final class JobWorkItem implements android.os.Parcelable {
  final Intent mIntent;
  final PersistableBundle mExtras;
  final long mNetworkDownloadBytes, mNetworkUploadBytes, mMinimumChunkBytes;
  int mDeliveryCount;
  public JobWorkItem(Intent intent) { this(intent, JobInfo.NETWORK_BYTES_UNKNOWN, JobInfo.NETWORK_BYTES_UNKNOWN); }
  public JobWorkItem(Intent intent, long downloadBytes, long uploadBytes) { this(intent, downloadBytes, uploadBytes, JobInfo.NETWORK_BYTES_UNKNOWN); }
  public JobWorkItem(Intent intent, long downloadBytes, long uploadBytes, long minimumChunkBytes) { this(intent, PersistableBundle.EMPTY, downloadBytes, uploadBytes, minimumChunkBytes); }
  JobWorkItem(Intent intent, PersistableBundle extras, long downloadBytes, long uploadBytes, long minimumChunkBytes) {
    if (minimumChunkBytes != JobInfo.NETWORK_BYTES_UNKNOWN && minimumChunkBytes <= 0) throw new IllegalArgumentException("Minimum chunk size must be positive");
    mIntent = intent; mExtras = extras == null ? PersistableBundle.EMPTY : extras; mNetworkDownloadBytes = downloadBytes; mNetworkUploadBytes = uploadBytes; mMinimumChunkBytes = minimumChunkBytes;
  }
  public PersistableBundle getExtras() { return mExtras; }
  public Intent getIntent() { return mIntent; }
  public long getEstimatedNetworkDownloadBytes() { return mNetworkDownloadBytes; }
  public long getEstimatedNetworkUploadBytes() { return mNetworkUploadBytes; }
  public long getMinimumNetworkChunkBytes() { return mMinimumChunkBytes; }
  public int getDeliveryCount() { return mDeliveryCount; }
  /** web: the scheduler handed the item out once more */
  public void a2wDelivered() { mDeliveryCount++; }
  @Override public String toString() { return "JobWorkItem{intent=" + mIntent + " dcount=" + mDeliveryCount + "}"; }
  public int describeContents() { return 0; }
  public void writeToParcel(Parcel out, int flags) {
    if (mIntent != null) { out.writeInt(1); mIntent.writeToParcel(out, 0); } else out.writeInt(0);
    out.writePersistableBundle(mExtras); out.writeLong(mNetworkDownloadBytes); out.writeLong(mNetworkUploadBytes); out.writeLong(mMinimumChunkBytes); out.writeInt(mDeliveryCount);
  }
  public static final android.os.Parcelable.Creator<JobWorkItem> CREATOR = new android.os.Parcelable.Creator<JobWorkItem>() {
    public JobWorkItem createFromParcel(Parcel in) {
      Intent i = in.readInt() != 0 ? Intent.CREATOR.createFromParcel(in) : null;
      JobWorkItem w = new JobWorkItem(i, in.readPersistableBundle(), in.readLong(), in.readLong(), in.readLong());
      w.mDeliveryCount = in.readInt();
      return w;
    }
    public JobWorkItem[] newArray(int size) { return new JobWorkItem[size]; }
  };
  public static final class Builder {
    private Intent mIntent;
    private PersistableBundle mExtras = PersistableBundle.EMPTY;
    private long mDown = JobInfo.NETWORK_BYTES_UNKNOWN, mUp = JobInfo.NETWORK_BYTES_UNKNOWN, mChunk = JobInfo.NETWORK_BYTES_UNKNOWN;
    public Builder() {}
    public Builder setExtras(PersistableBundle extras) { mExtras = java.util.Objects.requireNonNull(extras); return this; }
    public Builder setIntent(Intent intent) { mIntent = intent; return this; }
    public Builder setEstimatedNetworkBytes(long downloadBytes, long uploadBytes) { mDown = downloadBytes; mUp = uploadBytes; return this; }
    public Builder setMinimumNetworkChunkBytes(long chunkSizeBytes) { mChunk = chunkSizeBytes; return this; }
    public JobWorkItem build() { return new JobWorkItem(mIntent, mExtras, mDown, mUp, mChunk); }
  }
}
