package android.view;

public final class MotionEvent extends InputEvent {
  public static final int ACTION_MASK = 0xff, ACTION_DOWN = 0, ACTION_UP = 1, ACTION_MOVE = 2, ACTION_CANCEL = 3, ACTION_OUTSIDE = 4,
      ACTION_POINTER_DOWN = 5, ACTION_POINTER_UP = 6, ACTION_HOVER_MOVE = 7, ACTION_SCROLL = 8, ACTION_HOVER_ENTER = 9, ACTION_HOVER_EXIT = 10,
      ACTION_POINTER_INDEX_MASK = 0xff00, ACTION_POINTER_INDEX_SHIFT = 8,
      ACTION_POINTER_1_DOWN = 5, ACTION_POINTER_2_DOWN = 0x105, ACTION_POINTER_3_DOWN = 0x205,
      ACTION_POINTER_1_UP = 6, ACTION_POINTER_2_UP = 0x106, ACTION_POINTER_3_UP = 0x206,
      ACTION_POINTER_ID_MASK = 0xff00, ACTION_POINTER_ID_SHIFT = 8,
      EDGE_TOP = 1, EDGE_BOTTOM = 2, EDGE_LEFT = 4, EDGE_RIGHT = 8, AXIS_X = 0, AXIS_Y = 1, AXIS_PRESSURE = 2, AXIS_SIZE = 3,
      TOOL_TYPE_FINGER = 1, BUTTON_PRIMARY = 1;
  public static final int ACTION_BUTTON_PRESS = 11, ACTION_BUTTON_RELEASE = 12, INVALID_POINTER_ID = -1,
      AXIS_ORIENTATION = 8, AXIS_TOUCH_MAJOR = 4, AXIS_TOUCH_MINOR = 5, AXIS_TOOL_MAJOR = 6, AXIS_TOOL_MINOR = 7, AXIS_VSCROLL = 9,
      AXIS_HSCROLL = 10, AXIS_Z = 11, AXIS_RX = 12, AXIS_RY = 13, AXIS_RZ = 14, AXIS_HAT_X = 15, AXIS_HAT_Y = 16, AXIS_LTRIGGER = 17,
      AXIS_RTRIGGER = 18, AXIS_THROTTLE = 19, AXIS_RUDDER = 20, AXIS_WHEEL = 21, AXIS_GAS = 22, AXIS_BRAKE = 23, AXIS_DISTANCE = 24,
      AXIS_TILT = 25, AXIS_SCROLL = 26, AXIS_RELATIVE_X = 27, AXIS_RELATIVE_Y = 28, AXIS_GENERIC_1 = 32,
      TOOL_TYPE_UNKNOWN = 0, TOOL_TYPE_STYLUS = 2, TOOL_TYPE_MOUSE = 3, TOOL_TYPE_ERASER = 4, TOOL_TYPE_PALM = 5,
      BUTTON_SECONDARY = 2, BUTTON_TERTIARY = 4, BUTTON_BACK = 8, BUTTON_FORWARD = 16, BUTTON_STYLUS_PRIMARY = 32, BUTTON_STYLUS_SECONDARY = 64,
      FLAG_WINDOW_IS_OBSCURED = 1, FLAG_WINDOW_IS_PARTIALLY_OBSCURED = 2, CLASSIFICATION_NONE = 0;
  public static final Creator<MotionEvent> CREATOR = new Creator<MotionEvent>() {
    public MotionEvent createFromParcel(android.os.Parcel in) { return readFrom(in); }
    public MotionEvent[] newArray(int size) { return new MotionEvent[size]; }
  };

