package a2w;


/** Entry points the page calls (teavm.exports.*); everything runs on the main looper. */
public final class Events {
  private Events() {}
  static volatile boolean framePosted;
  /** input and frames bypass the traversal barrier, as they do on Android */
  static void postAsync(Runnable r) {
    android.os.Message m = android.os.Message.obtain(ViewRoot.mainHandler(), r);
    m.setAsynchronous(true);
    ViewRoot.mainHandler().sendMessage(m);
  }

  public static void onFrame() {
    if (framePosted) return;
    framePosted = true;
    postAsync(() -> { framePosted = false; Windows.frame(); });
  }
  public static void onTouch(int action, int id, float x, float y) {
    postAsync(() -> Windows.touch(action, id, x, y, 1, android.view.MotionEvent.TOOL_TYPE_FINGER, 0));
  }
  public static void onKey(int code, boolean down, int repeat, int meta) {
    postAsync(() -> Windows.key(code, down, repeat, meta));
  }
  public static void onPointer(int action, int id, float x, float y, float pressure, int tool, int buttons) {
    postAsync(() -> Windows.touch(action, id, x, y, pressure, tool, buttons));
  }
  public static void onPointerSample(int id, float x, float y, float ageMs) { postAsync(() -> Windows.touchSample(id, x, y, ageMs)); }
  public static void onHover(int action, float x, float y) { postAsync(() -> Windows.hover(action, x, y)); }
  public static void onScroll(float x, float y, float h, float v) { postAsync(() -> Windows.scroll(x, y, h, v)); }
  public static void onText(String text, int action) {
    postAsync(() -> TextInput.onText(text, action));
  }
  public static void onSelection(int start, int end) { postAsync(() -> TextInput.onSelection(start, end)); }
  public static void onVisibility(boolean visible) {
    ViewRoot.mainHandler().post(() -> ActivityThread.onPageVisibility(visible));
  }
  public static void onAudio(int id, int event) {
    ViewRoot.mainHandler().post(() -> Audio.event(id, event));
  }
  public static void onResize() {
    ViewRoot.mainHandler().post(() -> Windows.scheduleFrame(true));
  }
  public static void onWeb(int id, int event, String url) {
    ViewRoot.mainHandler().post(() -> android.webkit.WebView.event(id, event, url));
  }
}
