package android.view;

public final class VelocityTracker {
  static final int N = 20;
  final float[] xs = new float[N], ys = new float[N]; final long[] ts = new long[N];
  int n, head;
  float vx, vy;
  private VelocityTracker() {}
  public static VelocityTracker obtain() { return new VelocityTracker(); }
  public void recycle() { clear(); }
  public void clear() { n = 0; head = 0; vx = vy = 0; }
  public void addMovement(MotionEvent e) {
    if (e.getActionMasked() == MotionEvent.ACTION_DOWN) clear();
    xs[head] = e.getX(); ys[head] = e.getY(); ts[head] = e.getEventTime();
    head = (head + 1) % N;
    if (n < N) n++;
  }
  public void computeCurrentVelocity(int units) { computeCurrentVelocity(units, Float.MAX_VALUE); }
  public void computeCurrentVelocity(int units, float max) {
    vx = vy = 0;
    if (n < 2) return;
    int last = (head - 1 + N) % N;
    long tEnd = ts[last];
    int first = last;
    for (int k = 1; k < n; k++) {
      int i = (last - k + N) % N;
      if (tEnd - ts[i] > 100) break;
      first = i;
    }
    long dt = tEnd - ts[first];
    if (dt <= 0) return;
    vx = (xs[last] - xs[first]) * units / dt;
    vy = (ys[last] - ys[first]) * units / dt;
    vx = Math.max(-max, Math.min(max, vx));
    vy = Math.max(-max, Math.min(max, vy));
  }
  public float getXVelocity() { return vx; }
  public float getYVelocity() { return vy; }
  public float getXVelocity(int id) { return vx; }
  public float getYVelocity(int id) { return vy; }
}