  /** a pointer's coordinates and axes */
  public static final class PointerCoords {
    public float x, y, pressure, size, touchMajor, touchMinor, toolMajor, toolMinor, orientation;
    private java.util.HashMap<Integer, Float> axes;
    public PointerCoords() {}
    public PointerCoords(PointerCoords o) { copyFrom(o); }
    public void clear() { x = y = pressure = size = touchMajor = touchMinor = toolMajor = toolMinor = orientation = 0; axes = null; }
    public void copyFrom(PointerCoords o) {
      x = o.x; y = o.y; pressure = o.pressure; size = o.size; touchMajor = o.touchMajor; touchMinor = o.touchMinor;
      toolMajor = o.toolMajor; toolMinor = o.toolMinor; orientation = o.orientation;
      axes = o.axes == null ? null : new java.util.HashMap<>(o.axes);
    }
    public float getAxisValue(int axis) {
      switch (axis) {
        case AXIS_X: return x; case AXIS_Y: return y; case AXIS_PRESSURE: return pressure; case AXIS_SIZE: return size;
        case AXIS_TOUCH_MAJOR: return touchMajor; case AXIS_TOUCH_MINOR: return touchMinor; case AXIS_TOOL_MAJOR: return toolMajor;
        case AXIS_TOOL_MINOR: return toolMinor; case AXIS_ORIENTATION: return orientation;
        default: {
          if (axis < 0 || axis > 63) throw new IllegalArgumentException("Axis out of range.");
          Float f = axes == null ? null : axes.get(axis); return f == null ? 0 : f;
        }
      }
    }
    public void setAxisValue(int axis, float v) {
      switch (axis) {
        case AXIS_X: x = v; break; case AXIS_Y: y = v; break; case AXIS_PRESSURE: pressure = v; break; case AXIS_SIZE: size = v; break;
        case AXIS_TOUCH_MAJOR: touchMajor = v; break; case AXIS_TOUCH_MINOR: touchMinor = v; break; case AXIS_TOOL_MAJOR: toolMajor = v; break;
        case AXIS_TOOL_MINOR: toolMinor = v; break; case AXIS_ORIENTATION: orientation = v; break;
        default: {
          if (axis < 0 || axis > 63) throw new IllegalArgumentException("Axis out of range.");
          if (axes == null) axes = new java.util.HashMap<>();
          axes.put(axis, v);
        }
      }
    }
    /** (hidden API) whether an axis beyond the fixed ones is set */
    boolean hasAxes() { return axes != null && !axes.isEmpty(); }
  }
  /** a pointer's id and tool */
  public static final class PointerProperties {
    public int id = INVALID_POINTER_ID, toolType = TOOL_TYPE_UNKNOWN;
    public PointerProperties() {}
    public PointerProperties(PointerProperties o) { copyFrom(o); }
    public void clear() { id = INVALID_POINTER_ID; toolType = TOOL_TYPE_UNKNOWN; }
    public void copyFrom(PointerProperties o) { id = o.id; toolType = o.toolType; }
    @Override public boolean equals(Object o) { return o instanceof PointerProperties && ((PointerProperties) o).id == id && ((PointerProperties) o).toolType == toolType; }
    @Override public int hashCode() { return id | (toolType << 8); }
  }

  // ---- state: pointers, and samples (history, then the current one) of each pointer's coordinates
  int action, flags, edgeFlags, metaState, buttonState, actionButton, source = InputDevice.SOURCE_TOUCHSCREEN, deviceId, displayId, classification;
  float xPrecision = 1, yPrecision = 1;
  long downTime;
  int count;
  PointerProperties[] props = new PointerProperties[0];
  int samples;
  long[] timesNs = new long[1];
  PointerCoords[] coords = new PointerCoords[0];   // sample s, pointer i at s * count + i
  float offsetX, offsetY;    // added to every x, y (offsetLocation)
  float rawOffX, rawOffY;    // raw = coordinates + rawOff, without the location offset

  private MotionEvent() {}
  private PointerCoords at(int i, int s) {
    if (i < 0 || i >= count) throw new IllegalArgumentException("pointerIndex out of range");
    return coords[s * count + i];
  }
  private PointerCoords cur(int i) { return at(i, samples - 1); }
  private int histPos(int pos) {
    if (pos < 0 || pos >= samples - 1) throw new IllegalArgumentException("historyPos out of range");
    return pos;
  }

