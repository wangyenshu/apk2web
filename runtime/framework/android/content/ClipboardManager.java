package android.content;

/**
 * The clipboard: one primary clip for the app (kept for the page's lifetime); text put on it also goes to the
 * browser's clipboard when the browser allows it.
 */
public class ClipboardManager extends android.text.ClipboardManager {
  /** Called when the primary clip changes. */
  public interface OnPrimaryClipChangedListener { void onPrimaryClipChanged(); }
  private static ClipData sPrimary;
  private static final java.util.ArrayList<OnPrimaryClipChangedListener> sListeners = new java.util.ArrayList<>();
  /** for Context.getSystemService (not part of the API) */
  public ClipboardManager() {}
  public void setPrimaryClip(ClipData clip) {
    java.util.Objects.requireNonNull(clip);
    sPrimary = clip;
    if (clip.getItemCount() > 0) {
      CharSequence text = clip.getItemAt(0).getText();
      if (text != null) writeBrowserClipboard(text.toString());
    }
    for (OnPrimaryClipChangedListener l : new java.util.ArrayList<>(sListeners)) l.onPrimaryClipChanged();
  }
  public void clearPrimaryClip() {
    if (sPrimary == null) return;
    sPrimary = null;
    for (OnPrimaryClipChangedListener l : new java.util.ArrayList<>(sListeners)) l.onPrimaryClipChanged();
  }
  public ClipData getPrimaryClip() { return sPrimary; }
  public ClipDescription getPrimaryClipDescription() { return sPrimary == null ? null : sPrimary.getDescription(); }
  public boolean hasPrimaryClip() { return sPrimary != null; }
  public void addPrimaryClipChangedListener(OnPrimaryClipChangedListener what) { if (!sListeners.contains(what)) sListeners.add(what); }
  public void removePrimaryClipChangedListener(OnPrimaryClipChangedListener what) { sListeners.remove(what); }
  @Deprecated @Override public CharSequence getText() {
    ClipData clip = getPrimaryClip();
    return clip != null && clip.getItemCount() > 0 ? clip.getItemAt(0).coerceToText(null) : null;
  }
  @Deprecated @Override public void setText(CharSequence text) { setPrimaryClip(ClipData.newPlainText(null, text)); }
  @Deprecated @Override public boolean hasText() {
    ClipData clip = getPrimaryClip();
    return clip != null && clip.getItemCount() > 0 && clip.getItemAt(0).getText() != null;
  }
  @org.teavm.jso.JSBody(params = "t", script = "try { if (navigator.clipboard) navigator.clipboard.writeText(t).catch(function() {}); } catch (e) {}")
  private static native void writeBrowserClipboard(String t);
}
