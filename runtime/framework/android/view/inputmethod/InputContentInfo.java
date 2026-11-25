package android.view.inputmethod;

/** Rich content (an image, say) an input method commits: its URI, description and optional link. */
public final class InputContentInfo implements android.os.Parcelable {
  private final android.net.Uri mContentUri, mLinkUri;
  private final android.content.ClipDescription mDescription;
  public InputContentInfo(android.net.Uri contentUri, android.content.ClipDescription description) { this(contentUri, description, null); }
  public InputContentInfo(android.net.Uri contentUri, android.content.ClipDescription description, android.net.Uri linkUri) {
    java.util.Objects.requireNonNull(contentUri, "contentUri"); java.util.Objects.requireNonNull(description, "description");
    if (!"content".equals(contentUri.getScheme())) throw new InvalidParameterExceptionWrapper("contentUri must have content scheme");
    if (linkUri != null && !"http".equalsIgnoreCase(linkUri.getScheme()) && !"https".equalsIgnoreCase(linkUri.getScheme()))
      throw new InvalidParameterExceptionWrapper("linkUri must have either http or https scheme");
    mContentUri = contentUri; mDescription = description; mLinkUri = linkUri;
  }
  static final class InvalidParameterExceptionWrapper extends java.security.InvalidParameterException { InvalidParameterExceptionWrapper(String m) { super(m); } }
  public android.net.Uri getContentUri() { return mContentUri; }
  public android.content.ClipDescription getDescription() { return mDescription; }
  public android.net.Uri getLinkUri() { return mLinkUri; }
  /** the content is in this app's own storage: no grant is needed */
  public void requestPermission() {}
  public void releasePermission() {}
  public int describeContents() { return 0; }
  public void writeToParcel(android.os.Parcel dest, int flags) {
    dest.writeString(mContentUri.toString()); mDescription.writeToParcel(dest, flags); dest.writeString(mLinkUri == null ? null : mLinkUri.toString());
  }
  public static final android.os.Parcelable.Creator<InputContentInfo> CREATOR = new Creator();
  static final class Creator implements android.os.Parcelable.Creator<InputContentInfo> {
    public InputContentInfo createFromParcel(android.os.Parcel in) {
      android.net.Uri c = android.net.Uri.parse(in.readString());
      android.content.ClipDescription d = android.content.ClipDescription.CREATOR.createFromParcel(in);
      String l = in.readString();
      return new InputContentInfo(c, d, l == null ? null : android.net.Uri.parse(l));
    }
    public InputContentInfo[] newArray(int size) { return new InputContentInfo[size]; }
  }
}