  static MotionEvent make(long downTime, long eventTime, int action, int pointerCount, PointerProperties[] pp, PointerCoords[] pc,
      int metaState, int buttonState, float xPrecision, float yPrecision, int deviceId, int edgeFlags, int source, int displayId, int flags, int classification) {
    if (pointerCount < 1) throw new IllegalArgumentException("pointerCount must be at least 1");
    if (pp == null || pp.length < pointerCount) throw new IllegalArgumentException("pointerProperties array must be large enough to hold all pointers");
    if (pc == null || pc.length < pointerCount) throw new IllegalArgumentException("pointerCoords array must be large enough to hold all pointers");
    MotionEvent e = new MotionEvent();
    e.downTime = downTime; e.action = action; e.metaState = metaState; e.buttonState = buttonState; e.xPrecision = xPrecision; e.yPrecision = yPrecision;
    e.deviceId = deviceId; e.edgeFlags = edgeFlags; e.source = source; e.displayId = displayId; e.flags = flags; e.classification = classification;
    e.count = pointerCount;
    e.props = new PointerProperties[pointerCount];
    e.coords = new PointerCoords[pointerCount];
    for (int i = 0; i < pointerCount; i++) { e.props[i] = new PointerProperties(pp[i]); e.coords[i] = new PointerCoords(pc[i]); }
    e.samples = 1;
    e.timesNs = new long[] {eventTime * 1000000L};
    return e;
  }
  public static MotionEvent obtain(long downTime, long eventTime, int action, int pointerCount, PointerProperties[] pointerProperties, PointerCoords[] pointerCoords,
      int metaState, int buttonState, float xPrecision, float yPrecision, int deviceId, int edgeFlags, int source, int displayId, int flags, int classification) {
    return make(downTime, eventTime, action, pointerCount, pointerProperties, pointerCoords, metaState, buttonState, xPrecision, yPrecision, deviceId, edgeFlags, source, displayId, flags, classification);
  }
  public static MotionEvent obtain(long downTime, long eventTime, int action, int pointerCount, PointerProperties[] pointerProperties, PointerCoords[] pointerCoords,
      int metaState, int buttonState, float xPrecision, float yPrecision, int deviceId, int edgeFlags, int source, int displayId, int flags) {
    return make(downTime, eventTime, action, pointerCount, pointerProperties, pointerCoords, metaState, buttonState, xPrecision, yPrecision, deviceId, edgeFlags, source, displayId, flags, CLASSIFICATION_NONE);
  }
  public static MotionEvent obtain(long downTime, long eventTime, int action, int pointerCount, PointerProperties[] pointerProperties, PointerCoords[] pointerCoords,
      int metaState, int buttonState, float xPrecision, float yPrecision, int deviceId, int edgeFlags, int source, int flags) {
    return make(downTime, eventTime, action, pointerCount, pointerProperties, pointerCoords, metaState, buttonState, xPrecision, yPrecision, deviceId, edgeFlags, source, 0, flags, CLASSIFICATION_NONE);
  }
  @Deprecated public static MotionEvent obtain(long downTime, long eventTime, int action, int pointerCount, int[] pointerIds, PointerCoords[] pointerCoords,
      int metaState, float xPrecision, float yPrecision, int deviceId, int edgeFlags, int source, int flags) {
    PointerProperties[] pp = new PointerProperties[pointerCount];
    for (int i = 0; i < pointerCount; i++) { pp[i] = new PointerProperties(); pp[i].id = pointerIds[i]; pp[i].toolType = TOOL_TYPE_FINGER; }
    return make(downTime, eventTime, action, pointerCount, pp, pointerCoords, metaState, 0, xPrecision, yPrecision, deviceId, edgeFlags, source, 0, flags, CLASSIFICATION_NONE);
  }
  private static MotionEvent single(long downTime, long eventTime, int action, float x, float y, float pressure, float size, int metaState,
      float xPrecision, float yPrecision, int deviceId, int edgeFlags, int source, int displayId) {
    PointerProperties p = new PointerProperties();
    p.id = 0; p.toolType = TOOL_TYPE_FINGER;
    PointerCoords c = new PointerCoords();
    c.x = x; c.y = y; c.pressure = pressure; c.size = size;
    return make(downTime, eventTime, action, 1, new PointerProperties[] {p}, new PointerCoords[] {c}, metaState, 0, xPrecision, yPrecision, deviceId, edgeFlags, source, displayId, 0, CLASSIFICATION_NONE);
  }
  public static MotionEvent obtain(long downTime, long eventTime, int action, float x, float y, float pressure, float size, int metaState,
      float xPrecision, float yPrecision, int deviceId, int edgeFlags) {
    return single(downTime, eventTime, action, x, y, pressure, size, metaState, xPrecision, yPrecision, deviceId, edgeFlags, InputDevice.SOURCE_UNKNOWN, 0);
  }
  public static MotionEvent obtain(long downTime, long eventTime, int action, float x, float y, float pressure, float size, int metaState,
      float xPrecision, float yPrecision, int deviceId, int edgeFlags, int source, int displayId) {
    return single(downTime, eventTime, action, x, y, pressure, size, metaState, xPrecision, yPrecision, deviceId, edgeFlags, source, displayId);
  }
  @Deprecated public static MotionEvent obtain(long downTime, long eventTime, int action, int pointerCount, float x, float y, float pressure, float size, int metaState,
      float xPrecision, float yPrecision, int deviceId, int edgeFlags) {
    return obtain(downTime, eventTime, action, x, y, pressure, size, metaState, xPrecision, yPrecision, deviceId, edgeFlags);
  }
  public static MotionEvent obtain(long downTime, long eventTime, int action, float x, float y, int metaState) {
    return obtain(downTime, eventTime, action, x, y, 1.0f, 1.0f, metaState, 1.0f, 1.0f, 0, 0);
  }
  /** (not part of the API) touch-screen pointers at window coordinates (Windows) */
  public static MotionEvent obtain(long downTime, long eventTime, int action, int count, int[] pointerIds, float[] x, float[] y, int meta) {
    PointerProperties[] pp = new PointerProperties[count];
    PointerCoords[] pc = new PointerCoords[count];
    for (int i = 0; i < count; i++) {
      pp[i] = new PointerProperties(); pp[i].id = pointerIds[i]; pp[i].toolType = TOOL_TYPE_FINGER;
      pc[i] = new PointerCoords(); pc[i].x = x[i]; pc[i].y = y[i]; pc[i].pressure = 1; pc[i].size = 0.1f; pc[i].touchMajor = pc[i].touchMinor = 10;
    }
    return make(downTime, eventTime, action, count, pp, pc, meta, 0, 1, 1, 0, 0, InputDevice.SOURCE_TOUCHSCREEN, 0, 0, CLASSIFICATION_NONE);
  }
  public static MotionEvent obtain(MotionEvent o) {
    MotionEvent e = new MotionEvent();
    e.copyHeader(o);
    e.count = o.count; e.samples = o.samples;
    e.props = new PointerProperties[o.count];
    for (int i = 0; i < o.count; i++) e.props[i] = new PointerProperties(o.props[i]);
    e.coords = new PointerCoords[o.coords.length];
    for (int i = 0; i < o.coords.length; i++) e.coords[i] = new PointerCoords(o.coords[i]);
    e.timesNs = o.timesNs.clone();
    return e;
  }
  public static MotionEvent obtainNoHistory(MotionEvent o) {
    MotionEvent e = new MotionEvent();
    e.copyHeader(o);
    e.count = o.count; e.samples = 1;
    e.props = new PointerProperties[o.count];
    e.coords = new PointerCoords[o.count];
    for (int i = 0; i < o.count; i++) { e.props[i] = new PointerProperties(o.props[i]); e.coords[i] = new PointerCoords(o.cur(i)); }
    e.timesNs = new long[] {o.timesNs[o.samples - 1]};
    return e;
  }
  private void copyHeader(MotionEvent o) {
    action = o.action; flags = o.flags; edgeFlags = o.edgeFlags; metaState = o.metaState; buttonState = o.buttonState; actionButton = o.actionButton;
    source = o.source; deviceId = o.deviceId; displayId = o.displayId; classification = o.classification; xPrecision = o.xPrecision; yPrecision = o.yPrecision;
    downTime = o.downTime; offsetX = o.offsetX; offsetY = o.offsetY; rawOffX = o.rawOffX; rawOffY = o.rawOffY;
  }
  public void recycle() {}
  /** (not part of the API) raw coordinates = window coordinates + the window's position */
  public void a2wSetRawOffset(float dx, float dy) { rawOffX = dx; rawOffY = dy; }
  /** (not part of the API) sets the buttons and tool of a mouse or stylus event (Windows) */
  public void a2wSetPointer(int toolType, int buttons, int actionButton, float pressure) {
    for (PointerProperties p : props) p.toolType = toolType;
    for (PointerCoords c : coords) { c.pressure = pressure; if (toolType != TOOL_TYPE_FINGER) { c.size = 0; c.touchMajor = c.touchMinor = 0; } }
    buttonState = buttons; this.actionButton = actionButton;
    source = toolType == TOOL_TYPE_MOUSE ? InputDevice.SOURCE_MOUSE : toolType == TOOL_TYPE_STYLUS || toolType == TOOL_TYPE_ERASER ? InputDevice.SOURCE_STYLUS : InputDevice.SOURCE_TOUCHSCREEN;
  }
  /** (not part of the API) earlier samples of the pointers (Windows: the browser's coalesced moves) */
  public void a2wAddHistory(long eventTimeNanos, float[] x, float[] y) {
    PointerCoords[] c = new PointerCoords[x.length];
    for (int i = 0; i < c.length; i++) { c[i] = new PointerCoords(cur(i)); c[i].x = x[i] - offsetX; c[i].y = y[i] - offsetY; }
    insertSample(samples - 1, eventTimeNanos, c);
  }
  private void insertSample(int s, long tNs, PointerCoords[] c) {
    PointerCoords[] nc = new PointerCoords[(samples + 1) * count];
    System.arraycopy(coords, 0, nc, 0, s * count);
    for (int i = 0; i < count; i++) nc[s * count + i] = c[i];
    System.arraycopy(coords, s * count, nc, (s + 1) * count, (samples - s) * count);
    long[] nt = new long[samples + 1];
    System.arraycopy(timesNs, 0, nt, 0, s);
    nt[s] = tNs;
    System.arraycopy(timesNs, s, nt, s + 1, samples - s);
    coords = nc; timesNs = nt; samples++;
  }

  // ---- header
  public final int getDeviceId() { return deviceId; }
  public final int getSource() { return source; }
  public final void setSource(int s) { source = s; }
  public int getDisplayId() { return displayId; }
  public final int getAction() { return action; }
  public final int getActionMasked() { return action & ACTION_MASK; }
  public final int getActionIndex() { return (action & ACTION_POINTER_INDEX_MASK) >> ACTION_POINTER_INDEX_SHIFT; }
  public final void setAction(int a) { action = a; }
  public final int getFlags() { return flags; }
  public final int getActionButton() { return actionButton; }
  public final int getButtonState() { return buttonState; }
  public final boolean isButtonPressed(int button) { return button != 0 && (buttonState & button) == button; }
  public int getClassification() { return classification; }
  public final int getMetaState() { return metaState; }
  public final int getEdgeFlags() { return edgeFlags; }
  public final void setEdgeFlags(int f) { edgeFlags = f; }
  public final long getDownTime() { return downTime; }
  public final long getEventTime() { return timesNs[samples - 1] / 1000000L; }
  public final long getEventTimeNanos() { return timesNs[samples - 1]; }
  public final float getXPrecision() { return xPrecision; }
  public final float getYPrecision() { return yPrecision; }
  public final int getPointerCount() { return count; }
  public final int getPointerId(int i) { if (i < 0 || i >= count) throw new IllegalArgumentException("pointerIndex out of range"); return props[i].id; }
  public final int getToolType(int i) { if (i < 0 || i >= count) throw new IllegalArgumentException("pointerIndex out of range"); return props[i].toolType; }
  public final int findPointerIndex(int id) { for (int i = 0; i < count; i++) if (props[i].id == id) return i; return -1; }
  public final void getPointerProperties(int i, PointerProperties out) { if (i < 0 || i >= count) throw new IllegalArgumentException("pointerIndex out of range"); out.copyFrom(props[i]); }

  // ---- current sample
  private float axis(PointerCoords c, int axis) {
    float v = c.getAxisValue(axis);
    return axis == AXIS_X ? v + offsetX : axis == AXIS_Y ? v + offsetY : v;
  }
  public final float getX() { return cur(0).x + offsetX; }
  public final float getY() { return cur(0).y + offsetY; }
  public final float getX(int i) { return cur(i).x + offsetX; }
  public final float getY(int i) { return cur(i).y + offsetY; }
  public final float getPressure() { return cur(0).pressure; }
  public final float getPressure(int i) { return cur(i).pressure; }
  public final float getSize() { return cur(0).size; }
  public final float getSize(int i) { return cur(i).size; }
  public final float getTouchMajor() { return cur(0).touchMajor; }
  public final float getTouchMajor(int i) { return cur(i).touchMajor; }
  public final float getTouchMinor() { return cur(0).touchMinor; }
  public final float getTouchMinor(int i) { return cur(i).touchMinor; }
  public final float getToolMajor() { return cur(0).toolMajor; }
  public final float getToolMajor(int i) { return cur(i).toolMajor; }
  public final float getToolMinor() { return cur(0).toolMinor; }
  public final float getToolMinor(int i) { return cur(i).toolMinor; }
  public final float getOrientation() { return cur(0).orientation; }
  public final float getOrientation(int i) { return cur(i).orientation; }
  public final float getAxisValue(int axis) { return axis(cur(0), axis); }
  public final float getAxisValue(int axis, int i) { return axis(cur(i), axis); }
  public final float getRawX() { return cur(0).x + rawOffX; }
  public final float getRawY() { return cur(0).y + rawOffY; }
  public float getRawX(int i) { return cur(i).x + rawOffX; }
  public float getRawY(int i) { return cur(i).y + rawOffY; }
  public final void getPointerCoords(int i, PointerCoords out) { out.copyFrom(cur(i)); out.x += offsetX; out.y += offsetY; }

  // ---- history
  public final int getHistorySize() { return samples - 1; }
  public final long getHistoricalEventTime(int pos) { return timesNs[histPos(pos)] / 1000000L; }
  public long getHistoricalEventTimeNanos(int pos) { return timesNs[histPos(pos)]; }
  public final float getHistoricalX(int pos) { return at(0, histPos(pos)).x + offsetX; }
  public final float getHistoricalY(int pos) { return at(0, histPos(pos)).y + offsetY; }
  public final float getHistoricalX(int i, int pos) { return at(i, histPos(pos)).x + offsetX; }
  public final float getHistoricalY(int i, int pos) { return at(i, histPos(pos)).y + offsetY; }
  public final float getHistoricalPressure(int pos) { return at(0, histPos(pos)).pressure; }
  public final float getHistoricalPressure(int i, int pos) { return at(i, histPos(pos)).pressure; }
  public final float getHistoricalSize(int pos) { return at(0, histPos(pos)).size; }
  public final float getHistoricalSize(int i, int pos) { return at(i, histPos(pos)).size; }
  public final float getHistoricalTouchMajor(int pos) { return at(0, histPos(pos)).touchMajor; }
  public final float getHistoricalTouchMajor(int i, int pos) { return at(i, histPos(pos)).touchMajor; }
  public final float getHistoricalTouchMinor(int pos) { return at(0, histPos(pos)).touchMinor; }
  public final float getHistoricalTouchMinor(int i, int pos) { return at(i, histPos(pos)).touchMinor; }
  public final float getHistoricalToolMajor(int pos) { return at(0, histPos(pos)).toolMajor; }
  public final float getHistoricalToolMajor(int i, int pos) { return at(i, histPos(pos)).toolMajor; }
  public final float getHistoricalToolMinor(int pos) { return at(0, histPos(pos)).toolMinor; }
  public final float getHistoricalToolMinor(int i, int pos) { return at(i, histPos(pos)).toolMinor; }
  public final float getHistoricalOrientation(int pos) { return at(0, histPos(pos)).orientation; }
  public final float getHistoricalOrientation(int i, int pos) { return at(i, histPos(pos)).orientation; }
  public final float getHistoricalAxisValue(int axis, int pos) { return axis(at(0, histPos(pos)), axis); }
  public final float getHistoricalAxisValue(int axis, int i, int pos) { return axis(at(i, histPos(pos)), axis); }
  public final void getHistoricalPointerCoords(int i, int pos, PointerCoords out) { out.copyFrom(at(i, histPos(pos))); out.x += offsetX; out.y += offsetY; }
  /** the current sample becomes history; the new one is the current */
  public final void addBatch(long eventTime, float x, float y, float pressure, float size, int metaState) {
    PointerCoords[] c = new PointerCoords[count];
    for (int i = 0; i < count; i++) c[i] = new PointerCoords(cur(i));
    c[0].x = x - offsetX; c[0].y = y - offsetY; c[0].pressure = pressure; c[0].size = size;
    insertSample(samples, eventTime * 1000000L, c);
    this.metaState |= metaState;
  }
  public final void addBatch(long eventTime, PointerCoords[] pointerCoords, int metaState) {
    if (pointerCoords.length < count) throw new IllegalArgumentException("pointerCoords array must be large enough to hold all pointers");
    PointerCoords[] c = new PointerCoords[count];
    for (int i = 0; i < count; i++) { c[i] = new PointerCoords(pointerCoords[i]); c[i].x -= offsetX; c[i].y -= offsetY; }
    insertSample(samples, eventTime * 1000000L, c);
    this.metaState |= metaState;
  }

  // ---- location
  public final void offsetLocation(float dx, float dy) { offsetX += dx; offsetY += dy; }
  public final void setLocation(float x, float y) { offsetX = x - cur(0).x; offsetY = y - cur(0).y; }
  /** maps every sample's points (orientation turns with the matrix) */
  public final void transform(android.graphics.Matrix m) {
    float[] p = new float[2];
    float[] v = {0, 0, 0, -1};
    m.mapVectors(v);
    double turn = Math.atan2(v[2] - v[0], -(v[3] - v[1]));
    for (PointerCoords c : coords) {
      p[0] = c.x + offsetX; p[1] = c.y + offsetY;
      m.mapPoints(p);
      c.x = p[0]; c.y = p[1];
      if (c.orientation != 0 || turn != 0) c.orientation = (float) (c.orientation + turn);
    }
    offsetX = offsetY = 0;
  }
  /** (hidden API) a copy holding only the pointers in idBits (ViewGroup splitting) */
  public MotionEvent split(int idBits) {
    int n = 0, newIndex = -1, act = getActionMasked(), ai = getActionIndex();
    int[] keep = new int[count];
    for (int i = 0; i < count; i++) {
      if ((idBits & (1 << props[i].id)) == 0) continue;
      if (i == ai) newIndex = n;
      keep[n++] = i;
    }
    MotionEvent e = new MotionEvent();
    e.copyHeader(this);
    e.count = n; e.samples = samples; e.timesNs = timesNs.clone();
    e.props = new PointerProperties[n];
    for (int k = 0; k < n; k++) e.props[k] = new PointerProperties(props[keep[k]]);
    e.coords = new PointerCoords[samples * n];
    for (int s = 0; s < samples; s++) for (int k = 0; k < n; k++) e.coords[s * n + k] = new PointerCoords(coords[s * count + keep[k]]);
    if (act == ACTION_POINTER_DOWN || act == ACTION_POINTER_UP) {
      if (newIndex < 0) e.action = ACTION_MOVE;
      else if (n == 1) e.action = act == ACTION_POINTER_DOWN ? ACTION_DOWN : ACTION_UP;
      else e.action = act | (newIndex << ACTION_POINTER_INDEX_SHIFT);
    }
    return e;
  }
  /** (hidden API) */
  public int pointerIdBits() { int b = 0; for (int i = 0; i < count; i++) b |= 1 << props[i].id; return b; }

  // ---- names
  private static final String[] AXES = {"X", "Y", "PRESSURE", "SIZE", "TOUCH_MAJOR", "TOUCH_MINOR", "TOOL_MAJOR", "TOOL_MINOR", "ORIENTATION",
      "VSCROLL", "HSCROLL", "Z", "RX", "RY", "RZ", "HAT_X", "HAT_Y", "LTRIGGER", "RTRIGGER", "THROTTLE", "RUDDER", "WHEEL", "GAS", "BRAKE",
      "DISTANCE", "TILT", "SCROLL", "RELATIVE_X", "RELATIVE_Y", null, null, null, "GENERIC_1", "GENERIC_2", "GENERIC_3", "GENERIC_4",
      "GENERIC_5", "GENERIC_6", "GENERIC_7", "GENERIC_8", "GENERIC_9", "GENERIC_10", "GENERIC_11", "GENERIC_12", "GENERIC_13", "GENERIC_14",
      "GENERIC_15", "GENERIC_16", "GESTURE_X_OFFSET", "GESTURE_Y_OFFSET", "GESTURE_SCROLL_X_DISTANCE", "GESTURE_SCROLL_Y_DISTANCE",
      "GESTURE_PINCH_SCALE_FACTOR", "GESTURE_SWIPE_FINGER_COUNT"};
  public static String axisToString(int axis) { String n = axis >= 0 && axis < AXES.length ? AXES[axis] : null; return n != null ? "AXIS_" + n : Integer.toString(axis); }
  public static int axisFromString(String s) {
    if (s.startsWith("AXIS_")) { s = s.substring(5); for (int i = 0; i < AXES.length; i++) if (s.equals(AXES[i])) return i; }
    try { return Integer.parseInt(s, 10); } catch (NumberFormatException e) { return -1; }
  }
  public static String actionToString(int a) {
    switch (a) {
      case ACTION_DOWN: return "ACTION_DOWN"; case ACTION_UP: return "ACTION_UP"; case ACTION_CANCEL: return "ACTION_CANCEL";
      case ACTION_OUTSIDE: return "ACTION_OUTSIDE"; case ACTION_MOVE: return "ACTION_MOVE"; case ACTION_HOVER_MOVE: return "ACTION_HOVER_MOVE";
      case ACTION_SCROLL: return "ACTION_SCROLL"; case ACTION_HOVER_ENTER: return "ACTION_HOVER_ENTER"; case ACTION_HOVER_EXIT: return "ACTION_HOVER_EXIT";
      case ACTION_BUTTON_PRESS: return "ACTION_BUTTON_PRESS"; case ACTION_BUTTON_RELEASE: return "ACTION_BUTTON_RELEASE";
    }
    int index = (a & ACTION_POINTER_INDEX_MASK) >> ACTION_POINTER_INDEX_SHIFT;
    switch (a & ACTION_MASK) {
      case ACTION_POINTER_DOWN: return "ACTION_POINTER_DOWN(" + index + ")";
      case ACTION_POINTER_UP: return "ACTION_POINTER_UP(" + index + ")";
      default: return Integer.toString(a);
    }
  }
  private static String toolName(int t) {
    switch (t) { case TOOL_TYPE_FINGER: return "TOOL_TYPE_FINGER"; case TOOL_TYPE_STYLUS: return "TOOL_TYPE_STYLUS"; case TOOL_TYPE_MOUSE: return "TOOL_TYPE_MOUSE";
      case TOOL_TYPE_ERASER: return "TOOL_TYPE_ERASER"; case TOOL_TYPE_PALM: return "TOOL_TYPE_PALM"; case TOOL_TYPE_UNKNOWN: return "TOOL_TYPE_UNKNOWN"; default: return Integer.toString(t); }
  }
  @Override public String toString() {
    StringBuilder b = new StringBuilder("MotionEvent { action=").append(actionToString(action)).append(", actionButton=").append(actionButton);
    for (int i = 0; i < count; i++) {
      b.append(", id[").append(i).append("]=").append(props[i].id).append(", x[").append(i).append("]=").append(getX(i)).append(", y[").append(i).append("]=").append(getY(i));
      b.append(", toolType[").append(i).append("]=").append(toolName(props[i].toolType));
    }
    b.append(", buttonState=").append(buttonState).append(", classification=").append(classification == CLASSIFICATION_NONE ? "NONE" : Integer.toString(classification));
    b.append(", metaState=").append(KeyEvent.metaStateToString(metaState)).append(", flags=0x").append(Integer.toHexString(flags)).append(", edgeFlags=0x").append(Integer.toHexString(edgeFlags));
    b.append(", pointerCount=").append(count).append(", historySize=").append(samples - 1).append(", eventTime=").append(getEventTime()).append(", downTime=").append(downTime);
    b.append(", deviceId=").append(deviceId).append(", source=0x").append(Integer.toHexString(source)).append(", displayId=").append(displayId).append(" }");
    return b.toString();
  }

  // ---- parcel
  @Override public void writeToParcel(android.os.Parcel out, int f) {
    out.writeInt(count); out.writeInt(samples);
    out.writeInt(deviceId); out.writeInt(source); out.writeInt(displayId); out.writeInt(action); out.writeInt(actionButton); out.writeInt(flags);
    out.writeInt(edgeFlags); out.writeInt(metaState); out.writeInt(buttonState); out.writeInt(classification);
    out.writeFloat(xPrecision); out.writeFloat(yPrecision); out.writeLong(downTime);
    for (PointerProperties p : props) { out.writeInt(p.id); out.writeInt(p.toolType); }
    for (int s = 0; s < samples; s++) {
      out.writeLong(timesNs[s]);
      for (int i = 0; i < count; i++) {
        PointerCoords c = coords[s * count + i];
        out.writeFloat(c.x + offsetX); out.writeFloat(c.y + offsetY); out.writeFloat(c.pressure); out.writeFloat(c.size);
        out.writeFloat(c.touchMajor); out.writeFloat(c.touchMinor); out.writeFloat(c.toolMajor); out.writeFloat(c.toolMinor); out.writeFloat(c.orientation);
      }
    }
  }
  static MotionEvent readFrom(android.os.Parcel in) {
    MotionEvent e = new MotionEvent();
    e.count = in.readInt(); e.samples = in.readInt();
    e.deviceId = in.readInt(); e.source = in.readInt(); e.displayId = in.readInt(); e.action = in.readInt(); e.actionButton = in.readInt(); e.flags = in.readInt();
    e.edgeFlags = in.readInt(); e.metaState = in.readInt(); e.buttonState = in.readInt(); e.classification = in.readInt();
    e.xPrecision = in.readFloat(); e.yPrecision = in.readFloat(); e.downTime = in.readLong();
    e.props = new PointerProperties[e.count];
    for (int i = 0; i < e.count; i++) { e.props[i] = new PointerProperties(); e.props[i].id = in.readInt(); e.props[i].toolType = in.readInt(); }
    e.timesNs = new long[e.samples];
    e.coords = new PointerCoords[e.samples * e.count];
    for (int s = 0; s < e.samples; s++) {
      e.timesNs[s] = in.readLong();
      for (int i = 0; i < e.count; i++) {
        PointerCoords c = new PointerCoords();
        c.x = in.readFloat(); c.y = in.readFloat(); c.pressure = in.readFloat(); c.size = in.readFloat();
        c.touchMajor = in.readFloat(); c.touchMinor = in.readFloat(); c.toolMajor = in.readFloat(); c.toolMinor = in.readFloat(); c.orientation = in.readFloat();
        e.coords[s * e.count + i] = c;
      }
    }
    return e;
  }
}
