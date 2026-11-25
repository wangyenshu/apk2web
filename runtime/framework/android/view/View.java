package android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import a2w.Attrs;
import a2w.Values;
import java.util.ArrayList;

/**
 * android.view.View, reimplemented after the 4.x framework: measure/layout/draw passes,
 * drawable state, touch handling (press, click, long press) and view animations.
 */
public class View implements Drawable.Callback, KeyEvent.Callback {
  public static final int ACCESSIBILITY_DATA_SENSITIVE_AUTO = 0;
  public static final int ACCESSIBILITY_DATA_SENSITIVE_NO = 2;
  public static final int ACCESSIBILITY_DATA_SENSITIVE_YES = 1;
  public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
  public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
  public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
  public static final int AUTOFILL_FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 1;
  public static final int AUTOFILL_TYPE_DATE = 4;
  public static final int AUTOFILL_TYPE_LIST = 3;
  public static final int AUTOFILL_TYPE_NONE = 0;
  public static final int AUTOFILL_TYPE_TEXT = 1;
  public static final int AUTOFILL_TYPE_TOGGLE = 2;
  public static final int CONTENT_SENSITIVITY_AUTO = 0;
  public static final int CONTENT_SENSITIVITY_NOT_SENSITIVE = 2;
  public static final int CONTENT_SENSITIVITY_SENSITIVE = 1;
  public static final int DRAG_FLAG_ACCESSIBILITY_ACTION = 1024;
  public static final int DRAG_FLAG_GLOBAL = 256;
  public static final int DRAG_FLAG_GLOBAL_PERSISTABLE_URI_PERMISSION = 64;
  public static final int DRAG_FLAG_GLOBAL_PREFIX_URI_PERMISSION = 128;
  public static final int DRAG_FLAG_GLOBAL_SAME_APPLICATION = 4096;
  public static final int DRAG_FLAG_GLOBAL_URI_READ = 1;
  public static final int DRAG_FLAG_GLOBAL_URI_WRITE = 2;
  public static final int DRAG_FLAG_OPAQUE = 512;
  public static final int DRAG_FLAG_START_INTENT_SENDER_ON_UNHANDLED_DRAG = 8192;
  public static final int FIND_VIEWS_WITH_CONTENT_DESCRIPTION = 2;
  public static final int FIND_VIEWS_WITH_TEXT = 1;
  public static final int FOCUSABLES_ALL = 0;
  public static final int FOCUSABLES_TOUCH_MODE = 1;
  public static final int FOCUSABLE_AUTO = 16;
  public static final int IMPORTANT_FOR_AUTOFILL_AUTO = 0;
  public static final int IMPORTANT_FOR_AUTOFILL_NO = 2;
  public static final int IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS = 8;
  public static final int IMPORTANT_FOR_AUTOFILL_YES = 1;
  public static final int IMPORTANT_FOR_AUTOFILL_YES_EXCLUDE_DESCENDANTS = 4;
  public static final int IMPORTANT_FOR_CONTENT_CAPTURE_AUTO = 0;
  public static final int IMPORTANT_FOR_CONTENT_CAPTURE_NO = 2;
  public static final int IMPORTANT_FOR_CONTENT_CAPTURE_NO_EXCLUDE_DESCENDANTS = 8;
  public static final int IMPORTANT_FOR_CONTENT_CAPTURE_YES = 1;
  public static final int IMPORTANT_FOR_CONTENT_CAPTURE_YES_EXCLUDE_DESCENDANTS = 4;
  public static final int LAYOUT_DIRECTION_INHERIT = 2;
  public static final int LAYOUT_DIRECTION_LOCALE = 3;
  public static final int LAYOUT_DIRECTION_RTL = 1;
  public static final int NOT_FOCUSABLE = 0;
  public static final float REQUESTED_FRAME_RATE_CATEGORY_DEFAULT = Float.NaN;
  public static final float REQUESTED_FRAME_RATE_CATEGORY_HIGH = -4.0f;
  public static final float REQUESTED_FRAME_RATE_CATEGORY_LOW = -2.0f;
  public static final float REQUESTED_FRAME_RATE_CATEGORY_NORMAL = -3.0f;
  public static final float REQUESTED_FRAME_RATE_CATEGORY_NO_PREFERENCE = -1.0f;
  public static final int SCREEN_STATE_OFF = 0;
  public static final int SCREEN_STATE_ON = 1;
  public static final int SCROLL_AXIS_HORIZONTAL = 1;
  public static final int SCROLL_AXIS_NONE = 0;
  public static final int SCROLL_AXIS_VERTICAL = 2;
  public static final int SCROLL_CAPTURE_HINT_AUTO = 0;
  public static final int SCROLL_CAPTURE_HINT_EXCLUDE = 1;
  public static final int SCROLL_CAPTURE_HINT_EXCLUDE_DESCENDANTS = 4;
  public static final int SCROLL_CAPTURE_HINT_INCLUDE = 2;
  public static final int SCROLL_INDICATOR_BOTTOM = 2;
  public static final int SCROLL_INDICATOR_END = 32;
  public static final int SCROLL_INDICATOR_LEFT = 4;
  public static final int SCROLL_INDICATOR_RIGHT = 8;
  public static final int SCROLL_INDICATOR_START = 16;
  public static final int SCROLL_INDICATOR_TOP = 1;
  public static final int SYSTEM_UI_FLAG_FULLSCREEN = 4;
  public static final int SYSTEM_UI_FLAG_IMMERSIVE = 2048;
  public static final int SYSTEM_UI_FLAG_IMMERSIVE_STICKY = 4096;
  public static final int SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN = 1024;
  public static final int SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION = 512;
  public static final int SYSTEM_UI_FLAG_LAYOUT_STABLE = 256;
  public static final int SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR = 16;
  public static final int SYSTEM_UI_FLAG_LIGHT_STATUS_BAR = 8192;
  public static final int SYSTEM_UI_LAYOUT_FLAGS = 1536;
  public static final int TEXT_ALIGNMENT_CENTER = 4;
  public static final int TEXT_ALIGNMENT_INHERIT = 0;
  public static final int TEXT_ALIGNMENT_TEXT_END = 3;
  public static final int TEXT_ALIGNMENT_TEXT_START = 2;
  public static final int TEXT_ALIGNMENT_VIEW_END = 6;
  public static final int TEXT_ALIGNMENT_VIEW_START = 5;
  public static final int TEXT_DIRECTION_ANY_RTL = 2;
  public static final int TEXT_DIRECTION_FIRST_STRONG = 1;
  public static final int TEXT_DIRECTION_FIRST_STRONG_LTR = 6;
  public static final int TEXT_DIRECTION_FIRST_STRONG_RTL = 7;
  public static final int TEXT_DIRECTION_INHERIT = 0;
  public static final int TEXT_DIRECTION_LOCALE = 5;
  public static final int TEXT_DIRECTION_LTR = 3;
  public static final int TEXT_DIRECTION_RTL = 4;
  public static final int NO_ID = -1;
  public static final int VISIBLE = 0, INVISIBLE = 4, GONE = 8;
  static final int VISIBILITY_MASK = 0x0C;
  public static final int FOCUS_BACKWARD = 1, FOCUS_FORWARD = 2, FOCUS_LEFT = 17, FOCUS_UP = 33, FOCUS_RIGHT = 66, FOCUS_DOWN = 130;
  public static final int MEASURED_SIZE_MASK = 0x00ffffff, MEASURED_STATE_MASK = 0xff000000, MEASURED_HEIGHT_STATE_SHIFT = 16,
      MEASURED_STATE_TOO_SMALL = 0x01000000;
  public static final int SCROLLBARS_INSIDE_OVERLAY = 0, SCROLLBARS_INSIDE_INSET = 0x01000000, SCROLLBARS_OUTSIDE_OVERLAY = 0x02000000,
      SCROLLBARS_OUTSIDE_INSET = 0x03000000, SCROLLBAR_POSITION_DEFAULT = 0, SCROLLBAR_POSITION_LEFT = 1, SCROLLBAR_POSITION_RIGHT = 2;
  public static final int DRAWING_CACHE_QUALITY_LOW = 0x00080000, DRAWING_CACHE_QUALITY_HIGH = 0x00100000, DRAWING_CACHE_QUALITY_AUTO = 0;
  public static final int OVER_SCROLL_ALWAYS = 0, OVER_SCROLL_IF_CONTENT_SCROLLS = 1, OVER_SCROLL_NEVER = 2;
  public static final int LAYER_TYPE_NONE = 0, LAYER_TYPE_SOFTWARE = 1, LAYER_TYPE_HARDWARE = 2;
  public static final int SYSTEM_UI_FLAG_VISIBLE = 0, SYSTEM_UI_FLAG_LOW_PROFILE = 1, SYSTEM_UI_FLAG_HIDE_NAVIGATION = 2, STATUS_BAR_HIDDEN = 1, STATUS_BAR_VISIBLE = 0;
  public static final int KEEP_SCREEN_ON = 0x04000000, SOUND_EFFECTS_ENABLED = 0x08000000, HAPTIC_FEEDBACK_ENABLED = 0x10000000;
  public static final int TEXT_ALIGNMENT_GRAVITY = 1, LAYOUT_DIRECTION_LTR = 0, IMPORTANT_FOR_ACCESSIBILITY_NO = 2;

  protected static final int[] EMPTY_STATE_SET = {};
  protected static final int[] ENABLED_STATE_SET = {android.R.attr.state_enabled};
  protected static final int[] PRESSED_ENABLED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_enabled};
  protected static final int[] FOCUSED_STATE_SET = {android.R.attr.state_focused};
  protected static final int[] SELECTED_STATE_SET = {android.R.attr.state_selected};
  protected static final int[] PRESSED_STATE_SET = {android.R.attr.state_pressed};
  protected static final int[] WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_window_focused};
  protected static final int[] ENABLED_FOCUSED_STATE_SET = {android.R.attr.state_enabled, android.R.attr.state_focused};
  protected static final int[] PRESSED_FOCUSED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_focused};
  protected static final int[] PRESSED_SELECTED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_selected};
  protected static final int[] PRESSED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_window_focused};
  protected static final int[] ENABLED_SELECTED_STATE_SET = {android.R.attr.state_enabled, android.R.attr.state_selected};
  protected static final int[] ENABLED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_enabled, android.R.attr.state_window_focused};
  protected static final int[] FOCUSED_SELECTED_STATE_SET = {android.R.attr.state_focused, android.R.attr.state_selected};
  protected static final int[] FOCUSED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_focused, android.R.attr.state_window_focused};
  protected static final int[] SELECTED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_selected, android.R.attr.state_window_focused};
  protected static final int[] PRESSED_ENABLED_FOCUSED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_enabled, android.R.attr.state_focused};
  protected static final int[] PRESSED_ENABLED_SELECTED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_enabled, android.R.attr.state_selected};
  protected static final int[] PRESSED_ENABLED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_enabled, android.R.attr.state_window_focused};
  protected static final int[] PRESSED_FOCUSED_SELECTED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_focused, android.R.attr.state_selected};
  protected static final int[] PRESSED_FOCUSED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_focused, android.R.attr.state_window_focused};
  protected static final int[] PRESSED_SELECTED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_selected, android.R.attr.state_window_focused};
  protected static final int[] ENABLED_FOCUSED_SELECTED_STATE_SET = {android.R.attr.state_enabled, android.R.attr.state_focused, android.R.attr.state_selected};
  protected static final int[] ENABLED_FOCUSED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_enabled, android.R.attr.state_focused, android.R.attr.state_window_focused};
  protected static final int[] ENABLED_SELECTED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_enabled, android.R.attr.state_selected, android.R.attr.state_window_focused};
  protected static final int[] FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_focused, android.R.attr.state_selected, android.R.attr.state_window_focused};
  protected static final int[] PRESSED_ENABLED_FOCUSED_SELECTED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_enabled, android.R.attr.state_focused, android.R.attr.state_selected};
  protected static final int[] PRESSED_ENABLED_FOCUSED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_enabled, android.R.attr.state_focused, android.R.attr.state_window_focused};
  protected static final int[] PRESSED_ENABLED_SELECTED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_enabled, android.R.attr.state_selected, android.R.attr.state_window_focused};
  protected static final int[] PRESSED_FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_focused, android.R.attr.state_selected, android.R.attr.state_window_focused};
  protected static final int[] ENABLED_FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_enabled, android.R.attr.state_focused, android.R.attr.state_selected, android.R.attr.state_window_focused};
  protected static final int[] PRESSED_ENABLED_FOCUSED_SELECTED_WINDOW_FOCUSED_STATE_SET = {android.R.attr.state_pressed, android.R.attr.state_enabled, android.R.attr.state_focused, android.R.attr.state_selected, android.R.attr.state_window_focused};
  public static final android.util.Property<View, Float> ALPHA = new android.util.FloatProperty<View>("alpha") {
    @Override public void setValue(View v, float value) { v.setAlpha(value); }
    @Override public Float get(View v) { return v.getAlpha(); }
  };
  public static final android.util.Property<View, Float> TRANSLATION_X = new android.util.FloatProperty<View>("translationX") {
    @Override public void setValue(View v, float value) { v.setTranslationX(value); }
    @Override public Float get(View v) { return v.getTranslationX(); }
  };
  public static final android.util.Property<View, Float> TRANSLATION_Y = new android.util.FloatProperty<View>("translationY") {
    @Override public void setValue(View v, float value) { v.setTranslationY(value); }
    @Override public Float get(View v) { return v.getTranslationY(); }
  };
  public static final android.util.Property<View, Float> TRANSLATION_Z = new android.util.FloatProperty<View>("translationZ") {
    @Override public void setValue(View v, float value) { v.setTranslationZ(value); }
    @Override public Float get(View v) { return v.getTranslationZ(); }
  };
  public static final android.util.Property<View, Float> X = new android.util.FloatProperty<View>("x") {
    @Override public void setValue(View v, float value) { v.setX(value); }
    @Override public Float get(View v) { return v.getX(); }
  };
  public static final android.util.Property<View, Float> Y = new android.util.FloatProperty<View>("y") {
    @Override public void setValue(View v, float value) { v.setY(value); }
    @Override public Float get(View v) { return v.getY(); }
  };
  public static final android.util.Property<View, Float> Z = new android.util.FloatProperty<View>("z") {
    @Override public void setValue(View v, float value) { v.setZ(value); }
    @Override public Float get(View v) { return v.getZ(); }
  };
  public static final android.util.Property<View, Float> ROTATION = new android.util.FloatProperty<View>("rotation") {
    @Override public void setValue(View v, float value) { v.setRotation(value); }
    @Override public Float get(View v) { return v.getRotation(); }
  };
  public static final android.util.Property<View, Float> ROTATION_X = new android.util.FloatProperty<View>("rotationX") {
    @Override public void setValue(View v, float value) { v.setRotationX(value); }
    @Override public Float get(View v) { return v.getRotationX(); }
  };
  public static final android.util.Property<View, Float> ROTATION_Y = new android.util.FloatProperty<View>("rotationY") {
    @Override public void setValue(View v, float value) { v.setRotationY(value); }
    @Override public Float get(View v) { return v.getRotationY(); }
  };
  public static final android.util.Property<View, Float> SCALE_X = new android.util.FloatProperty<View>("scaleX") {
    @Override public void setValue(View v, float value) { v.setScaleX(value); }
    @Override public Float get(View v) { return v.getScaleX(); }
  };
  public static final android.util.Property<View, Float> SCALE_Y = new android.util.FloatProperty<View>("scaleY") {
    @Override public void setValue(View v, float value) { v.setScaleY(value); }
    @Override public Float get(View v) { return v.getScaleY(); }
  };

  // ---------------------------------------------------------------- listeners
  public interface OnClickListener { void onClick(View v); }
  public interface OnLongClickListener { boolean onLongClick(View v); }
  public interface OnTouchListener { boolean onTouch(View v, MotionEvent event); }
  public interface OnFocusChangeListener { void onFocusChange(View v, boolean hasFocus); }
  public interface OnKeyListener { boolean onKey(View v, int keyCode, KeyEvent event); }
  public interface OnCreateContextMenuListener { void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info); }
  public interface OnLayoutChangeListener { void onLayoutChange(View v, int l, int t, int r, int b, int ol, int ot, int or, int ob); }
  public interface OnAttachStateChangeListener { void onViewAttachedToWindow(View v); void onViewDetachedFromWindow(View v); }
  public interface OnSystemUiVisibilityChangeListener { void onSystemUiVisibilityChange(int visibility); }
  public interface OnGenericMotionListener { boolean onGenericMotion(View v, MotionEvent e); }
  public interface OnHoverListener { boolean onHover(View v, MotionEvent e); }
  public interface OnDragListener { boolean onDrag(View v, Object e); }

  public static class MeasureSpec {
    static final int MODE_SHIFT = 30, MODE_MASK = 0x3 << MODE_SHIFT;
    public static final int UNSPECIFIED = 0, EXACTLY = 1 << MODE_SHIFT, AT_MOST = 2 << MODE_SHIFT;
    public static int makeMeasureSpec(int size, int mode) { return (size & ~MODE_MASK) | (mode & MODE_MASK); }
    public static int getMode(int spec) { return spec & MODE_MASK; }
    public static int getSize(int spec) { return spec & ~MODE_MASK; }
    public static String toString(int s) { int m = getMode(s); return "MeasureSpec: " + (m == EXACTLY ? "EXACTLY " : m == AT_MOST ? "AT_MOST " : "UNSPECIFIED ") + getSize(s); }
  }

  /** Per-window state shared by every attached view (Android's View.AttachInfo). */
  public static final class AttachInfo {
    public final a2w.ViewRoot root;
    public final Handler handler;
    public final ViewTreeObserver treeObserver = new ViewTreeObserver();
    public View rootView;
    public long drawingTime;
    public int windowVisibility = VISIBLE;
    public boolean hasWindowFocus;
    public boolean keepScreenOn;
    public float applicationScale = 1;
    final Rect tmpRect = new Rect();
    final Transformation tmpTransformation = new Transformation();
    public AttachInfo(a2w.ViewRoot r, Handler h) { root = r; handler = h; }
  }

  // ---------------------------------------------------------------- flags
  static final int ENABLED = 0, DISABLED = 0x20, ENABLED_MASK = 0x20;
  static final int FOCUSABLE = 1, FOCUSABLE_MASK = 0x11;
  static final int CLICKABLE = 0x4000, LONG_CLICKABLE = 0x200000, FOCUSABLE_IN_TOUCH_MODE = 0x40000, WILL_NOT_DRAW = 0x80,
      DUPLICATE_PARENT_STATE = 0x400000, DRAWING_CACHE_ENABLED = 0x8000, SAVE_DISABLED = 0x10000, HAPTIC = 0x10000000, SOUND = 0x08000000;

  static final int PFLAG_WANTS_FOCUS = 1, PFLAG_FOCUSED = 2, PFLAG_SELECTED = 4, PFLAG_IS_ROOT_NAMESPACE = 8, PFLAG_HAS_BOUNDS = 0x10,
      PFLAG_DRAWN = 0x20, PFLAG_DRAW_ANIMATION = 0x40, PFLAG_SKIP_DRAW = 0x80, PFLAG_DRAWABLE_STATE_DIRTY = 0x400,
      PFLAG_MEASURED_DIMENSION_SET = 0x800, PFLAG_FORCE_LAYOUT = 0x1000, PFLAG_LAYOUT_REQUIRED = 0x2000, PFLAG_PRESSED = 0x4000,
      PFLAG_ANIMATION_STARTED = 0x10000, PFLAG_ACTIVATED = 0x40000000, PFLAG_CANCEL_NEXT_UP_EVENT = 0x04000000, PFLAG_PREPRESSED = 0x02000000;

  int mViewFlags = SOUND | HAPTIC | FOCUSABLE_AUTO;
  int mPrivateFlags;

  // ---------------------------------------------------------------- state
  protected Context mContext;
  final Resources mResources;
  int mID = NO_ID;
  Object mTag;
  SparseArray<Object> mKeyedTags;
  protected ViewParent mParent;
  AttachInfo mAttachInfo;
  protected int mLeft, mTop, mRight, mBottom;
  protected int mScrollX, mScrollY;
  protected int mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom;
  int mUserPaddingLeft, mUserPaddingRight, mUserPaddingBottom;
  int mMeasuredWidth, mMeasuredHeight;
  int mOldWidthMeasureSpec = Integer.MIN_VALUE, mOldHeightMeasureSpec = Integer.MIN_VALUE;
  int mMinWidth, mMinHeight;
  protected ViewGroup.LayoutParams mLayoutParams;
  Drawable mBackground;
  boolean mBackgroundSizeChanged;
  int[] mDrawableState;
  protected Animation mCurrentAnimation;
  float mAlpha = 1, mTranslationX, mTranslationY, mScaleX = 1, mScaleY = 1, mRotation, mPivotX, mPivotY;
  boolean mPivotSet;
  Matrix mTransformMatrix;
  int mWindowAttachCount;
  int mDrawingCacheBackgroundColor;
  boolean mHasPerformedLongPress;
  int mTouchSlop = 12;
  int mScrollBarStyle, mOverScrollMode = OVER_SCROLL_IF_CONTENT_SCROLLS;
  boolean mVerticalScrollBarEnabled, mHorizontalScrollBarEnabled;

  OnClickListener mOnClickListener;
  OnLongClickListener mOnLongClickListener;
  OnTouchListener mOnTouchListener;
  OnFocusChangeListener mOnFocusChangeListener;
  OnKeyListener mOnKeyListener;
  OnCreateContextMenuListener mOnCreateContextMenuListener;
  ArrayList<OnLayoutChangeListener> mOnLayoutChangeListeners;
  ArrayList<OnAttachStateChangeListener> mOnAttachStateChangeListeners;
  ViewTreeObserver mFloatingTreeObserver;
  ArrayList<Runnable> mPendingPosts;

  private CheckForLongPress mPendingCheckForLongPress;
  private CheckForTap mPendingCheckForTap;
  private UnsetPressedState mUnsetPressedState;
  private PerformClick mPerformClick;

  private static int sNextGeneratedId = 1;

  // ---------------------------------------------------------------- construction
  public View(Context context) {
    mContext = context;
    mResources = context != null ? context.getResources() : null;
    mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
  }
  public View(Context context, AttributeSet attrs) { this(context, attrs, 0); }
  /** web: the attribute set the view was constructed with (widgets read their own attributes from it after super()) */
  protected AttributeSet mCtorAttrs;
  public View(Context context, AttributeSet attrs, int defStyle) {
    this(context);
    mCtorAttrs = attrs;
    if (attrs instanceof Attrs) {
      Attrs a = (Attrs) attrs;
      // "?attr/x" values resolve against this view's context theme; a default style the theme names
      // (the widget's defStyleAttr: textViewStyle, buttonStyle, or an AppCompat attribute) comes before the widget's own
      if (a.theme == null && context != null) a.theme = context.getTheme();
      int ds = defStyle != 0 ? defStyle : implicitStyleAttr();
      if (ds != 0) a.withThemeStyle(ds);
      applyAttrs(a);
    }
  }
  public View(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) { this(context, attrs, defStyleAttr); }
  /** the theme attribute naming this platform widget's default style (TextView: textViewStyle...) */
  private int implicitStyleAttr() {
    for (Class<?> k = getClass(); k != null && k != View.class; k = k.getSuperclass()) {
      String n = k.getName();
      if (!n.startsWith("android.widget.")) continue;
      switch (n.substring(15)) {
        case "TextView": return android.R.attr.textViewStyle;
        case "Button": return android.R.attr.buttonStyle;
        case "EditText": return android.R.attr.editTextStyle;
        case "CheckBox": return android.R.attr.checkboxStyle;
        case "RadioButton": return android.R.attr.radioButtonStyle;
        case "ImageButton": return android.R.attr.imageButtonStyle;
        case "ToggleButton": return android.R.attr.buttonStyleToggle;
        case "SeekBar": return android.R.attr.seekBarStyle;
        case "Spinner": return android.R.attr.spinnerStyle;
        case "ListView": return android.R.attr.listViewStyle;
        case "GridView": return android.R.attr.gridViewStyle;
        case "ScrollView": return android.R.attr.scrollViewStyle;
        case "HorizontalScrollView": return android.R.attr.horizontalScrollViewStyle;
        case "AutoCompleteTextView": return android.R.attr.autoCompleteTextViewStyle;
        case "RatingBar": return android.R.attr.ratingBarStyle;
        case "ImageView": case "LinearLayout": case "FrameLayout": case "RelativeLayout": return 0;
        default: continue;
      }
    }
    return 0;
  }

  /** Reads the View attributes (android:id, background, padding, visibility, ...). */
  void applyAttrs(Attrs a) {
    String v;
    if ((v = a.a("id")) != null) mID = Values.refId(v);
    if ((v = a.a("tag")) != null) mTag = Values.isRef(v) ? mResources.getString(Values.refId(v)) : v;
    if ((v = a.a("backgroundTint")) != null) mBackgroundTint = tintList(v);
    if ((v = a.a("backgroundTintMode")) != null) mBackgroundTintMode = a2w.Drawables.porterDuffMode(v);
    if ((v = a.a("background")) != null) {
      if (v.startsWith("#")) setBackgroundDrawable(new ColorDrawable(Values.parseColor(v)));
      else if (Values.isRef(v)) { int id = Values.refId(v); if (id != 0) setBackgroundDrawable(mResources.getDrawable(id, mContext != null ? mContext.getTheme() : null)); }
    }
    if (!(this instanceof android.widget.FrameLayout) && (v = a.a("foreground")) != null) {
      if (v.startsWith("#")) setForeground(new ColorDrawable(Values.parseColor(v)));
      else if (Values.isRef(v)) { int id = Values.refId(v); if (id != 0) { try { setForeground(mResources.getDrawable(id, mContext != null ? mContext.getTheme() : null)); } catch (RuntimeException e) { /* none */ } } }
      if ((v = a.a("foregroundGravity")) != null) mViewForegroundGravity = Values.gravity(v);
    }
    int pad = -1;
    if ((v = a.a("padding")) != null) pad = (int) dim(v);
    int pl = pad >= 0 ? pad : mPaddingLeft, pt = pad >= 0 ? pad : mPaddingTop, pr = pad >= 0 ? pad : mPaddingRight, pb = pad >= 0 ? pad : mPaddingBottom;
    if ((v = a.a("paddingLeft")) != null) pl = (int) dim(v);
    if ((v = a.a("paddingTop")) != null) pt = (int) dim(v);
    if ((v = a.a("paddingRight")) != null) pr = (int) dim(v);
    if ((v = a.a("paddingBottom")) != null) pb = (int) dim(v);
    if (pad >= 0 || a.a("paddingLeft") != null || a.a("paddingTop") != null || a.a("paddingRight") != null || a.a("paddingBottom") != null || mBackground != null)
      setPadding(pl, pt, pr, pb);
    if ((v = a.a("visibility")) != null) {
      int vis = v.equals("gone") || v.equals("2") ? GONE : v.equals("invisible") || v.equals("1") ? INVISIBLE : VISIBLE;
      mViewFlags = (mViewFlags & ~VISIBILITY_MASK) | vis;
    }
    if ((v = a.a("clickable")) != null) setFlag(CLICKABLE, v.equals("true"));
    if ((v = a.a("onClick")) != null && !v.isEmpty()) {
      // android:onClick="name": a public method name(View) of the context (an activity), found by reflection
      final String handler = v;
      setOnClickListener(view -> {
        for (android.content.Context k = getContext(); k != null; ) {
          if (a2w.Reflection.call(k, handler, view)) return;
          k = k instanceof android.content.ContextWrapper ? ((android.content.ContextWrapper) k).getBaseContext() : null;
        }
        throw new IllegalStateException("Could not find method " + handler + "(View) in the context for android:onClick");
      });
    }
    if ((v = a.a("longClickable")) != null) setFlag(LONG_CLICKABLE, v.equals("true"));
    if ((v = a.a("focusable")) != null) setFocusable(v.equals("auto") || v.equals("16") || v.equals("0x10") ? FOCUSABLE_AUTO : v.equals("true") ? FOCUSABLE : NOT_FOCUSABLE);
    if ((v = a.a("focusableInTouchMode")) != null) setFocusableInTouchMode(v.equals("true"));
    if ((v = a.a("focusedByDefault")) != null) mFocusedByDefault = v.equals("true");
    if ((v = a.a("nextFocusLeft")) != null && Values.isRef(v)) mNextFocusLeftId = Values.refId(v);
    if ((v = a.a("nextFocusRight")) != null && Values.isRef(v)) mNextFocusRightId = Values.refId(v);
    if ((v = a.a("nextFocusUp")) != null && Values.isRef(v)) mNextFocusUpId = Values.refId(v);
    if ((v = a.a("nextFocusDown")) != null && Values.isRef(v)) mNextFocusDownId = Values.refId(v);
    if ((v = a.a("nextFocusForward")) != null && Values.isRef(v)) mNextFocusForwardId = Values.refId(v);
    if ((v = a.a("defaultFocusHighlightEnabled")) != null) mDefaultFocusHighlightEnabled = !v.equals("false");
    if ((v = a.a("enabled")) != null && v.equals("false")) mViewFlags |= DISABLED;
    if ((v = a.a("duplicateParentState")) != null) setFlag(DUPLICATE_PARENT_STATE, v.equals("true"));
    if ((v = a.a("minWidth")) != null) mMinWidth = (int) dim(v);
    if ((v = a.a("minHeight")) != null) mMinHeight = (int) dim(v);
    if ((v = a.a("soundEffectsEnabled")) != null) setFlag(SOUND, v.equals("true"));
    if ((v = a.a("keepScreenOn")) != null) setFlag(KEEP_SCREEN_ON, v.equals("true"));
    if ((v = a.a("scrollX")) != null) mScrollX = (int) dim(v);
    if ((v = a.a("scrollY")) != null) mScrollY = (int) dim(v);
    if ((v = a.a("alpha")) != null) mAlpha = Values.parseFloat(v, 1);
    if ((v = a.a("contentDescription")) != null) {}
    if ((v = a.a("scrollbars")) != null) { mVerticalScrollBarEnabled = v.contains("vertical"); mHorizontalScrollBarEnabled = v.contains("horizontal"); }
  }
  protected float dim(String v) { return Values.isRef(v) ? mResources.getDimension(Values.refId(v)) : Values.dimension(v); }
  void setFlag(int flag, boolean on) { setFlags(on ? flag : 0, flag); }
  /** sets the masked flags; a FOCUSABLE_AUTO view is focusable exactly when it is clickable; a view that stops being focusable loses focus */
  void setFlags(int flags, int mask) {
    int old = mViewFlags;
    mViewFlags = (mViewFlags & ~mask) | (flags & mask);
    if ((mViewFlags & FOCUSABLE_AUTO) != 0 && ((mViewFlags ^ old) & (FOCUSABLE_MASK | CLICKABLE)) != 0)
      mViewFlags = (mViewFlags & ~FOCUSABLE) | ((mViewFlags & CLICKABLE) != 0 ? FOCUSABLE : 0);
    if ((old & FOCUSABLE) != 0 && (mViewFlags & FOCUSABLE) == 0 && (mPrivateFlags & PFLAG_FOCUSED) != 0) clearFocus();
  }

  public static int generateViewId() { return 0x00ff0000 + sNextGeneratedId++; }

  // ---------------------------------------------------------------- basic properties
  public final Context getContext() { return mContext; }
  public Resources getResources() { return mResources; }
  public int getId() { return mID; }
  public void setId(int id) { mID = id; }
  public Object getTag() { return mTag; }
  public void setTag(Object t) { mTag = t; }
  public Object getTag(int key) { return mKeyedTags == null ? null : mKeyedTags.get(key); }
  public void setTag(int key, Object t) { if (mKeyedTags == null) mKeyedTags = new SparseArray<>(); mKeyedTags.put(key, t); }
  public final ViewParent getParent() { return mParent; }
  public View getRootView() {
    if (mAttachInfo != null && mAttachInfo.rootView != null) return mAttachInfo.rootView;
    View v = this;
    while (v.mParent instanceof View) v = (View) v.mParent;
    return v;
  }
  public ViewGroup.LayoutParams getLayoutParams() { return mLayoutParams; }
  public void setLayoutParams(ViewGroup.LayoutParams p) {
    if (p == null) throw new NullPointerException("Layout parameters cannot be null");
    mLayoutParams = p;
    if (mParent instanceof ViewGroup) ((ViewGroup) mParent).onSetLayoutParams(this, p);
    requestLayout();
  }
  /** web: assign layout params without requesting a layout (adapter views) */
  public void setLayoutParams2(ViewGroup.LayoutParams p) { mLayoutParams = p; }
  public final int getLeft() { return mLeft; }
  public final int getTop() { return mTop; }
  public final int getRight() { return mRight; }
  public final int getBottom() { return mBottom; }
  public final int getWidth() { return mRight - mLeft; }
  public final int getHeight() { return mBottom - mTop; }
  public void setLeft(int l) { setFrame(l, mTop, mRight, mBottom); }
  public void setTop(int t) { setFrame(mLeft, t, mRight, mBottom); }
  public void setRight(int r) { setFrame(mLeft, mTop, r, mBottom); }
  public void setBottom(int b) { setFrame(mLeft, mTop, mRight, b); }
  public void getHitRect(Rect r) { r.set(mLeft, mTop, mRight, mBottom); }
  public void getDrawingRect(Rect r) { r.set(mScrollX, mScrollY, mScrollX + getWidth(), mScrollY + getHeight()); }
  public final boolean getLocalVisibleRect(Rect r) { r.set(0, 0, getWidth(), getHeight()); return true; }
  public boolean getGlobalVisibleRect(Rect r) { int[] l = new int[2]; getLocationInWindow(l); r.set(l[0], l[1], l[0] + getWidth(), l[1] + getHeight()); return true; }
  public boolean getGlobalVisibleRect(Rect r, Point off) { boolean b = getGlobalVisibleRect(r); if (off != null) off.set(r.left, r.top); return b; }
  public void getLocationOnScreen(int[] loc) {
    getLocationInWindow(loc);
    if (mAttachInfo != null) { loc[0] += mAttachInfo.root.windowLeft(); loc[1] += mAttachInfo.root.windowTop(); }
  }
  public void getLocationInWindow(int[] loc) {
    float x = mLeft + mTranslationX, y = mTop + mTranslationY;
    ViewParent p = mParent;
    while (p instanceof View) {
      View v = (View) p;
      x += v.mLeft - v.mScrollX + v.mTranslationX;
      y += v.mTop - v.mScrollY + v.mTranslationY;
      p = v.mParent;
    }
    loc[0] = (int) (x + 0.5f); loc[1] = (int) (y + 0.5f);
  }
  public void offsetTopAndBottom(int o) { if (o != 0) { mTop += o; mBottom += o; invalidate(); } }
  public void offsetLeftAndRight(int o) { if (o != 0) { mLeft += o; mRight += o; invalidate(); } }

  // ---------------------------------------------------------------- padding
  public void setPadding(int l, int t, int r, int b) {
    boolean changed = mPaddingLeft != l || mPaddingTop != t || mPaddingRight != r || mPaddingBottom != b;
    mUserPaddingLeft = l; mUserPaddingRight = r; mUserPaddingBottom = b;
    mPaddingLeft = l; mPaddingTop = t; mPaddingRight = r; mPaddingBottom = b;
    if (changed) requestLayout();
  }
  public void setPaddingRelative(int s, int t, int e, int b) { setPadding(s, t, e, b); }
  public int getPaddingLeft() { return mPaddingLeft; }
  public int getPaddingTop() { return mPaddingTop; }
  public int getPaddingRight() { return mPaddingRight; }
  public int getPaddingBottom() { return mPaddingBottom; }
  public int getPaddingStart() { return mPaddingLeft; }
  public int getPaddingEnd() { return mPaddingRight; }

  // ---------------------------------------------------------------- visibility & enabled
  public int getVisibility() { return mViewFlags & VISIBILITY_MASK; }
  public void setVisibility(int v) {
    int old = mViewFlags & VISIBILITY_MASK;
    if (old == v) return;
    mViewFlags = (mViewFlags & ~VISIBILITY_MASK) | (v & VISIBILITY_MASK);
    if (v != VISIBLE) { if (hasFocus()) clearFocus(); }
    if (old == GONE || v == GONE) { requestLayout(); }
    invalidateParent();
    if (mParent instanceof ViewGroup) ((ViewGroup) mParent).onChildVisibilityChanged(this, old, v);
    onVisibilityChanged(this, v);
    if (mBackground != null) mBackground.setVisible(v == VISIBLE, false);
  }
  protected void onVisibilityChanged(View changed, int visibility) {}
  void dispatchVisibilityChanged(View changed, int visibility) { onVisibilityChanged(changed, visibility); }
  protected void onWindowVisibilityChanged(int visibility) {}
  public void dispatchWindowVisibilityChanged(int v) { onWindowVisibilityChanged(v); }
  public int getWindowVisibility() { return mAttachInfo != null ? mAttachInfo.windowVisibility : GONE; }
  public boolean isShown() {
    View v = this;
    while (true) {
      if ((v.mViewFlags & VISIBILITY_MASK) != VISIBLE) return false;
      ViewParent p = v.mParent;
      if (p == null) return false;
      if (!(p instanceof View)) return true;
      v = (View) p;
    }
  }
  public boolean isEnabled() { return (mViewFlags & ENABLED_MASK) == ENABLED; }
  public void setEnabled(boolean e) {
    if (e == isEnabled()) return;
    setFlag(DISABLED, !e);
    refreshDrawableState();
    invalidate();
  }
  public boolean isClickable() { return (mViewFlags & CLICKABLE) != 0; }
  public void setClickable(boolean c) { setFlag(CLICKABLE, c); }
  public boolean isLongClickable() { return (mViewFlags & LONG_CLICKABLE) != 0; }
  public void setLongClickable(boolean c) { setFlag(LONG_CLICKABLE, c); }
  public final boolean isFocusable() { return (mViewFlags & FOCUSABLE) != 0; }
  public void setFocusable(boolean f) { setFocusable(f ? FOCUSABLE : NOT_FOCUSABLE); }
  public final boolean isFocusableInTouchMode() { return (mViewFlags & FOCUSABLE_IN_TOUCH_MODE) != 0; }
  public void setFocusableInTouchMode(boolean f) { setFlags(f ? FOCUSABLE_IN_TOUCH_MODE : 0, FOCUSABLE_IN_TOUCH_MODE); if (f) setFlags(FOCUSABLE, FOCUSABLE_MASK); }
  public void setDuplicateParentStateEnabled(boolean e) { setFlag(DUPLICATE_PARENT_STATE, e); }
  public boolean isDuplicateParentStateEnabled() { return (mViewFlags & DUPLICATE_PARENT_STATE) != 0; }
  public void setWillNotDraw(boolean w) { setFlag(WILL_NOT_DRAW, w); }
  public boolean willNotDraw() { return (mViewFlags & WILL_NOT_DRAW) != 0; }
  public void setWillNotCacheDrawing(boolean w) {}
  public boolean willNotCacheDrawing() { return true; }
  public void setSoundEffectsEnabled(boolean e) { setFlag(SOUND, e); }
  public boolean isSoundEffectsEnabled() { return (mViewFlags & SOUND) != 0; }
  public void setHapticFeedbackEnabled(boolean e) { setFlag(HAPTIC, e); }
  public boolean isHapticFeedbackEnabled() { return (mViewFlags & HAPTIC) != 0; }
  public boolean performHapticFeedback(int c) { return false; }
  public boolean performHapticFeedback(int c, int f) { return false; }
  public void playSoundEffect(int s) {}
  public void setKeepScreenOn(boolean k) { setFlag(KEEP_SCREEN_ON, k); }
  public boolean getKeepScreenOn() { return (mViewFlags & KEEP_SCREEN_ON) != 0; }
  public void setSaveEnabled(boolean e) { setFlag(SAVE_DISABLED, !e); }
  public boolean isSaveEnabled() { return (mViewFlags & SAVE_DISABLED) == 0; }
  public void setContentDescription(CharSequence d) {}
  public CharSequence getContentDescription() { return null; }
  public void setDrawingCacheEnabled(boolean e) { setFlag(DRAWING_CACHE_ENABLED, e); }
  public boolean isDrawingCacheEnabled() { return (mViewFlags & DRAWING_CACHE_ENABLED) != 0; }
  public void setDrawingCacheQuality(int q) {}
  public void setDrawingCacheBackgroundColor(int c) { mDrawingCacheBackgroundColor = c; }
  public void setAnimationCacheEnabled(boolean e) {}
  public void setAlwaysDrawnWithCacheEnabled(boolean e) {}
  public void buildDrawingCache() {}
  public void buildDrawingCache(boolean auto) {}
  public void destroyDrawingCache() {}
  public Bitmap getDrawingCache() { return getDrawingCache(false); }
  public Bitmap getDrawingCache(boolean auto) {
    int w = getWidth(), h = getHeight();
    if (w <= 0 || h <= 0) return null;
    Bitmap b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    Canvas c = new Canvas(b);
    c.translate(-mScrollX, -mScrollY);
    draw(c);
    return b;
  }
  public void setLayerType(int t, Paint p) {}
  public int getLayerType() { return LAYER_TYPE_NONE; }
  public void setVerticalScrollBarEnabled(boolean e) { mVerticalScrollBarEnabled = e; }
  public boolean isVerticalScrollBarEnabled() { return mVerticalScrollBarEnabled; }
  public void setHorizontalScrollBarEnabled(boolean e) { mHorizontalScrollBarEnabled = e; }
  public boolean isHorizontalScrollBarEnabled() { return mHorizontalScrollBarEnabled; }
  public void setScrollBarStyle(int s) { mScrollBarStyle = s; }
  public int getScrollBarStyle() { return mScrollBarStyle; }
  public void setScrollbarFadingEnabled(boolean f) {}
  public void setVerticalFadingEdgeEnabled(boolean e) {}
  public void setHorizontalFadingEdgeEnabled(boolean e) {}
  public void setFadingEdgeLength(int l) {}
  public void setOverScrollMode(int m) { mOverScrollMode = m; }
  public int getOverScrollMode() { return mOverScrollMode; }
  public void setSystemUiVisibility(int v) {}
  public int getSystemUiVisibility() { return 0; }
  public void setOnSystemUiVisibilityChangeListener(OnSystemUiVisibilityChangeListener l) {}
  public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0, IMPORTANT_FOR_ACCESSIBILITY_YES = 1,
      IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
  int mImportantForAccessibility, mAccessibilityLiveRegion;
  boolean mFitsSystemWindows, mSaveFromParent = true, mSaveEnabled = true;
  AccessibilityDelegate mAccessibilityDelegate;
  public void setImportantForAccessibility(int m) { mImportantForAccessibility = m; }
  public int getImportantForAccessibility() { return mImportantForAccessibility; }
  public boolean isImportantForAccessibility() { return mImportantForAccessibility != IMPORTANT_FOR_ACCESSIBILITY_NO && mImportantForAccessibility != IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS; }
  public void setAccessibilityLiveRegion(int mode) { mAccessibilityLiveRegion = mode; }
  public int getAccessibilityLiveRegion() { return mAccessibilityLiveRegion; }
  public void setAccessibilityDelegate(AccessibilityDelegate d) { mAccessibilityDelegate = d; }
  public AccessibilityDelegate getAccessibilityDelegate() { return mAccessibilityDelegate; }
  public void sendAccessibilityEvent(int eventType) {}
  public void announceForAccessibility(CharSequence text) {}
  public boolean isLaidOut() { return mLeft != mRight || mTop != mBottom || mLaidOutOnce; }
  boolean mLaidOutOnce;
  public boolean getFitsSystemWindows() { return mFitsSystemWindows; }
  public boolean fitsSystemWindows() { return mFitsSystemWindows; }
  public void requestFitSystemWindows() {}
  // ---- window insets (zero on the page; apps may replace them)
  public interface OnApplyWindowInsetsListener { WindowInsets onApplyWindowInsets(View v, WindowInsets insets); }
  OnApplyWindowInsetsListener mOnApplyWindowInsetsListener;
  public void setOnApplyWindowInsetsListener(OnApplyWindowInsetsListener l) { mOnApplyWindowInsetsListener = l; }
  public WindowInsets onApplyWindowInsets(WindowInsets insets) {
    if (getFitsSystemWindows() && insets.hasSystemWindowInsets()) {
      setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(), insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
      return insets.consumeSystemWindowInsets();
    }
    return insets;
  }
  public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
    return mOnApplyWindowInsetsListener != null ? mOnApplyWindowInsetsListener.onApplyWindowInsets(this, insets) : onApplyWindowInsets(insets);
  }
  public WindowInsets getRootWindowInsets() { return mAttachInfo != null ? WindowInsets.zero() : null; }
  public WindowInsets computeSystemWindowInsets(WindowInsets in, android.graphics.Rect outLocalInsets) {
    if (outLocalInsets != null) outLocalInsets.set(0, 0, 0, 0);
    return in;
  }
  public void requestApplyInsets() {
    if (mAttachInfo != null) { final View root = getRootView(); post(() -> root.dispatchApplyWindowInsets(WindowInsets.zero())); }
  }
  public void setSaveFromParentEnabled(boolean enabled) { mSaveFromParent = enabled; }
  public boolean isSaveFromParentEnabled() { return mSaveFromParent; }
  public int getWindowSystemUiVisibility() { return 0; }
  public void dispatchSystemUiVisibilityChanged(int visibility) {}
  /** android.view.View.AccessibilityDelegate: the accessibility hooks (no accessibility services in the page) */
  public static class AccessibilityDelegate {
    public AccessibilityDelegate() {}
    public void sendAccessibilityEvent(View host, int eventType) {}
    public boolean performAccessibilityAction(View host, int action, android.os.Bundle args) { return false; }
    public void sendAccessibilityEventUnchecked(View host, android.view.accessibility.AccessibilityEvent event) {}
    public boolean dispatchPopulateAccessibilityEvent(View host, android.view.accessibility.AccessibilityEvent event) { return false; }
    public void onPopulateAccessibilityEvent(View host, android.view.accessibility.AccessibilityEvent event) {}
    public void onInitializeAccessibilityEvent(View host, android.view.accessibility.AccessibilityEvent event) {}
    public void onInitializeAccessibilityNodeInfo(View host, android.view.accessibility.AccessibilityNodeInfo info) {}
    public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, android.view.accessibility.AccessibilityEvent event) { return true; }
    public android.view.accessibility.AccessibilityNodeProvider getAccessibilityNodeProvider(View host) { return null; }
  }
  public void setLayoutDirection(int d) {}
  public int getLayoutDirection() { return LAYOUT_DIRECTION_LTR; }
  public void setFitsSystemWindows(boolean f) { mFitsSystemWindows = f; }
  public boolean isInEditMode() { return false; }
  public boolean isHardwareAccelerated() { return false; }
  public boolean isOpaque() { return mBackground != null && mBackground.getOpacity() == PixelFormat.OPAQUE; }
  /** touch mode: entered by a touch (or click, or wheel), left by a navigation or typing key; global, as on a device */
  public boolean isInTouchMode() { return a2w.ViewRoot.touchMode; }
  public boolean isLayoutRtl() { return getLayoutDirection() == LAYOUT_DIRECTION_RTL; }
  public boolean isAttachedToWindow() { return mAttachInfo != null; }
  public android.os.IBinder getWindowToken() { return mAttachInfo != null ? mAttachInfo.root.token : null; }
  public android.os.IBinder getApplicationWindowToken() { return getWindowToken(); }
  public Handler getHandler() { return mAttachInfo != null ? mAttachInfo.handler : null; }
  public Display getDisplay() { return Display.DEFAULT; }

  // ---------------------------------------------------------------- property transforms (API 11)
  public float getAlpha() { return mAlpha; }
  public void setAlpha(float a) { if (mAlpha != a) { mAlpha = a; invalidateParent(); } }
  public float getTranslationX() { return mTranslationX; }
  public void setTranslationX(float v) { if (mTranslationX != v) { mTranslationX = v; transformChanged(); } }
  public float getTranslationY() { return mTranslationY; }
  public void setTranslationY(float v) { if (mTranslationY != v) { mTranslationY = v; transformChanged(); } }
  public float getScaleX() { return mScaleX; }
  public void setScaleX(float v) { if (mScaleX != v) { mScaleX = v; transformChanged(); } }
  public float getScaleY() { return mScaleY; }
  public void setScaleY(float v) { if (mScaleY != v) { mScaleY = v; transformChanged(); } }
  public float getRotation() { return mRotation; }
  public void setRotation(float v) { if (mRotation != v) { mRotation = v; transformChanged(); } }
  public float getPivotX() { return mPivotSet ? mPivotX : getWidth() / 2f; }
  public void setPivotX(float v) { mPivotSet = true; mPivotX = v; transformChanged(); }
  public float getPivotY() { return mPivotSet ? mPivotY : getHeight() / 2f; }
  public void setPivotY(float v) { mPivotSet = true; mPivotY = v; transformChanged(); }
  public float getX() { return mLeft + mTranslationX; }
  public void setX(float x) { setTranslationX(x - mLeft); }
  public float getY() { return mTop + mTranslationY; }
  public void setY(float y) { setTranslationY(y - mTop); }
  // depth: kept (no shadows); 3D rotation: a Camera like RenderNode's, drawn as the nearest affine map
  float mElevation, mTranslationZ, mRotationX, mRotationY;
  /** the camera's z location in inches (RenderNode's), negative: 8 inches in front by default */
  float mCameraZ = -8;
  public float getElevation() { return mElevation; }
  public void setElevation(float e) { mElevation = e; }
  public float getTranslationZ() { return mTranslationZ; }
  public void setTranslationZ(float z) { mTranslationZ = z; }
  public float getZ() { return mElevation + mTranslationZ; }
  public void setZ(float z) { setTranslationZ(z - mElevation); }
  public float getRotationX() { return mRotationX; }
  public void setRotationX(float r) { if (mRotationX != r) { mRotationX = r; transformChanged(); } }
  public float getRotationY() { return mRotationY; }
  public void setRotationY(float r) { if (mRotationY != r) { mRotationY = r; transformChanged(); } }
  private float dpi() { return mResources != null ? mResources.getDisplayMetrics().densityDpi : android.util.DisplayMetrics.DENSITY_DEFAULT; }
  public float getCameraDistance() { return -(mCameraZ * dpi()); }
  public void setCameraDistance(float d) { mCameraZ = -Math.abs(d) / dpi(); transformChanged(); }
  public void setOutlineProvider(ViewOutlineProvider p) {}
  public ViewOutlineProvider getOutlineProvider() { return ViewOutlineProvider.BACKGROUND; }
  public void setClipToOutline(boolean c) {}
  public boolean getClipToOutline() { return false; }
  public void invalidateOutline() {}
  public void setStateListAnimator(android.animation.StateListAnimator a) {}
  public android.animation.StateListAnimator getStateListAnimator() { return null; }
  private void transformChanged() { mTransformMatrix = null; invalidateParent(); }
  boolean hasTransform() { return mTranslationX != 0 || mTranslationY != 0 || mScaleX != 1 || mScaleY != 1 || mRotation != 0 || mRotationX != 0 || mRotationY != 0; }
  public Matrix getMatrix() {
    if (mTransformMatrix == null) {
      Matrix m = new Matrix();
      float px = getPivotX(), py = getPivotY();
      if (mRotationX == 0 && mRotationY == 0) {
        m.setTranslate(mTranslationX, mTranslationY);
        m.preRotate(mRotation, px, py);
        m.preScale(mScaleX, mScaleY, px, py);
      } else {
        // RenderProperties::updateMatrix
        m.preScale(mScaleX, mScaleY, px, py);
        android.graphics.Camera cam = new android.graphics.Camera();
        cam.setLocation(0, 0, mCameraZ);
        cam.rotateX(mRotationX);
        cam.rotateY(mRotationY);
        cam.rotateZ(-mRotation);
        Matrix t3 = new Matrix();
        cam.getMatrix(t3);
        t3.preTranslate(-px, -py);
        t3.postTranslate(px + mTranslationX, py + mTranslationY);
        m.postConcat(t3);
      }
      mTransformMatrix = m;
    }
    return mTransformMatrix;
  }
  ViewPropertyAnimator mAnimator;
  public ViewPropertyAnimator animate() { if (mAnimator == null) mAnimator = new ViewPropertyAnimator(this); return mAnimator; }

  // ---------------------------------------------------------------- background
  public void setBackgroundColor(int c) {
    if (mBackground instanceof ColorDrawable) { ((ColorDrawable) mBackground).setColor(c); invalidate(); }
    else setBackgroundDrawable(new ColorDrawable(c));
  }
  public void setBackgroundResource(int id) { setBackgroundDrawable(id == 0 ? null : mResources.getDrawable(id)); }
  public void setBackground(Drawable d) { setBackgroundDrawable(d); }
  public void setBackgroundDrawable(Drawable d) {
    if (d == mBackground) return;
    boolean requestLayout = false;
    if (mBackground != null) { mBackground.setCallback(null); unscheduleDrawable(mBackground); }
    if (d != null) {
      Rect padding = new Rect();
      if (d.getPadding(padding)) setPadding(padding.left, padding.top, padding.right, padding.bottom);
      if (mBackground == null || mBackground.getMinimumHeight() != d.getMinimumHeight() || mBackground.getMinimumWidth() != d.getMinimumWidth()) requestLayout = true;
      d.setCallback(this);
      if (mBackgroundTint != null) d.setTintList(mBackgroundTint);
      if (mBackgroundTintMode != null) d.setTintMode(mBackgroundTintMode);
      if (d.isStateful()) d.setState(getDrawableState());
      d.setVisible(getVisibility() == VISIBLE, false);
      mBackground = d;
    } else {
      mBackground = null;
      requestLayout = true;
    }
    if (requestLayout) requestLayout();
    mBackgroundSizeChanged = true;
    invalidate();
  }
  public Drawable getBackground() { return mBackground; }

  // ---------------------------------------------------------------- drawable callbacks & state
  public void invalidateDrawable(Drawable who) { if (verifyDrawable(who)) invalidate(); }
  public void scheduleDrawable(Drawable who, Runnable what, long when) {
    if (verifyDrawable(who) && what != null) {
      Handler h = mAttachInfo != null ? mAttachInfo.handler : a2w.ViewRoot.mainHandler();
      h.postAtTime(what, who, when);
    }
  }
  public void unscheduleDrawable(Drawable who, Runnable what) {
    if (verifyDrawable(who) && what != null) a2w.ViewRoot.mainHandler().removeCallbacks(what, who);
  }
  public void unscheduleDrawable(Drawable who) { if (who != null) a2w.ViewRoot.mainHandler().removeCallbacksAndMessages(who); }
  protected boolean verifyDrawable(Drawable who) { return who == mBackground || who != null && who == mViewForeground; }
  protected void drawableStateChanged() {
    Drawable d = mBackground;
    if (d != null && d.isStateful()) d.setState(getDrawableState());
    Drawable fg = mViewForeground;
    if (fg != null && fg.isStateful()) fg.setState(getDrawableState());
  }
  public void refreshDrawableState() {
    mPrivateFlags |= PFLAG_DRAWABLE_STATE_DIRTY;
    drawableStateChanged();
    if (mParent instanceof View) ((View) mParent).childDrawableStateChanged(this);
  }
  public void childDrawableStateChanged(View child) {}
  public void jumpDrawablesToCurrentState() {}
  public final int[] getDrawableState() {
    if (mDrawableState != null && (mPrivateFlags & PFLAG_DRAWABLE_STATE_DIRTY) == 0) return mDrawableState;
    mDrawableState = onCreateDrawableState(0);
    mPrivateFlags &= ~PFLAG_DRAWABLE_STATE_DIRTY;
    return mDrawableState;
  }
  protected int[] onCreateDrawableState(int extraSpace) {
    if ((mViewFlags & DUPLICATE_PARENT_STATE) != 0 && mParent instanceof View) return ((View) mParent).onCreateDrawableState(extraSpace);
    int n = 0;
    int[] s = new int[8 + extraSpace];
    if ((mPrivateFlags & PFLAG_PRESSED) != 0) s[n++] = android.R.attr.state_pressed;
    if ((mViewFlags & ENABLED_MASK) == ENABLED) s[n++] = android.R.attr.state_enabled;
    if (isFocused()) s[n++] = android.R.attr.state_focused;
    if ((mPrivateFlags & PFLAG_SELECTED) != 0) s[n++] = android.R.attr.state_selected;
    if (hasWindowFocus()) s[n++] = android.R.attr.state_window_focused;
    if ((mPrivateFlags & PFLAG_ACTIVATED) != 0) s[n++] = android.R.attr.state_activated;
    int[] r = new int[n + extraSpace];
    System.arraycopy(s, 0, r, 0, n);
    return r;
  }
  protected static int[] mergeDrawableStates(int[] base, int[] add) {
    int i = base.length - 1;
    while (i >= 0 && base[i] == 0) i--;
    System.arraycopy(add, 0, base, i + 1, Math.min(add.length, base.length - i - 1));
    return base;
  }
  public void setPressed(boolean p) {
    boolean need = p != ((mPrivateFlags & PFLAG_PRESSED) == PFLAG_PRESSED);
    if (p) mPrivateFlags |= PFLAG_PRESSED; else mPrivateFlags &= ~PFLAG_PRESSED;
    if (need) { refreshDrawableState(); invalidate(); }
    dispatchSetPressed(p);
  }
  protected void dispatchSetPressed(boolean p) {}
  public boolean isPressed() { return (mPrivateFlags & PFLAG_PRESSED) == PFLAG_PRESSED; }
  public void setSelected(boolean s) {
    if (((mPrivateFlags & PFLAG_SELECTED) != 0) != s) {
      mPrivateFlags = (mPrivateFlags & ~PFLAG_SELECTED) | (s ? PFLAG_SELECTED : 0);
      invalidate();
      refreshDrawableState();
      dispatchSetSelected(s);
    }
  }
  protected void dispatchSetSelected(boolean s) {}
  public boolean isSelected() { return (mPrivateFlags & PFLAG_SELECTED) != 0; }
  public void setActivated(boolean a) {
    if (((mPrivateFlags & PFLAG_ACTIVATED) != 0) != a) {
      mPrivateFlags = (mPrivateFlags & ~PFLAG_ACTIVATED) | (a ? PFLAG_ACTIVATED : 0);
      invalidate();
      refreshDrawableState();
      dispatchSetActivated(a);
    }
  }
  protected void dispatchSetActivated(boolean a) {}
  public boolean isActivated() { return (mPrivateFlags & PFLAG_ACTIVATED) != 0; }

  // ---------------------------------------------------------------- focus
  public boolean isFocused() { return (mPrivateFlags & PFLAG_FOCUSED) != 0; }
  public boolean hasFocus() { return (mPrivateFlags & PFLAG_FOCUSED) != 0; }
  public boolean hasFocusable() { return hasFocusable(true, false); }
  public boolean hasExplicitFocusable() { return hasFocusable(false, true); }
  boolean hasFocusable(boolean allowAutoFocus, boolean dispatchExplicit) {
    if (!isFocusableInTouchMode()) for (ViewParent p = mParent; p instanceof ViewGroup; p = p.getParent()) if (((ViewGroup) p).shouldBlockFocusForTouchscreen()) return false;
    if ((mViewFlags & VISIBILITY_MASK) != VISIBLE || (mViewFlags & ENABLED_MASK) != ENABLED) return false;
    return (allowAutoFocus || getFocusable() != FOCUSABLE_AUTO) && isFocusable();
  }
  /** visible, focusable, enabled and (once laid out) not empty */
  boolean canTakeFocus() {
    return (mViewFlags & VISIBILITY_MASK) == VISIBLE && (mViewFlags & FOCUSABLE) == FOCUSABLE && (mViewFlags & ENABLED_MASK) == ENABLED
        && (!isLaidOut() || isLayoutRequested() || (mRight > mLeft && mBottom > mTop));
  }
  public View findFocus() { return isFocused() ? this : null; }
  public final boolean requestFocus() { return requestFocus(FOCUS_DOWN); }
  public final boolean requestFocus(int dir) { return requestFocus(dir, null); }
  public boolean requestFocus(int dir, Rect prev) { return requestFocusNoSearch(dir, prev); }
  boolean requestFocusNoSearch(int dir, Rect prev) {
    if (!canTakeFocus()) return false;
    if (isInTouchMode() && (mViewFlags & FOCUSABLE_IN_TOUCH_MODE) == 0) return false;
    if (hasAncestorThatBlocksDescendantFocus()) return false;
    handleFocusGainInternal(dir, prev);
    return true;
  }
  private boolean hasAncestorThatBlocksDescendantFocus() {
    final boolean focusableInTouchMode = isFocusableInTouchMode();
    for (ViewParent p = mParent; p instanceof ViewGroup; p = p.getParent()) {
      ViewGroup g = (ViewGroup) p;
      if (g.getDescendantFocusability() == ViewGroup.FOCUS_BLOCK_DESCENDANTS || (!focusableInTouchMode && g.shouldBlockFocusForTouchscreen())) return true;
    }
    return false;
  }
  /** focus for a view touched: leaves touch mode first, so views not focusable in touch mode can take it */
  public final boolean requestFocusFromTouch() {
    if (isInTouchMode()) a2w.ViewRoot.ensureTouchMode(false);
    return requestFocus(FOCUS_DOWN);
  }
  void handleFocusGainInternal(int dir, Rect prev) {
    if ((mPrivateFlags & PFLAG_FOCUSED) == 0) {
      View old = mAttachInfo != null ? mAttachInfo.root.focused : null;
      if (old != null && old != this) old.unFocus();
      mPrivateFlags |= PFLAG_FOCUSED;
      if (mAttachInfo != null) { mAttachInfo.root.focused = this; mAttachInfo.treeObserver.dispatchOnGlobalFocusChange(old, this); }
      if (mParent != null) mParent.requestChildFocus(this, this);
      onFocusChanged(true, dir, prev);
      refreshDrawableState();
    }
  }
  /** gives up focus; out of touch mode the window then gives focus to its first focusable view, as on a device */
  public void clearFocus() {
    if ((mPrivateFlags & PFLAG_FOCUSED) != 0) {
      mPrivateFlags &= ~PFLAG_FOCUSED;
      if (mAttachInfo != null && mAttachInfo.root.focused == this) mAttachInfo.root.focused = null;
      if (mParent != null) mParent.clearChildFocus(this);
      onFocusChanged(false, 0, null);
      refreshDrawableState();
      if (mAttachInfo != null && !isInTouchMode()) { View root = mAttachInfo.rootView; if (root != null && root.findFocus() == null) root.requestFocus(); }
    }
  }
  void unFocus() {
    if ((mPrivateFlags & PFLAG_FOCUSED) != 0) {
      mPrivateFlags &= ~PFLAG_FOCUSED;
      onFocusChanged(false, 0, null);
      refreshDrawableState();
    }
  }
  protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
    if (!gainFocus && isPressed()) setPressed(false);
    if (mAttachInfo != null) mAttachInfo.root.keyDispatchState.reset(this);
    invalidate();
    if (mOnFocusChangeListener != null) mOnFocusChangeListener.onFocusChange(this, gainFocus);
  }
  public void setOnFocusChangeListener(OnFocusChangeListener l) { mOnFocusChangeListener = l; }
  public OnFocusChangeListener getOnFocusChangeListener() { return mOnFocusChangeListener; }
  int mNextFocusDownId = NO_ID, mNextFocusUpId = NO_ID, mNextFocusLeftId = NO_ID, mNextFocusRightId = NO_ID, mNextFocusForwardId = NO_ID, mLabelForId = NO_ID;
  public void setNextFocusDownId(int id) { mNextFocusDownId = id; }
  public void setNextFocusUpId(int id) { mNextFocusUpId = id; }
  public void setNextFocusLeftId(int id) { mNextFocusLeftId = id; }
  public void setNextFocusRightId(int id) { mNextFocusRightId = id; }
  public void setNextFocusForwardId(int id) { mNextFocusForwardId = id; }
  public int getNextFocusDownId() { return mNextFocusDownId; }
  public int getNextFocusUpId() { return mNextFocusUpId; }
  public int getNextFocusLeftId() { return mNextFocusLeftId; }
  public int getNextFocusRightId() { return mNextFocusRightId; }
  public int getNextFocusForwardId() { return mNextFocusForwardId; }
  public void setLabelFor(int id) { mLabelForId = id; }
  public int getLabelFor() { return mLabelForId; }
  public void addFocusables(ArrayList<View> views, int dir, int mode) {
    if (views == null || !canTakeFocus()) return;
    if ((mode & FOCUSABLES_TOUCH_MODE) == FOCUSABLES_TOUCH_MODE && !isFocusableInTouchMode()) return;
    views.add(this);
  }
  /** the view a nextFocus*Id names for a direction (for FOCUS_BACKWARD: the view whose nextFocusForwardId names this one) */
  View findUserSetNextFocus(View root, int direction) {
    int id;
    switch (direction) {
      case FOCUS_LEFT: id = mNextFocusLeftId; break;
      case FOCUS_RIGHT: id = mNextFocusRightId; break;
      case FOCUS_UP: id = mNextFocusUpId; break;
      case FOCUS_DOWN: id = mNextFocusDownId; break;
      case FOCUS_FORWARD: id = mNextFocusForwardId; break;
      case FOCUS_BACKWARD: return mID == NO_ID ? null : findForwardLinkTo(root, root, this);
      default: return null;
    }
    return id == NO_ID ? null : findViewInsideOut(root, id);
  }
  /** the view with this id nearest to this view: in its subtree, then its parent's, up to root */
  View findViewInsideOut(View root, int id) {
    for (View start = this; ; ) {
      View v = start.findViewById(id);
      if (v != null || start == root) return v;
      ViewParent p = start.getParent();
      if (!(p instanceof View)) return null;
      start = (View) p;
    }
  }
  private static View findForwardLinkTo(View root, View v, View target) {
    if (v.mNextFocusForwardId != NO_ID && v != target && v.findViewInsideOut(root, v.mNextFocusForwardId) == target) return v;
    if (v instanceof ViewGroup) {
      ViewGroup g = (ViewGroup) v;
      for (int i = 0; i < g.getChildCount(); i++) { View r = findForwardLinkTo(root, g.getChildAt(i), target); if (r != null) return r; }
    }
    return null;
  }
  public ArrayList<View> getFocusables(int dir) { ArrayList<View> r = new ArrayList<>(24); addFocusables(r, dir); return r; }
  public void addTouchables(ArrayList<View> views) { if ((isClickable() || isLongClickable()) && isEnabled() && getVisibility() == VISIBLE) views.add(this); }
  public ArrayList<View> getTouchables() { ArrayList<View> r = new ArrayList<>(); addTouchables(r); return r; }
  public void getFocusedRect(Rect r) { getDrawingRect(r); }
  /** the visible part of the window (the whole page: the on-screen keyboard does not resize it) */
  public void getWindowVisibleDisplayFrame(Rect outRect) { outRect.set(0, 0, a2w.Screen.W, a2w.Screen.H); }
  int mTextAlignment = 1;
  public void setTextAlignment(int a) { mTextAlignment = a; invalidate(); }
  public int getTextAlignment() { return mTextAlignment; }
  public boolean canResolveTextAlignment() { return true; }
  String mTransitionName;
  CharSequence mTooltip, mStateDescription;
  public void setTransitionName(String n) { mTransitionName = n; }
  public String getTransitionName() { return mTransitionName; }
  public void setTooltipText(CharSequence t) { mTooltip = t; }
  public CharSequence getTooltipText() { return mTooltip; }
  public void setStateDescription(CharSequence d) { mStateDescription = d; }
  public CharSequence getStateDescription() { return mStateDescription; }
  public CharSequence getAccessibilityClassName() { return getClass().getName(); }
  /** this view's state (onSaveInstanceState) under its id, unless saving is disabled; a ViewGroup adds its children's */
  public void dispatchSaveInstanceState(SparseArray<android.os.Parcelable> container) {
    if (getId() != NO_ID && (mViewFlags & SAVE_DISABLED) == 0) {
      mSaveStateCalled = false;
      android.os.Parcelable state = onSaveInstanceState();
      if (!mSaveStateCalled) throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
      if (state != null) container.put(getId(), state);
    }
  }
  public void dispatchRestoreInstanceState(SparseArray<android.os.Parcelable> container) {
    if (getId() != NO_ID) {
      android.os.Parcelable state = container.get(getId());
      if (state != null) {
        mSaveStateCalled = false;
        onRestoreInstanceState(state);
        if (!mSaveStateCalled) throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
      }
    }
  }
  public View focusSearch(int dir) { return mParent != null ? mParent.focusSearch(this, dir) : null; }
  public void addFocusables(ArrayList<View> views, int dir) { addFocusables(views, dir, isInTouchMode() ? FOCUSABLES_TOUCH_MODE : FOCUSABLES_ALL); }
  public boolean hasWindowFocus() { return mAttachInfo != null && mAttachInfo.hasWindowFocus; }
  public void onWindowFocusChanged(boolean hasWindowFocus) {
    if (!hasWindowFocus) { if (isPressed()) setPressed(false); removeLongPressCallback(); }
    refreshDrawableState();
  }
  public void dispatchWindowFocusChanged(boolean hasFocus) { onWindowFocusChanged(hasFocus); }
  public boolean onCheckIsTextEditor() { return false; }
  public android.view.inputmethod.InputConnection onCreateInputConnection(android.view.inputmethod.EditorInfo outAttrs) { return null; }

  // ---------------------------------------------------------------- listeners
  public void setOnClickListener(OnClickListener l) { if (!isClickable()) setClickable(true); mOnClickListener = l; }
  public boolean hasOnClickListeners() { return mOnClickListener != null; }
  public void setOnLongClickListener(OnLongClickListener l) { if (!isLongClickable()) setLongClickable(true); mOnLongClickListener = l; }
  public void setOnTouchListener(OnTouchListener l) { mOnTouchListener = l; }
  public void setOnKeyListener(OnKeyListener l) { mOnKeyListener = l; }
  /** web: offers a key typed into the page's text field to the view's OnKeyListener */
  public boolean a2wKeyListenerConsumes(KeyEvent e) { return mOnKeyListener != null && (mViewFlags & ENABLED_MASK) == ENABLED && mOnKeyListener.onKey(this, e.getKeyCode(), e); }
  public void setOnCreateContextMenuListener(OnCreateContextMenuListener l) { if (!isLongClickable()) setLongClickable(true); mOnCreateContextMenuListener = l; }
  OnGenericMotionListener mOnGenericMotionListener;
  OnHoverListener mOnHoverListener;
  public void setOnGenericMotionListener(OnGenericMotionListener l) { mOnGenericMotionListener = l; }
  public void setOnHoverListener(OnHoverListener l) { mOnHoverListener = l; }
  public void setOnDragListener(OnDragListener l) {}
  public void addOnLayoutChangeListener(OnLayoutChangeListener l) { if (mOnLayoutChangeListeners == null) mOnLayoutChangeListeners = new ArrayList<>(); mOnLayoutChangeListeners.add(l); }
  public void removeOnLayoutChangeListener(OnLayoutChangeListener l) { if (mOnLayoutChangeListeners != null) mOnLayoutChangeListeners.remove(l); }
  public void addOnAttachStateChangeListener(OnAttachStateChangeListener l) { if (mOnAttachStateChangeListeners == null) mOnAttachStateChangeListeners = new ArrayList<>(); mOnAttachStateChangeListeners.add(l); }
  public void removeOnAttachStateChangeListener(OnAttachStateChangeListener l) { if (mOnAttachStateChangeListeners != null) mOnAttachStateChangeListeners.remove(l); }
  public boolean performClick() {
    playSoundEffect(SoundEffectConstants.CLICK);
    if (mOnClickListener != null) { mOnClickListener.onClick(this); return true; }
    return false;
  }
  public boolean callOnClick() { if (mOnClickListener != null) { mOnClickListener.onClick(this); return true; } return false; }
  public boolean performLongClick() {
    boolean handled = false;
    if (mOnLongClickListener != null) handled = mOnLongClickListener.onLongClick(this);
    if (!handled) handled = showContextMenu();
    return handled;
  }
  /** builds this view's context menu (it and its parents add items) and shows it; selections go to the activity */
  public boolean showContextMenu() {
    a2w.MenuBuilder.ContextMenuBuilder menu = new a2w.MenuBuilder.ContextMenuBuilder(getContext());
    createContextMenu(menu);
    if (!menu.hasVisibleItems()) return false;
    android.app.Activity act = null;
    for (android.content.Context k = getContext(); k != null; k = k instanceof android.content.ContextWrapper ? ((android.content.ContextWrapper) k).getBaseContext() : null) {
      if (k instanceof android.app.Activity) { act = (android.app.Activity) k; break; }
      if (!(k instanceof android.content.ContextWrapper)) break;
    }
    // the window's callback (a dialog's views report to the dialog), else the activity of the context
    Window.Callback cb = act;
    View root = getRootView();
    if (root instanceof a2w.PhoneWindow.DecorView && ((a2w.PhoneWindow.DecorView) root).window().getCallback() != null) cb = ((a2w.PhoneWindow.DecorView) root).window().getCallback();
    final Window.Callback w = cb;
    menu.setCallback((m, item) -> w != null && w.onMenuItemSelected(Window.FEATURE_CONTEXT_MENU, item));
    a2w.MenuPopup.show(menu, () -> { if (w != null) w.onPanelClosed(Window.FEATURE_CONTEXT_MENU, menu); });
    return true;
  }
  public boolean showContextMenu(float x, float y) { return showContextMenu(); }
  public void createContextMenu(ContextMenu menu) {
    ContextMenu.ContextMenuInfo menuInfo = getContextMenuInfo();
    if (menu instanceof a2w.MenuBuilder) ((a2w.MenuBuilder) menu).setMenuInfo(menuInfo);
    onCreateContextMenu(menu);
    if (mOnCreateContextMenuListener != null) mOnCreateContextMenuListener.onCreateContextMenu(menu, this, menuInfo);
    if (mParent instanceof View) ((View) mParent).createContextMenu(menu);
  }
  protected ContextMenu.ContextMenuInfo getContextMenuInfo() { return null; }
  protected void onCreateContextMenu(ContextMenu menu) {}

  // ---------------------------------------------------------------- tree observer & attach
  public ViewTreeObserver getViewTreeObserver() {
    if (mAttachInfo != null) return mAttachInfo.treeObserver;
    if (mFloatingTreeObserver == null) mFloatingTreeObserver = new ViewTreeObserver();
    return mFloatingTreeObserver;
  }
  void dispatchAttachedToWindow(AttachInfo info, int visibility) {
    mAttachInfo = info;
    mWindowAttachCount++;
    if (mFloatingTreeObserver != null) { info.treeObserver.merge(mFloatingTreeObserver); mFloatingTreeObserver = null; }
    if (mPendingPosts != null) { for (Runnable r : mPendingPosts) info.handler.post(r); mPendingPosts = null; }
    onAttachedToWindow();
    if (mOnAttachStateChangeListeners != null) for (OnAttachStateChangeListener l : new ArrayList<>(mOnAttachStateChangeListeners)) l.onViewAttachedToWindow(this);
    int vis = info.windowVisibility;
    if (vis != GONE) onWindowVisibilityChanged(vis);
    if ((mPrivateFlags & PFLAG_DRAWABLE_STATE_DIRTY) != 0) refreshDrawableState();
  }
  void dispatchDetachedFromWindow() {
    AttachInfo info = mAttachInfo;
    if (info != null && info.windowVisibility != GONE) onWindowVisibilityChanged(GONE);
    onDetachedFromWindow();
    if (mOnAttachStateChangeListeners != null) for (OnAttachStateChangeListener l : new ArrayList<>(mOnAttachStateChangeListeners)) l.onViewDetachedFromWindow(this);
    if (info != null && info.root.focused == this) info.root.focused = null;
    mAttachInfo = null;
  }
  protected void onAttachedToWindow() {
    if ((mPrivateFlags & PFLAG_DRAWABLE_STATE_DIRTY) != 0) refreshDrawableState();
  }
  protected void onDetachedFromWindow() {
    removeLongPressCallback();
    removeTapCallback();
    if (mCurrentAnimation != null && !mCurrentAnimation.hasEnded()) {}
    mPrivateFlags &= ~PFLAG_PRESSED;
  }
  protected int getWindowAttachCount() { return mWindowAttachCount; }
  protected void onFinishInflate() {}
  public void onStartTemporaryDetach() { removeUnsetPressCallback(); }
  public void onFinishTemporaryDetach() {}
  protected void onConfigurationChanged(android.content.res.Configuration c) {}
  public void dispatchConfigurationChanged(android.content.res.Configuration c) { onConfigurationChanged(c); }

  // ---------------------------------------------------------------- posting
  public boolean post(Runnable r) {
    if (mAttachInfo != null) return mAttachInfo.handler.post(r);
    if (mPendingPosts == null) mPendingPosts = new ArrayList<>();
    mPendingPosts.add(r);
    return true;
  }
  public boolean postDelayed(Runnable r, long d) {
    if (mAttachInfo != null) return mAttachInfo.handler.postDelayed(r, d);
    return a2w.ViewRoot.mainHandler().postDelayed(r, d);
  }
  public boolean removeCallbacks(Runnable r) {
    if (r != null) {
      a2w.ViewRoot.mainHandler().removeCallbacks(r);
      if (mPendingPosts != null) mPendingPosts.remove(r);
    }
    return true;
  }
  public void postInvalidate() { postInvalidateDelayed(0); }
  public void postInvalidate(int l, int t, int r, int b) { postInvalidateDelayed(0); }
  public void postInvalidateDelayed(long d) { a2w.ViewRoot.mainHandler().postDelayed(this::invalidate, d); }
  public void postInvalidateDelayed(long d, int l, int t, int r, int b) { postInvalidateDelayed(d); }
  public void postOnAnimation(Runnable r) { post(r); }
  public void postInvalidateOnAnimation() { postInvalidate(); }

  // ---------------------------------------------------------------- invalidation & layout requests
  public void invalidate() {
    if (mAttachInfo == null || skipInvalidate()) return;
    if (DEBUG_INVALIDATE != null) DEBUG_INVALIDATE.merge(getClass().getName() + "#" + (getId() > 0 ? a2w.Res.name(getId()) : "")
        + (isShown() ? "" : "(hidden)") + (mCurrentAnimation != null ? "(anim)" : "") + (getAnimation() != null ? "" : ""), 1, Integer::sum);
    mAttachInfo.root.invalidate();
  }
  /** web: per-class invalidate counts while debugging (Main.debugInvalidators) */
  public static java.util.HashMap<String, Integer> DEBUG_INVALIDATE;
  private boolean skipInvalidate() { return (mViewFlags & VISIBILITY_MASK) != VISIBLE && mCurrentAnimation == null; }
  public void invalidate(boolean b) { invalidate(); }
  public void invalidate(Rect r) { invalidate(); }
  public void invalidate(int l, int t, int r, int b) { invalidate(); }
  void invalidateParent() { invalidate(); }
  protected void invalidateParentCaches() {}
  protected void invalidateParentIfNeeded() { invalidate(); }
  public void requestLayout() {
    mPrivateFlags |= PFLAG_FORCE_LAYOUT;
    if (mParent != null && !mParent.isLayoutRequested()) mParent.requestLayout();
  }
  public void forceLayout() { mPrivateFlags |= PFLAG_FORCE_LAYOUT; }
  public boolean isLayoutRequested() { return (mPrivateFlags & PFLAG_FORCE_LAYOUT) == PFLAG_FORCE_LAYOUT; }
  public boolean isDirty() { return true; }

  // ---------------------------------------------------------------- measure
  public final void measure(int widthMeasureSpec, int heightMeasureSpec) {
    if ((mPrivateFlags & PFLAG_FORCE_LAYOUT) == PFLAG_FORCE_LAYOUT || widthMeasureSpec != mOldWidthMeasureSpec || heightMeasureSpec != mOldHeightMeasureSpec) {
      mPrivateFlags &= ~PFLAG_MEASURED_DIMENSION_SET;
      onMeasure(widthMeasureSpec, heightMeasureSpec);
      if ((mPrivateFlags & PFLAG_MEASURED_DIMENSION_SET) != PFLAG_MEASURED_DIMENSION_SET)
        throw new IllegalStateException("onMeasure() did not set the measured dimension by calling setMeasuredDimension()");
      mPrivateFlags |= PFLAG_LAYOUT_REQUIRED;
    }
    mOldWidthMeasureSpec = widthMeasureSpec;
    mOldHeightMeasureSpec = heightMeasureSpec;
  }
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
  }
  protected final void setMeasuredDimension(int w, int h) { mMeasuredWidth = w; mMeasuredHeight = h; mPrivateFlags |= PFLAG_MEASURED_DIMENSION_SET; }
  public final int getMeasuredWidth() { return mMeasuredWidth & MEASURED_SIZE_MASK; }
  public final int getMeasuredHeight() { return mMeasuredHeight & MEASURED_SIZE_MASK; }
  public final int getMeasuredWidthAndState() { return mMeasuredWidth; }
  public final int getMeasuredHeightAndState() { return mMeasuredHeight; }
  public final int getMeasuredState() { return (mMeasuredWidth & MEASURED_STATE_MASK) | ((mMeasuredHeight >> MEASURED_HEIGHT_STATE_SHIFT) & (MEASURED_STATE_MASK >> MEASURED_HEIGHT_STATE_SHIFT)); }
  public static int combineMeasuredStates(int a, int b) { return a | b; }
  public static int resolveSize(int size, int spec) { return resolveSizeAndState(size, spec, 0) & MEASURED_SIZE_MASK; }
  public static int resolveSizeAndState(int size, int spec, int childState) {
    int result = size, mode = MeasureSpec.getMode(spec), specSize = MeasureSpec.getSize(spec);
    switch (mode) {
      case MeasureSpec.UNSPECIFIED: result = size; break;
      case MeasureSpec.AT_MOST: result = specSize < size ? specSize | MEASURED_STATE_TOO_SMALL : size; break;
      case MeasureSpec.EXACTLY: result = specSize; break;
    }
    return result | (childState & MEASURED_STATE_MASK);
  }
  public static int getDefaultSize(int size, int spec) {
    int mode = MeasureSpec.getMode(spec), specSize = MeasureSpec.getSize(spec);
    switch (mode) {
      case MeasureSpec.UNSPECIFIED: return size;
      default: return specSize;
    }
  }
  protected int getSuggestedMinimumWidth() { return mBackground == null ? mMinWidth : Math.max(mMinWidth, mBackground.getMinimumWidth()); }
  protected int getSuggestedMinimumHeight() { return mBackground == null ? mMinHeight : Math.max(mMinHeight, mBackground.getMinimumHeight()); }
  public int getMinimumWidth() { return mMinWidth; }
  public int getMinimumHeight() { return mMinHeight; }
  public void setMinimumWidth(int w) { mMinWidth = w; requestLayout(); }
  public void setMinimumHeight(int h) { mMinHeight = h; requestLayout(); }
  public int getBaseline() { return -1; }

  // ---------------------------------------------------------------- layout
  public void layout(int l, int t, int r, int b) {
    mLaidOutOnce = true;
    int ol = mLeft, ot = mTop, or = mRight, ob = mBottom;
    boolean changed = setFrame(l, t, r, b);
    if (changed || (mPrivateFlags & PFLAG_LAYOUT_REQUIRED) == PFLAG_LAYOUT_REQUIRED) {
      onLayout(changed, l, t, r, b);
      mPrivateFlags &= ~PFLAG_LAYOUT_REQUIRED;
      if (mOnLayoutChangeListeners != null)
        for (OnLayoutChangeListener li : new ArrayList<>(mOnLayoutChangeListeners)) li.onLayoutChange(this, l, t, r, b, ol, ot, or, ob);
    }
    mPrivateFlags &= ~PFLAG_FORCE_LAYOUT;
  }
  protected void onLayout(boolean changed, int l, int t, int r, int b) {}
  protected boolean setFrame(int l, int t, int r, int b) {
    if (mLeft != l || mRight != r || mTop != t || mBottom != b) {
      int ow = mRight - mLeft, oh = mBottom - mTop, nw = r - l, nh = b - t;
      boolean sizeChanged = nw != ow || nh != oh;
      invalidate();
      mLeft = l; mTop = t; mRight = r; mBottom = b;
      mPrivateFlags |= PFLAG_HAS_BOUNDS;
      if (sizeChanged) { mTransformMatrix = null; onSizeChanged(nw, nh, ow, oh); }
      mBackgroundSizeChanged = true;
      return true;
    }
    return false;
  }
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {}

  // ---------------------------------------------------------------- scrolling
  public final int getScrollX() { return mScrollX; }
  public final int getScrollY() { return mScrollY; }
  public void setScrollX(int x) { scrollTo(x, mScrollY); }
  public void setScrollY(int y) { scrollTo(mScrollX, y); }
  public void scrollTo(int x, int y) {
    if (mScrollX != x || mScrollY != y) {
      int ox = mScrollX, oy = mScrollY;
      mScrollX = x; mScrollY = y;
      onScrollChanged(mScrollX, mScrollY, ox, oy);
      invalidate();
    }
  }
  public void scrollBy(int x, int y) { scrollTo(mScrollX + x, mScrollY + y); }
  protected void onScrollChanged(int l, int t, int oldl, int oldt) { if (mAttachInfo != null) mAttachInfo.treeObserver.dispatchOnScrollChanged(); }
  public void computeScroll() {}
  protected boolean awakenScrollBars() { return false; }
  protected boolean awakenScrollBars(int d) { return false; }
  protected boolean awakenScrollBars(int startDelay, boolean invalidate) { if (invalidate) invalidate(); return false; }
  protected boolean isPaddingOffsetRequired() { return false; }
  protected int getLeftPaddingOffset() { return 0; }
  protected int getRightPaddingOffset() { return 0; }
  protected int getTopPaddingOffset() { return 0; }
  protected int getBottomPaddingOffset() { return 0; }
  protected float getLeftFadingEdgeStrength() { return computeHorizontalScrollOffset() > 0 ? 1.0f : 0.0f; }
  protected float getRightFadingEdgeStrength() { return computeHorizontalScrollOffset() + computeHorizontalScrollExtent() < computeHorizontalScrollRange() ? 1.0f : 0.0f; }
  public boolean canResolveTextDirection() { return true; }
  public boolean isTextAlignmentResolved() { return true; }
  public ActionMode startActionMode(ActionMode.Callback callback) { return startActionMode(callback, ActionMode.TYPE_PRIMARY); }
  public ActionMode startActionMode(ActionMode.Callback callback, int type) { ViewParent p = getParent(); return p == null ? null : p.startActionModeForChild(this, callback, type); }
  protected int computeHorizontalScrollRange() { return getWidth(); }
  protected int computeHorizontalScrollOffset() { return mScrollX; }
  protected int computeHorizontalScrollExtent() { return getWidth(); }
  protected int computeVerticalScrollRange() { return getHeight(); }
  protected int computeVerticalScrollOffset() { return mScrollY; }
  protected int computeVerticalScrollExtent() { return getHeight(); }
  public boolean canScrollHorizontally(int d) { return false; }
  public boolean canScrollVertically(int d) { return false; }
  protected boolean overScrollBy(int dx, int dy, int sx, int sy, int rx, int ry, int mx, int my, boolean touch) {
    int nx = sx + dx, ny = sy + dy;
    boolean cx = false, cy = false;
    int left = -mx, right = mx + rx, top = -my, bottom = my + ry;
    if (nx > right) { nx = right; cx = true; } else if (nx < left) { nx = left; cx = true; }
    if (ny > bottom) { ny = bottom; cy = true; } else if (ny < top) { ny = top; cy = true; }
    onOverScrolled(nx, ny, cx, cy);
    return cx || cy;
  }
  protected void onOverScrolled(int x, int y, boolean cx, boolean cy) {}
  protected float getTopFadingEdgeStrength() { return 0; }
  protected float getBottomFadingEdgeStrength() { return 0; }
  public int getSolidColor() { return 0; }
  public boolean requestRectangleOnScreen(Rect r) { return requestRectangleOnScreen(r, false); }
  public boolean requestRectangleOnScreen(Rect r, boolean immediate) {
    View child = this;
    ViewParent p = mParent;
    Rect pos = new Rect(r);
    boolean scrolled = false;
    while (p instanceof View) {
      scrolled |= p.requestChildRectangleOnScreen(child, pos, immediate);
      View pv = (View) p;
      pos.offset(child.mLeft - child.mScrollX, child.mTop - child.mScrollY);
      child = pv;
      p = pv.mParent;
    }
    return scrolled;
  }

  // ---------------------------------------------------------------- drawing
  public void draw(Canvas canvas) {
    int clipSave = -1;
    if (mClipBounds != null) { clipSave = canvas.save(); canvas.clipRect(mClipBounds); }
    try { drawContent(canvas); } finally { if (clipSave >= 0) canvas.restoreToCount(clipSave); }
  }
  private void drawContent(Canvas canvas) {
    final Drawable background = mBackground;
    if (background != null) {
      if (mBackgroundSizeChanged) { background.setBounds(0, 0, mRight - mLeft, mBottom - mTop); mBackgroundSizeChanged = false; }
      if ((mScrollX | mScrollY) == 0) background.draw(canvas);
      else { canvas.translate(mScrollX, mScrollY); background.draw(canvas); canvas.translate(-mScrollX, -mScrollY); }
    }
    onDraw(canvas);
    dispatchDraw(canvas);
    onDrawForeground(canvas);
    drawDefaultFocusHighlight(canvas);
  }
  /** draws the foreground drawable (and the scroll bars) over the content */
  public void onDrawForeground(Canvas canvas) {
    onDrawScrollBars(canvas);
    Drawable fg = mViewForeground;
    if (fg != null) {
      if (mViewForegroundBoundsChanged) {
        Rect out = new Rect();
        Gravity.apply(mViewForegroundGravity, fg.getIntrinsicWidth(), fg.getIntrinsicHeight(), new Rect(0, 0, getWidth(), getHeight()), out);
        fg.setBounds(out);
        mViewForegroundBoundsChanged = false;
      }
      fg.draw(canvas);
    }
  }
  // ---- foreground (API 23), background tint, clip bounds, touch delegate, hover, context clicks
  Drawable mViewForeground;
  int mViewForegroundGravity = Gravity.FILL;
  boolean mViewForegroundBoundsChanged;
  android.content.res.ColorStateList mBackgroundTint, mForegroundTint;
  android.graphics.PorterDuff.Mode mBackgroundTintMode, mForegroundTintMode;
  Rect mClipBounds;
  TouchDelegate mTouchDelegate;
  boolean mHovered, mContextClickable, mNestedScrollingEnabled;
  int mTextDirection, mScrollIndicators, mVerticalScrollbarPosition;
  public void setForeground(Drawable d) {
    if (mViewForeground == d) return;
    if (mViewForeground != null) mViewForeground.setCallback(null);
    mViewForeground = d;
    if (d != null) { d.setCallback(this); if (d.isStateful()) d.setState(getDrawableState()); if (mForegroundTint != null) d.setTintList(mForegroundTint); }
    mViewForegroundBoundsChanged = true;
    setWillNotDraw(false);
    invalidate();
  }
  public Drawable getForeground() { return mViewForeground; }
  /** a color or color-list attribute value */
  android.content.res.ColorStateList tintList(String v) {
    try {
      if (v.startsWith("#")) return android.content.res.ColorStateList.valueOf(Values.parseColor(v));
      if (Values.isRef(v)) return mResources.getColorStateList(Values.refId(v), mContext != null ? mContext.getTheme() : null);
    } catch (RuntimeException e) { /* none */ }
    return null;
  }
  public void setForegroundGravity(int g) { mViewForegroundGravity = g; mViewForegroundBoundsChanged = true; invalidate(); }
  public int getForegroundGravity() { return mViewForegroundGravity; }
  public void setForegroundTintList(android.content.res.ColorStateList t) { mForegroundTint = t; Drawable fg = getForeground(); if (fg != null) fg.setTintList(t); }
  public android.content.res.ColorStateList getForegroundTintList() { return mForegroundTint; }
  public void setForegroundTintMode(android.graphics.PorterDuff.Mode m) { mForegroundTintMode = m; Drawable fg = getForeground(); if (fg != null) fg.setTintMode(m); }
  public android.graphics.PorterDuff.Mode getForegroundTintMode() { return mForegroundTintMode; }
  public void setBackgroundTintList(android.content.res.ColorStateList t) { mBackgroundTint = t; if (mBackground != null) { mBackground.setTintList(t); invalidate(); } }
  public android.content.res.ColorStateList getBackgroundTintList() { return mBackgroundTint; }
  public void setBackgroundTintMode(android.graphics.PorterDuff.Mode m) { mBackgroundTintMode = m; if (mBackground != null) { mBackground.setTintMode(m); invalidate(); } }
  public android.graphics.PorterDuff.Mode getBackgroundTintMode() { return mBackgroundTintMode; }
  public void setClipBounds(Rect r) { mClipBounds = r == null ? null : new Rect(r); invalidate(); }
  public Rect getClipBounds() { return mClipBounds == null ? null : new Rect(mClipBounds); }
  public boolean getClipBounds(Rect out) { if (mClipBounds == null) return false; out.set(mClipBounds); return true; }
  public void setTouchDelegate(TouchDelegate d) { mTouchDelegate = d; }
  public TouchDelegate getTouchDelegate() { return mTouchDelegate; }
  public boolean isHovered() { return mHovered; }
  public void setHovered(boolean h) { if (mHovered != h) { mHovered = h; refreshDrawableState(); onHoverChanged(h); } }
  public void onHoverChanged(boolean hovered) {}
  public boolean isContextClickable() { return mContextClickable; }
  public void setContextClickable(boolean c) { mContextClickable = c; }
  public interface OnContextClickListener { boolean onContextClick(View v); }
  OnContextClickListener mOnContextClickListener;
  public void setOnContextClickListener(OnContextClickListener l) { if (!isContextClickable()) setContextClickable(true); mOnContextClickListener = l; }
  public boolean performContextClick() { return mOnContextClickListener != null && mOnContextClickListener.onContextClick(this); }
  public boolean performContextClick(float x, float y) { return performContextClick(); }
  public boolean performLongClick(float x, float y) { return performLongClick(); }
  public boolean hasOnLongClickListeners() { return mOnLongClickListener != null; }
  public void postOnAnimationDelayed(Runnable r, long delayMillis) { postDelayed(r, delayMillis); }
  public void postInvalidateOnAnimation(int l, int t, int r, int b) { postInvalidateOnAnimation(); }
  public boolean isPaddingRelative() { return false; }
  public int getTextDirection() { return mTextDirection; }
  public void setTextDirection(int d) { mTextDirection = d; }
  public boolean isTextDirectionResolved() { return true; }
  public boolean isLayoutDirectionResolved() { return true; }
  public boolean canResolveLayoutDirection() { return true; }
  public boolean isInLayout() { return false; }
  public boolean isTemporarilyDetached() { return false; }
  public void onVisibilityAggregated(boolean isVisible) {}
  public void setScrollIndicators(int indicators) { mScrollIndicators = indicators; }
  public void setScrollIndicators(int indicators, int mask) { mScrollIndicators = (mScrollIndicators & ~mask) | (indicators & mask); }
  public int getScrollIndicators() { return mScrollIndicators; }
  public int getScrollBarSize() { return Math.round(4 * a2w.Screen.DENSITY); }
  public void setScrollBarSize(int size) {}
  public void setVerticalScrollbarPosition(int p) { mVerticalScrollbarPosition = p; }
  public int getVerticalScrollbarPosition() { return mVerticalScrollbarPosition; }
  public int getVerticalScrollbarWidth() { return getScrollBarSize(); }
  public int getHorizontalScrollbarHeight() { return getScrollBarSize(); }
  /** FOCUSABLE, NOT_FOCUSABLE or FOCUSABLE_AUTO (focusable when clickable) */
  public void setFocusable(int focusable) {
    if ((focusable & (FOCUSABLE_AUTO | FOCUSABLE)) == 0) setFlags(0, FOCUSABLE_IN_TOUCH_MODE);
    setFlags(focusable, FOCUSABLE_MASK);
  }
  public int getFocusable() { return (mViewFlags & FOCUSABLE_AUTO) != 0 ? FOCUSABLE_AUTO : mViewFlags & FOCUSABLE; }
  boolean mFocusedByDefault, mDefaultFocusHighlightEnabled = true;
  public boolean isFocusedByDefault() { return mFocusedByDefault; }
  public void setFocusedByDefault(boolean b) { mFocusedByDefault = b; }
  public boolean getDefaultFocusHighlightEnabled() { return mDefaultFocusHighlightEnabled; }
  public void setDefaultFocusHighlightEnabled(boolean b) { mDefaultFocusHighlightEnabled = b; invalidate(); }
  private Drawable mDefaultFocusHighlight;
  /** out of touch mode, a focused view whose background and foreground show no focused state gets the theme's selectableItemBackground drawn over it */
  private void drawDefaultFocusHighlight(Canvas canvas) {
    if (!isFocused() || !mDefaultFocusHighlightEnabled || mAttachInfo == null || isInTouchMode()) return;
    Drawable bg = mBackground, fg = mViewForeground;
    if ((bg != null && bg.isStateful() && bg.hasFocusStateSpecified()) || (fg != null && fg.isStateful() && fg.hasFocusStateSpecified())) return;
    if (mDefaultFocusHighlight == null) {
      try {
        android.content.res.TypedArray ta = mContext.obtainStyledAttributes(new int[] {android.R.attr.selectableItemBackground});
        mDefaultFocusHighlight = ta.getDrawable(0);
        ta.recycle();
      } catch (RuntimeException e) { mDefaultFocusHighlight = null; }
      if (mDefaultFocusHighlight == null) mDefaultFocusHighlight = new android.graphics.drawable.ColorDrawable(0x33808080);
    }
    Drawable d = mDefaultFocusHighlight;
    d.setState(getDrawableState());
    d.setBounds(mScrollX, mScrollY, mScrollX + mRight - mLeft, mScrollY + mBottom - mTop);
    d.draw(canvas);
  }
  public boolean hasOverlappingRendering() { return true; }
  public void forceHasOverlappingRendering(boolean b) {}
  public boolean getHasOverlappingRendering() { return true; }
  public boolean getFilterTouchesWhenObscured() { return false; }
  public void setFilterTouchesWhenObscured(boolean b) {}
  public boolean onFilterTouchEventForSecurity(MotionEvent e) { return true; }
  public float getTransitionAlpha() { return 1; }
  public void setTransitionAlpha(float a) {}
  public long getUniqueDrawingId() { return System.identityHashCode(this); }
  public boolean isHorizontalFadingEdgeEnabled() { return false; }
  public boolean isVerticalFadingEdgeEnabled() { return false; }
  public int getHorizontalFadingEdgeLength() { return 0; }
  public int getVerticalFadingEdgeLength() { return 0; }
  public boolean isScrollbarFadingEnabled() { return true; }
  public int getScrollBarDefaultDelayBeforeFade() { return 300; }
  public void setScrollBarDefaultDelayBeforeFade(int d) {}
  public int getScrollBarFadeDuration() { return 250; }
  public void setScrollBarFadeDuration(int d) {}
  public void setPointerIcon(PointerIcon icon) { mPointerIcon = icon; }
  public PointerIcon getPointerIcon() { return mPointerIcon; }
  public PointerIcon onResolvePointerIcon(MotionEvent event, int pointerIndex) { return mPointerIcon; }
  PointerIcon mPointerIcon;
  // ---- nested scrolling (no platform parent takes part; AndroidX parents use their own interfaces)
  public void setNestedScrollingEnabled(boolean e) { mNestedScrollingEnabled = e; }
  public boolean isNestedScrollingEnabled() { return mNestedScrollingEnabled; }
  public boolean startNestedScroll(int axes) {
    if (!mNestedScrollingEnabled) return false;
    for (ViewParent p = getParent(); p instanceof ViewGroup; p = p.getParent()) {
      ViewGroup g = (ViewGroup) p;
      if (g.onStartNestedScroll(this, this, axes)) { mNestedScrollingParent = g; g.onNestedScrollAccepted(this, this, axes); return true; }
    }
    return false;
  }
  ViewGroup mNestedScrollingParent;
  public void stopNestedScroll() { if (mNestedScrollingParent != null) { mNestedScrollingParent.onStopNestedScroll(this); mNestedScrollingParent = null; } }
  public boolean hasNestedScrollingParent() { return mNestedScrollingParent != null; }
  public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
    if (mNestedScrollingParent == null) return false;
    mNestedScrollingParent.onNestedScroll(this, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    if (offsetInWindow != null) { offsetInWindow[0] = 0; offsetInWindow[1] = 0; }
    return true;
  }
  public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
    if (mNestedScrollingParent == null) return false;
    int[] c = consumed != null ? consumed : new int[2];
    c[0] = 0; c[1] = 0;
    mNestedScrollingParent.onNestedPreScroll(this, dx, dy, c);
    if (offsetInWindow != null) { offsetInWindow[0] = 0; offsetInWindow[1] = 0; }
    return c[0] != 0 || c[1] != 0;
  }
  public boolean dispatchNestedFling(float vx, float vy, boolean consumed) { return mNestedScrollingParent != null && mNestedScrollingParent.onNestedFling(this, vx, vy, consumed); }
  public boolean dispatchNestedPreFling(float vx, float vy) { return mNestedScrollingParent != null && mNestedScrollingParent.onNestedPreFling(this, vx, vy); }
  protected void onDraw(Canvas canvas) {}
  protected void dispatchDraw(Canvas canvas) {}
  protected final void onDrawScrollBars(Canvas canvas) {}
  protected long getDrawingTime() { return mAttachInfo != null ? mAttachInfo.drawingTime : SystemClock.uptimeMillis(); }

  /** Draws this view as a child of parent (ViewGroup.drawChild): offsets, clip, animation, alpha. */
  boolean drawAsChild(Canvas canvas, ViewGroup parent, long drawingTime) {
    boolean more = false;
    Transformation transformToApply = null;
    final Animation a = mCurrentAnimation;
    if (a != null) {
      Transformation t = parent.mChildTransformation;
      if (!a.isInitialized()) {
        a.initialize(getWidth(), getHeight(), parent.getWidth(), parent.getHeight());
        a.initializeInvalidateArea(0, 0, getWidth(), getHeight());
        if (mAttachInfo != null) a.setListenerHandler(mAttachInfo.handler);
        onAnimationStart();
      }
      t.clear();
      more = a.getTransformation(drawingTime, t, 1f);
      transformToApply = t;
      if (more) parent.invalidate();
    }
    if (parent.mStaticTransformationsEnabled) {
      Transformation st = parent.mChildTransformation2;
      st.clear();
      if (parent.getChildStaticTransformation(this, st)) {
        if (transformToApply == null) transformToApply = st;
        else { Transformation c = new Transformation(); c.set(transformToApply); c.compose(st); transformToApply = c; }
      }
    }
    computeScroll();
    int sx = mScrollX, sy = mScrollY;
    int restoreTo = canvas.save();
    canvas.translate(mLeft, mTop);
    float alpha = mAlpha;
    Matrix total = hasTransform() ? new Matrix(getMatrix()) : null;
    if (transformToApply != null) {
      int type = transformToApply.getTransformationType();
      if ((type & Transformation.TYPE_MATRIX) != 0) { if (total == null) total = new Matrix(); total.preConcat(transformToApply.getMatrix()); }
      if ((type & Transformation.TYPE_ALPHA) != 0) alpha *= transformToApply.getAlpha();
    }
    boolean edgeOn = false;
    if (total != null) {
      // perspective (3D rotations, Camera animations): the nearest affine map over this view's bounds
      if (!total.isAffine()) { Matrix near = Canvas.affineNear(total, 0, 0, getWidth(), getHeight()); edgeOn = near == null; total = near; }
      if (total != null) canvas.concat(total);
    }
    boolean skip = alpha <= 0.002f || edgeOn;
    if (!skip) {
      if (alpha < 1) canvas.multiplyAlpha(alpha);
      if (parent.mClipChildren) canvas.clipRect(0, 0, getWidth(), getHeight());
      canvas.translate(-sx, -sy);
      draw(canvas);
    }
    canvas.restoreToCount(restoreTo);
    if (a != null && !more) parent.finishAnimatingView(this, a);
    return more;
  }

  // ---------------------------------------------------------------- animations
  public void startAnimation(Animation a) {
    a.setStartTime(Animation.START_ON_FIRST_FRAME);
    setAnimation(a);
    invalidateParentCaches();
    invalidate(true);
  }
  public void clearAnimation() {
    if (mCurrentAnimation != null) mCurrentAnimation.detach();
    mCurrentAnimation = null;
    invalidateParentIfNeeded();
  }
  public void setAnimation(Animation a) {
    mCurrentAnimation = a;
    if (a != null) a.reset();
  }
  public Animation getAnimation() { return mCurrentAnimation; }
  protected void onAnimationStart() { mPrivateFlags |= PFLAG_ANIMATION_STARTED; }
  protected void onAnimationEnd() { mPrivateFlags &= ~PFLAG_ANIMATION_STARTED; }
  protected boolean onSetAlpha(int alpha) { return false; }
  public boolean hasTransientState() { return false; }

  // ---------------------------------------------------------------- finding views
  public final View findViewById(int id) { return id < 0 && id != NO_ID ? null : findViewTraversal(id); }
  protected View findViewTraversal(int id) { return id == mID ? this : null; }
  public final View findViewWithTag(Object tag) { return tag == null ? null : findViewWithTagTraversal(tag); }
  protected View findViewWithTagTraversal(Object tag) { return tag.equals(mTag) ? this : null; }
  public void findViewsWithText(ArrayList<View> out, CharSequence text, int flags) {}

  // ---------------------------------------------------------------- touch
  public boolean dispatchTouchEvent(MotionEvent event) {
    if (mOnTouchListener != null && (mViewFlags & ENABLED_MASK) == ENABLED && mOnTouchListener.onTouch(this, event)) return true;
    return onTouchEvent(event);
  }
  public boolean onInterceptTouchEvent(MotionEvent ev) { return false; }
  /** pointer events (hover, scroll) to the view under the pointer, others to the focused view; then this view's listener and onGenericMotionEvent */
  public boolean dispatchGenericMotionEvent(MotionEvent event) {
    if ((event.getSource() & InputDevice.SOURCE_CLASS_POINTER) != 0) {
      int action = event.getAction();
      if (action == MotionEvent.ACTION_HOVER_ENTER || action == MotionEvent.ACTION_HOVER_MOVE || action == MotionEvent.ACTION_HOVER_EXIT) {
        if (dispatchHoverEvent(event)) return true;
      } else if (dispatchGenericPointerEvent(event)) return true;
    } else if (dispatchGenericFocusedEvent(event)) return true;
    return dispatchGenericMotionEventInternal(event);
  }
  boolean dispatchGenericMotionEventInternal(MotionEvent event) {
    if (mOnGenericMotionListener != null && (mViewFlags & ENABLED_MASK) == ENABLED && mOnGenericMotionListener.onGenericMotion(this, event)) return true;
    return onGenericMotionEvent(event);
  }
  protected boolean dispatchHoverEvent(MotionEvent event) {
    if (mOnHoverListener != null && (mViewFlags & ENABLED_MASK) == ENABLED && mOnHoverListener.onHover(this, event)) return true;
    return onHoverEvent(event);
  }
  protected boolean dispatchGenericPointerEvent(MotionEvent event) { return false; }
  protected boolean dispatchGenericFocusedEvent(MotionEvent event) { return false; }
  public boolean onGenericMotionEvent(MotionEvent e) { return false; }
  boolean isHoverable() { return (mViewFlags & ENABLED_MASK) == ENABLED && (isClickable() || isLongClickable() || isContextClickable()); }
  public boolean onTrackballEvent(MotionEvent e) { return false; }
  public boolean dispatchTrackballEvent(MotionEvent e) { return onTrackballEvent(e); }
  /** hoverable (clickable) or hovered views track the hover state from ENTER and EXIT, and see the event in onGenericMotionEvent too */
  public boolean onHoverEvent(MotionEvent event) {
    if (isHoverable() || isHovered()) {
      switch (event.getActionMasked()) {
        case MotionEvent.ACTION_HOVER_ENTER: setHovered(true); break;
        case MotionEvent.ACTION_HOVER_EXIT: setHovered(false); break;
      }
      dispatchGenericMotionEventInternal(event);
      return true;
    }
    return false;
  }
  /** the vertical scroll per unit of AXIS_VSCROLL */
  protected float getVerticalScrollFactor() { return ViewConfiguration.get(mContext).getScaledVerticalScrollFactor(); }
  protected float getHorizontalScrollFactor() { return ViewConfiguration.get(mContext).getScaledHorizontalScrollFactor(); }
  public boolean isInScrollingContainer() {
    ViewParent p = getParent();
    while (p instanceof ViewGroup) {
      if (((ViewGroup) p).shouldDelayChildPressedState()) return true;
      p = p.getParent();
    }
    return false;
  }
  boolean pointInView(float x, float y, float slop) {
    return x >= -slop && y >= -slop && x < ((mRight - mLeft) + slop) && y < ((mBottom - mTop) + slop);
  }
  public boolean onTouchEvent(MotionEvent event) {
    final int viewFlags = mViewFlags;
    if (mTouchDelegate != null && mTouchDelegate.onTouchEvent(event)) return true;
    if ((viewFlags & ENABLED_MASK) == DISABLED) {
      if (event.getAction() == MotionEvent.ACTION_UP && (mPrivateFlags & PFLAG_PRESSED) != 0) setPressed(false);
      return ((viewFlags & CLICKABLE) == CLICKABLE || (viewFlags & LONG_CLICKABLE) == LONG_CLICKABLE);
    }
    if (((viewFlags & CLICKABLE) == CLICKABLE || (viewFlags & LONG_CLICKABLE) == LONG_CLICKABLE)) {
      switch (event.getActionMasked()) {
        case MotionEvent.ACTION_UP: {
          boolean prepressed = (mPrivateFlags & PFLAG_PREPRESSED) != 0;
          if ((mPrivateFlags & PFLAG_PRESSED) != 0 || prepressed) {
            boolean focusTaken = false;
            if (isFocusable() && isFocusableInTouchMode() && !isFocused()) focusTaken = requestFocus();
            if (prepressed) setPressed(true);
            if (!mHasPerformedLongPress) {
              removeLongPressCallback();
              if (!focusTaken) {
                if (mPerformClick == null) mPerformClick = new PerformClick();
                if (!post(mPerformClick)) performClick();
              }
            }
            if (mUnsetPressedState == null) mUnsetPressedState = new UnsetPressedState();
            if (prepressed) postDelayed(mUnsetPressedState, ViewConfiguration.getPressedStateDuration());
            else if (!post(mUnsetPressedState)) mUnsetPressedState.run();
            removeTapCallback();
          }
          break;
        }
        case MotionEvent.ACTION_DOWN: {
          mHasPerformedLongPress = false;
          if (isInScrollingContainer()) {
            mPrivateFlags |= PFLAG_PREPRESSED;
            if (mPendingCheckForTap == null) mPendingCheckForTap = new CheckForTap();
            postDelayed(mPendingCheckForTap, ViewConfiguration.getTapTimeout());
          } else {
            setPressed(true);
            checkForLongClick(0);
          }
          break;
        }
        case MotionEvent.ACTION_CANCEL:
          setPressed(false);
          removeTapCallback();
          removeLongPressCallback();
          break;
        case MotionEvent.ACTION_MOVE: {
          final int x = (int) event.getX(), y = (int) event.getY();
          if (!pointInView(x, y, mTouchSlop)) {
            removeTapCallback();
            if ((mPrivateFlags & PFLAG_PRESSED) != 0) { removeLongPressCallback(); setPressed(false); }
          }
          break;
        }
      }
      return true;
    }
    return false;
  }
  public void cancelLongPress() { removeLongPressCallback(); removeTapCallback(); }
  private void removeLongPressCallback() { if (mPendingCheckForLongPress != null) removeCallbacks(mPendingCheckForLongPress); }
  private void removeUnsetPressCallback() { if ((mPrivateFlags & PFLAG_PRESSED) != 0 && mUnsetPressedState != null) { setPressed(false); removeCallbacks(mUnsetPressedState); } }
  private void removeTapCallback() { if (mPendingCheckForTap != null) { mPrivateFlags &= ~PFLAG_PREPRESSED; removeCallbacks(mPendingCheckForTap); } }
  private void checkForLongClick(int delayOffset) {
    if ((mViewFlags & LONG_CLICKABLE) == LONG_CLICKABLE) {
      mHasPerformedLongPress = false;
      if (mPendingCheckForLongPress == null) mPendingCheckForLongPress = new CheckForLongPress();
      mPendingCheckForLongPress.windowAttachCount = mWindowAttachCount;
      postDelayed(mPendingCheckForLongPress, ViewConfiguration.getLongPressTimeout() - delayOffset);
    }
  }
  final class CheckForLongPress implements Runnable {
    int windowAttachCount;
    public void run() {
      if (isPressed() && mParent != null && windowAttachCount == mWindowAttachCount) {
        if (performLongClick()) mHasPerformedLongPress = true;
      }
    }
  }
  final class CheckForTap implements Runnable {
    public void run() {
      mPrivateFlags &= ~PFLAG_PREPRESSED;
      setPressed(true);
      checkForLongClick(ViewConfiguration.getTapTimeout());
    }
  }
  final class PerformClick implements Runnable { public void run() { performClick(); } }
  final class UnsetPressedState implements Runnable { public void run() { setPressed(false); } }

  // ---------------------------------------------------------------- keys
  public boolean dispatchKeyEvent(KeyEvent event) {
    if (mOnKeyListener != null && (mViewFlags & ENABLED_MASK) == ENABLED && mOnKeyListener.onKey(this, event.getKeyCode(), event)) return true;
    return event.dispatch(this, mAttachInfo != null ? mAttachInfo.root.keyDispatchState : null, this);
  }
  public boolean dispatchKeyEventPreIme(KeyEvent e) { return onKeyPreIme(e.getKeyCode(), e); }
  public boolean dispatchKeyShortcutEvent(KeyEvent e) { return onKeyShortcut(e.getKeyCode(), e); }
  public boolean onKeyPreIme(int k, KeyEvent e) { return false; }
  static boolean isConfirmKey(int keyCode) {
    return keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_SPACE || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER;
  }
  /** a confirm key (center, enter, space) presses a clickable view and starts the long-press timer */
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (isConfirmKey(keyCode) && event.hasNoModifiers()) {
      if ((mViewFlags & ENABLED_MASK) == DISABLED) return true;
      if (event.getRepeatCount() == 0 && ((mViewFlags & CLICKABLE) == CLICKABLE || (mViewFlags & LONG_CLICKABLE) == LONG_CLICKABLE)) {
        if ((mViewFlags & CLICKABLE) == CLICKABLE) setPressed(true);
        mHasPerformedLongPress = false;
        if ((mViewFlags & LONG_CLICKABLE) == LONG_CLICKABLE) {
          if (mPendingCheckForLongPress == null) mPendingCheckForLongPress = new CheckForLongPress();
          mPendingCheckForLongPress.windowAttachCount = mWindowAttachCount;
          removeCallbacks(mPendingCheckForLongPress);
          postDelayed(mPendingCheckForLongPress, ViewConfiguration.getLongPressTimeout());
        }
        return true;
      }
    }
    return false;
  }
  public boolean onKeyLongPress(int keyCode, KeyEvent event) { return false; }
  /** ...and its release clicks it, unless the long press fired or the key was canceled */
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    if (isConfirmKey(keyCode) && event.hasNoModifiers()) {
      if ((mViewFlags & ENABLED_MASK) == DISABLED) return true;
      if ((mViewFlags & CLICKABLE) == CLICKABLE && isPressed()) {
        setPressed(false);
        if (!mHasPerformedLongPress) {
          removeLongPressCallback();
          if (!event.isCanceled()) return performClick();
        }
      }
    }
    return false;
  }
  public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) { return false; }
  public boolean onKeyShortcut(int k, KeyEvent e) { return false; }
  public KeyEvent.DispatcherState getKeyDispatcherState() { return mAttachInfo != null ? mAttachInfo.root.keyDispatchState : null; }

  // ---------------------------------------------------------------- saved state (Activity.onSaveInstanceState, recreate)
  private boolean mSaveStateCalled;
  public void saveHierarchyState(SparseArray<android.os.Parcelable> container) { dispatchSaveInstanceState(container); }
  public void restoreHierarchyState(SparseArray<android.os.Parcelable> container) { dispatchRestoreInstanceState(container); }
  protected android.os.Parcelable onSaveInstanceState() { mSaveStateCalled = true; return BaseSavedState.EMPTY_STATE; }
  protected void onRestoreInstanceState(android.os.Parcelable state) {
    mSaveStateCalled = true;
    if (state != null && !(state instanceof AbsSavedState))
      throw new IllegalArgumentException("Wrong state class, expecting View State but received " + state.getClass().toString() + " instead. This usually happens "
          + "when two views of different type have the same id in the same hierarchy. This view's id is " + getId() + ". Make sure other views do not use the same id.");
  }
  /** The base of a view's saved state (extend it in a custom view's onSaveInstanceState). */
  public static class BaseSavedState extends AbsSavedState {
    public BaseSavedState(android.os.Parcel source) { this(source, null); }
    public BaseSavedState(android.os.Parcel source, ClassLoader loader) { super(source, loader); }
    public BaseSavedState(android.os.Parcelable superState) { super(superState); }
    @Override public void writeToParcel(android.os.Parcel out, int flags) { super.writeToParcel(out, flags); }
    public static final android.os.Parcelable.Creator<BaseSavedState> CREATOR = new android.os.Parcelable.ClassLoaderCreator<BaseSavedState>() {
      public BaseSavedState createFromParcel(android.os.Parcel in) { return new BaseSavedState(in); }
      public BaseSavedState createFromParcel(android.os.Parcel in, ClassLoader loader) { return new BaseSavedState(in, loader); }
      public BaseSavedState[] newArray(int size) { return new BaseSavedState[size]; }
    };
  }

  // ---------------------------------------------------------------- misc
  public static View inflate(Context context, int resource, ViewGroup root) { return LayoutInflater.from(context).inflate(resource, root); }
  public void bringToFront() { if (mParent != null) mParent.bringChildToFront(this); }
  public void setOnScrollChangeListener(OnScrollChangeListener l) { mOnScrollChangeListener = l; }
  public boolean isScrollContainer() { return false; }
  public void setScrollContainer(boolean b) {}
  protected boolean fitSystemWindows(Rect r) { return false; }
  public String toString() {
    String n = getClass().getName();
    return n.substring(n.lastIndexOf('.') + 1) + "{" + Integer.toHexString(System.identityHashCode(this)) + (mID != NO_ID ? " #" + Integer.toHexString(mID) : "")
        + " " + mLeft + "," + mTop + "-" + mRight + "," + mBottom + "}";
  }

  // ================================================================ state of features a converted app's device does not run:
  // accessibility services, autofill, content capture, handwriting, credentials, translation, keyboard clusters,
  // pointer capture and drag and drop between windows. Each value is kept as set, and reported as on a device where
  // the feature is idle (no accessibility service focuses views, no autofill session asks for structure).
  private int mAccessibilityTraversalBefore = NO_ID, mAccessibilityTraversalAfter = NO_ID, mAccessibilityDataSensitive = ACCESSIBILITY_DATA_SENSITIVE_AUTO;
  private boolean mAccessibilityHeading, mScreenReaderFocusable, mKeyboardNavigationCluster, mRevealOnFocusHint = true, mPreferKeepClear;
  private CharSequence mAccessibilityPaneTitle;
  private String[] mAutofillHints;
  private android.view.autofill.AutofillId mAutofillId;
  private int mImportantForAutofill = IMPORTANT_FOR_AUTOFILL_AUTO, mImportantForContentCapture = IMPORTANT_FOR_CONTENT_CAPTURE_AUTO;
  private android.view.contentcapture.ContentCaptureSession mContentCaptureSession;
  private int mContentSensitivity = CONTENT_SENSITIVITY_AUTO;
  private boolean mAutoHandwritingEnabled = true, mHandwritingDelegate, mIsCredential, mForceDarkAllowed = true, mAllowClickWhenDisabled;
  private float mHandwritingOffsetLeft, mHandwritingOffsetTop, mHandwritingOffsetRight, mHandwritingOffsetBottom;
  private int mHandwritingDelegateFlags;
  private Runnable mHandwritingDelegatorCallback;
  private String mAllowedHandwritingDelegatePackage, mAllowedHandwritingDelegatorPackage;
  private int mNextClusterForwardId = NO_ID;
  private int mOutlineAmbientShadowColor = 0xff000000, mOutlineSpotShadowColor = 0xff000000;
  private Matrix mAnimationMatrix;
  private android.graphics.BlendMode mBackgroundTintBlendMode, mForegroundTintBlendMode;
  private Drawable mVerticalThumb, mVerticalTrack, mHorizontalThumb, mHorizontalTrack;
  private java.util.List<android.graphics.Rect> mSystemGestureExclusionRects = java.util.Collections.emptyList(), mPreferKeepClearRects = java.util.Collections.emptyList();
  private float mRequestedFrameRate = REQUESTED_FRAME_RATE_CATEGORY_DEFAULT, mFrameContentVelocity;
  private int mScrollCaptureHint = SCROLL_CAPTURE_HINT_AUTO;
  private ScrollCaptureCallback mScrollCaptureCallback;
  private String[] mReceiveContentMimeTypes;
  private OnReceiveContentListener mOnReceiveContentListener;
  private OnCapturedPointerListener mOnCapturedPointerListener;
  private OnScrollChangeListener mOnScrollChangeListener;
  private java.util.ArrayList<OnUnhandledKeyEventListener> mUnhandledKeyListeners;
  private android.graphics.RenderEffect mRenderEffect;
  private Paint mLayerPaint;
  private int mTransientStateCount;
  private android.view.translation.ViewTranslationCallback mViewTranslationCallback;
  private android.view.translation.ViewTranslationResponse mViewTranslationResponse;
  private WindowInsetsAnimation.Callback mWindowInsetsAnimationCallback;
  private android.credentials.GetCredentialRequest mPendingCredentialRequest;
  private android.os.OutcomeReceiver<android.credentials.GetCredentialResponse, android.credentials.GetCredentialException> mPendingCredentialCallback;
  private int mSourceLayoutResId;

  /** Interface definition for a callback when the scroll position of a view changes. */
  public interface OnScrollChangeListener { void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY); }
  /** Interface definition for a callback when a captured pointer event is dispatched to this view. */
  public interface OnCapturedPointerListener { boolean onCapturedPointer(View view, MotionEvent event); }
  /** Interface definition for a callback for key events no view handled. */
  public interface OnUnhandledKeyEventListener { boolean onUnhandledKeyEvent(View v, KeyEvent event); }

  // ---- accessibility (no accessibility service is running)
  public int getAccessibilityTraversalBefore() { return mAccessibilityTraversalBefore; }
  public void setAccessibilityTraversalBefore(int beforeId) { mAccessibilityTraversalBefore = beforeId; }
  public int getAccessibilityTraversalAfter() { return mAccessibilityTraversalAfter; }
  public void setAccessibilityTraversalAfter(int afterId) { mAccessibilityTraversalAfter = afterId; }
  public boolean isAccessibilityHeading() { return mAccessibilityHeading; }
  public void setAccessibilityHeading(boolean isHeading) { mAccessibilityHeading = isHeading; }
  public boolean isScreenReaderFocusable() { return mScreenReaderFocusable; }
  public void setScreenReaderFocusable(boolean screenReaderFocusable) { mScreenReaderFocusable = screenReaderFocusable; }
  public CharSequence getAccessibilityPaneTitle() { return mAccessibilityPaneTitle; }
  public void setAccessibilityPaneTitle(CharSequence accessibilityPaneTitle) { mAccessibilityPaneTitle = accessibilityPaneTitle; }
  public boolean isAccessibilityDataSensitive() {
    if (mAccessibilityDataSensitive == ACCESSIBILITY_DATA_SENSITIVE_YES) return true;
    if (mAccessibilityDataSensitive == ACCESSIBILITY_DATA_SENSITIVE_NO) return false;
    return mParent instanceof View && ((View) mParent).isAccessibilityDataSensitive();
  }
  public void setAccessibilityDataSensitive(int accessibilityDataSensitive) { mAccessibilityDataSensitive = accessibilityDataSensitive; }
  /** no accessibility service puts its focus on views */
  public boolean isAccessibilityFocused() { return false; }
  public android.view.accessibility.AccessibilityNodeProvider getAccessibilityNodeProvider() {
    return mAccessibilityDelegate != null ? mAccessibilityDelegate.getAccessibilityNodeProvider(this) : null;
  }
  public ViewParent getParentForAccessibility() {
    for (ViewParent p = mParent; p != null; p = p.getParent())
      if (!(p instanceof View) || ((View) p).getImportantForAccessibility() != IMPORTANT_FOR_ACCESSIBILITY_NO) return p;
    return null;
  }
  public void addChildrenForAccessibility(java.util.ArrayList<View> outChildren) {}
  public android.view.accessibility.AccessibilityNodeInfo createAccessibilityNodeInfo() {
    android.view.accessibility.AccessibilityNodeProvider p = getAccessibilityNodeProvider();
    if (p != null) return p.createAccessibilityNodeInfo(android.view.accessibility.AccessibilityNodeProvider.HOST_VIEW_ID);
    android.view.accessibility.AccessibilityNodeInfo info = android.view.accessibility.AccessibilityNodeInfo.obtain(this);
    onInitializeAccessibilityNodeInfo(info);
    return info;
  }
  public void onInitializeAccessibilityNodeInfo(android.view.accessibility.AccessibilityNodeInfo info) {
    if (mAccessibilityDelegate != null) { mAccessibilityDelegate.onInitializeAccessibilityNodeInfo(this, info); return; }
    onInitializeAccessibilityNodeInfoInternal(info);
  }
  void onInitializeAccessibilityNodeInfoInternal(android.view.accessibility.AccessibilityNodeInfo info) {
    android.graphics.Rect r = new android.graphics.Rect(0, 0, getWidth(), getHeight());
    r.offset(getLeft(), getTop());
    info.setBoundsInParent(r);
    int[] loc = new int[2];
    getLocationOnScreen(loc);
    info.setBoundsInScreen(new android.graphics.Rect(loc[0], loc[1], loc[0] + getWidth(), loc[1] + getHeight()));
    ViewParent parent = getParentForAccessibility();
    if (parent instanceof View) info.setParent((View) parent);
    if (mContext != null) info.setPackageName(mContext.getPackageName());
    info.setClassName(getAccessibilityClassName());
    info.setContentDescription(getContentDescription());
    info.setEnabled(isEnabled());
    info.setClickable(isClickable());
    info.setFocusable(isFocusable());
    info.setFocused(isFocused());
    info.setSelected(isSelected());
    info.setLongClickable(isLongClickable());
    info.setVisibleToUser(isShown());
    if (isFocusable()) info.addAction(isFocused() ? android.view.accessibility.AccessibilityNodeInfo.ACTION_CLEAR_FOCUS : android.view.accessibility.AccessibilityNodeInfo.ACTION_FOCUS);
    info.addAction(isSelected() ? android.view.accessibility.AccessibilityNodeInfo.ACTION_CLEAR_SELECTION : android.view.accessibility.AccessibilityNodeInfo.ACTION_SELECT);
    if (isClickable() && isEnabled()) info.addAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK);
    if (isLongClickable() && isEnabled()) info.addAction(android.view.accessibility.AccessibilityNodeInfo.ACTION_LONG_CLICK);
  }
  public void addExtraDataToAccessibilityNodeInfo(android.view.accessibility.AccessibilityNodeInfo info, String extraDataKey, android.os.Bundle arguments) {}
  public void onInitializeAccessibilityEvent(android.view.accessibility.AccessibilityEvent event) {
    if (mAccessibilityDelegate != null) { mAccessibilityDelegate.onInitializeAccessibilityEvent(this, event); return; }
    event.setSource(this);
    event.setClassName(getAccessibilityClassName());
    if (mContext != null) event.setPackageName(mContext.getPackageName());
    event.setEnabled(isEnabled());
    event.setContentDescription(getContentDescription());
  }
  public void onPopulateAccessibilityEvent(android.view.accessibility.AccessibilityEvent event) {
    if (mAccessibilityDelegate != null) mAccessibilityDelegate.onPopulateAccessibilityEvent(this, event);
  }
  public boolean dispatchPopulateAccessibilityEvent(android.view.accessibility.AccessibilityEvent event) {
    if (mAccessibilityDelegate != null) return mAccessibilityDelegate.dispatchPopulateAccessibilityEvent(this, event);
    onPopulateAccessibilityEvent(event);
    return false;
  }
  /** events go to accessibility services: none run, so there is nobody to send them to */
  public void sendAccessibilityEventUnchecked(android.view.accessibility.AccessibilityEvent event) {
    if (mAccessibilityDelegate != null) mAccessibilityDelegate.sendAccessibilityEventUnchecked(this, event);
  }
  public boolean performAccessibilityAction(int action, android.os.Bundle arguments) {
    if (mAccessibilityDelegate != null) return mAccessibilityDelegate.performAccessibilityAction(this, action, arguments);
    return performAccessibilityActionInternal(action, arguments);
  }
  boolean performAccessibilityActionInternal(int action, android.os.Bundle arguments) {
    switch (action) {
      case android.view.accessibility.AccessibilityNodeInfo.ACTION_CLICK: if (isClickable() && isEnabled()) { performClick(); return true; } return false;
      case android.view.accessibility.AccessibilityNodeInfo.ACTION_LONG_CLICK: if (isLongClickable() && isEnabled()) return performLongClick();
        return false;
      case android.view.accessibility.AccessibilityNodeInfo.ACTION_FOCUS: return !isFocused() && requestFocus();
      case android.view.accessibility.AccessibilityNodeInfo.ACTION_CLEAR_FOCUS: if (isFocused()) { clearFocus(); return true; } return false;
      case android.view.accessibility.AccessibilityNodeInfo.ACTION_SELECT: if (!isSelected()) { setSelected(true); return true; } return false;
      case android.view.accessibility.AccessibilityNodeInfo.ACTION_CLEAR_SELECTION: if (isSelected()) { setSelected(false); return true; } return false;
      default: return false;
    }
  }
  public boolean dispatchNestedPrePerformAccessibilityAction(int action, android.os.Bundle arguments) {
    for (ViewParent p = mParent; p != null; p = p.getParent())
      if (p.onNestedPrePerformAccessibilityAction(this, action, arguments)) return true;
    return false;
  }

  // ---- autofill and content capture (no autofill service, no capture session)
  public void setAutofillHints(String... autofillHints) { mAutofillHints = autofillHints == null || autofillHints.length == 0 ? null : autofillHints; }
  public String[] getAutofillHints() { return mAutofillHints; }
  public final android.view.autofill.AutofillId getAutofillId() {
    if (mAutofillId == null) mAutofillId = android.view.autofill.AutofillId.forView(getAutofillViewId());
    return mAutofillId;
  }
  private int getAutofillViewId() { if (mAutofillViewId == 0) mAutofillViewId = 1000000 + (++sNextAutofillId); return mAutofillViewId; }
  private int mAutofillViewId;
  private static int sNextAutofillId;
  public void setAutofillId(android.view.autofill.AutofillId id) {
    if (isAttachedToWindow()) throw new IllegalStateException("Cannot set autofill id when view is attached");
    mAutofillId = id;
  }
  /** a plain view has no value to fill: AUTOFILL_TYPE_NONE (widgets with values override these) */
  public int getAutofillType() { return AUTOFILL_TYPE_NONE; }
  public android.view.autofill.AutofillValue getAutofillValue() { return null; }
  public void autofill(android.view.autofill.AutofillValue value) {}
  public void autofill(android.util.SparseArray<android.view.autofill.AutofillValue> values) {}
  public int getImportantForAutofill() { return mImportantForAutofill; }
  public void setImportantForAutofill(int mode) { mImportantForAutofill = mode; }
  public final boolean isImportantForAutofill() {
    int m = mImportantForAutofill;
    if (m == IMPORTANT_FOR_AUTOFILL_NO || m == IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS) return false;
    if (m == IMPORTANT_FOR_AUTOFILL_YES || m == IMPORTANT_FOR_AUTOFILL_YES_EXCLUDE_DESCENDANTS) return true;
    for (ViewParent p = mParent; p instanceof View; p = p.getParent()) {
      int pm = ((View) p).mImportantForAutofill;
      if (pm == IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS || pm == IMPORTANT_FOR_AUTOFILL_YES_EXCLUDE_DESCENDANTS) return false;
    }
    return getAutofillType() != AUTOFILL_TYPE_NONE || mAutofillHints != null;
  }
  public boolean isVisibleToUserForAutofill(int virtualId) { return isShown(); }
  public void onProvideAutofillStructure(ViewStructure structure, int flags) { onProvideStructure(structure); }
  public void onProvideAutofillVirtualStructure(ViewStructure structure, int flags) {}
  public void dispatchProvideAutofillStructure(ViewStructure structure, int flags) { onProvideAutofillStructure(structure, flags); }
  public void onProvideStructure(ViewStructure structure) {
    structure.setId(mID, null, null, null);
    structure.setDimens(mLeft, mTop, mScrollX, mScrollY, getWidth(), getHeight());
    structure.setVisibility(getVisibility());
    structure.setEnabled(isEnabled());
    structure.setClickable(isClickable());
    structure.setFocusable(isFocusable());
    structure.setFocused(isFocused());
    structure.setSelected(isSelected());
    structure.setLongClickable(isLongClickable());
    structure.setClassName(getAccessibilityClassName().toString());
    structure.setContentDescription(getContentDescription());
  }
  public void onProvideVirtualStructure(ViewStructure structure) {}
  public void dispatchProvideStructure(ViewStructure structure) { onProvideStructure(structure); }
  public void onProvideContentCaptureStructure(ViewStructure structure, int flags) { onProvideStructure(structure); }
  public int getImportantForContentCapture() { return mImportantForContentCapture; }
  public void setImportantForContentCapture(int mode) { mImportantForContentCapture = mode; }
  public final boolean isImportantForContentCapture() {
    int m = mImportantForContentCapture;
    if (m == IMPORTANT_FOR_CONTENT_CAPTURE_NO || m == IMPORTANT_FOR_CONTENT_CAPTURE_NO_EXCLUDE_DESCENDANTS) return false;
    if (m == IMPORTANT_FOR_CONTENT_CAPTURE_YES || m == IMPORTANT_FOR_CONTENT_CAPTURE_YES_EXCLUDE_DESCENDANTS) return true;
    return !(this instanceof ViewGroup);
  }
  public void setContentCaptureSession(android.view.contentcapture.ContentCaptureSession contentCaptureSession) { mContentCaptureSession = contentCaptureSession; }
  public final android.view.contentcapture.ContentCaptureSession getContentCaptureSession() {
    for (View v = this; v != null; v = v.mParent instanceof View ? (View) v.mParent : null) if (v.mContentCaptureSession != null) return v.mContentCaptureSession;
    return null;
  }
  public final void setContentSensitivity(int mode) { mContentSensitivity = mode; }
  public final int getContentSensitivity() { return mContentSensitivity; }
  public final boolean isContentSensitive() { return mContentSensitivity == CONTENT_SENSITIVITY_SENSITIVE; }

  // ---- handwriting, credentials, translation, dark theme
  public void setAutoHandwritingEnabled(boolean enabled) { mAutoHandwritingEnabled = enabled; }
  public boolean isAutoHandwritingEnabled() { return mAutoHandwritingEnabled; }
  public void setHandwritingBoundsOffsets(float offsetLeft, float offsetTop, float offsetRight, float offsetBottom) {
    mHandwritingOffsetLeft = offsetLeft; mHandwritingOffsetTop = offsetTop; mHandwritingOffsetRight = offsetRight; mHandwritingOffsetBottom = offsetBottom;
  }
  public float getHandwritingBoundsOffsetLeft() { return mHandwritingOffsetLeft; }
  public float getHandwritingBoundsOffsetTop() { return mHandwritingOffsetTop; }
  public float getHandwritingBoundsOffsetRight() { return mHandwritingOffsetRight; }
  public float getHandwritingBoundsOffsetBottom() { return mHandwritingOffsetBottom; }
  public void setHandwritingDelegatorCallback(Runnable callback) { mHandwritingDelegatorCallback = callback; }
  public Runnable getHandwritingDelegatorCallback() { return mHandwritingDelegatorCallback; }
  public void setIsHandwritingDelegate(boolean isHandwritingDelegate) { mHandwritingDelegate = isHandwritingDelegate; }
  public boolean isHandwritingDelegate() { return mHandwritingDelegate; }
  public void setHandwritingDelegateFlags(int flags) { mHandwritingDelegateFlags = flags; }
  public int getHandwritingDelegateFlags() { return mHandwritingDelegateFlags; }
  public void setAllowedHandwritingDelegatePackage(String allowedPackageName) { mAllowedHandwritingDelegatePackage = allowedPackageName; }
  public String getAllowedHandwritingDelegatePackageName() { return mAllowedHandwritingDelegatePackage; }
  public void setAllowedHandwritingDelegatorPackage(String allowedPackageName) { mAllowedHandwritingDelegatorPackage = allowedPackageName; }
  public String getAllowedHandwritingDelegatorPackageName() { return mAllowedHandwritingDelegatorPackage; }
  public void setIsCredential(boolean isCredential) { mIsCredential = isCredential; }
  public boolean isCredential() { return mIsCredential; }
  public void setPendingCredentialRequest(android.credentials.GetCredentialRequest request,
      android.os.OutcomeReceiver<android.credentials.GetCredentialResponse, android.credentials.GetCredentialException> callback) {
    mPendingCredentialRequest = request; mPendingCredentialCallback = callback;
  }
  public void clearPendingCredentialRequest() { mPendingCredentialRequest = null; mPendingCredentialCallback = null; }
  public final android.credentials.GetCredentialRequest getPendingCredentialRequest() { return mPendingCredentialRequest; }
  public final android.os.OutcomeReceiver<android.credentials.GetCredentialResponse, android.credentials.GetCredentialException> getPendingCredentialCallback() { return mPendingCredentialCallback; }
  public void setViewTranslationCallback(android.view.translation.ViewTranslationCallback callback) { mViewTranslationCallback = callback; }
  public void clearViewTranslationCallback() { mViewTranslationCallback = null; }
  public android.view.translation.ViewTranslationResponse getViewTranslationResponse() { return mViewTranslationResponse; }
  public void onViewTranslationResponse(android.view.translation.ViewTranslationResponse response) { mViewTranslationResponse = response; }
  public void onVirtualViewTranslationResponses(android.util.LongSparseArray<android.view.translation.ViewTranslationResponse> response) {}
  /** this view has no text of its own to translate */
  public void onCreateViewTranslationRequest(int[] supportedFormats, java.util.function.Consumer<android.view.translation.ViewTranslationRequest> requestsCollector) {}
  public void onCreateVirtualViewTranslationRequests(long[] virtualIds, int[] supportedFormats, java.util.function.Consumer<android.view.translation.ViewTranslationRequest> requestsCollector) {}
  public void dispatchCreateViewTranslationRequest(java.util.Map<android.view.autofill.AutofillId, long[]> viewIds, int[] supportedFormats,
      android.view.translation.TranslationCapability capability, java.util.List<android.view.translation.ViewTranslationRequest> requests) {
    if (viewIds.containsKey(getAutofillId())) onCreateViewTranslationRequest(supportedFormats, requests::add);
  }
  public void setForceDarkAllowed(boolean allow) { mForceDarkAllowed = allow; }
  public boolean isForceDarkAllowed() { return mForceDarkAllowed; }
  public void setAllowClickWhenDisabled(boolean clickableWhenDisabled) { mAllowClickWhenDisabled = clickableWhenDisabled; }

  // ---- keyboard navigation clusters and focus
  public void setKeyboardNavigationCluster(boolean isCluster) { mKeyboardNavigationCluster = isCluster; }
  public final boolean isKeyboardNavigationCluster() { return mKeyboardNavigationCluster; }
  public int getNextClusterForwardId() { return mNextClusterForwardId; }
  public void setNextClusterForwardId(int nextClusterForwardId) { mNextClusterForwardId = nextClusterForwardId; }
  public void addKeyboardNavigationClusters(java.util.Collection<View> views, int direction) {
    if (mKeyboardNavigationCluster && isShown() && isFocusable()) views.add(this);
  }
  public View keyboardNavigationClusterSearch(View currentCluster, int direction) {
    java.util.ArrayList<View> clusters = new java.util.ArrayList<>();
    getRootView().addKeyboardNavigationClusters(clusters, direction);
    if (clusters.isEmpty()) return null;
    int i = currentCluster == null ? -1 : clusters.indexOf(currentCluster);
    int n = clusters.size();
    int next = direction == FOCUS_BACKWARD ? (i <= 0 ? n - 1 : i - 1) : (i + 1) % n;
    return clusters.get(next);
  }
  public boolean restoreDefaultFocus() { return requestFocus(FOCUS_DOWN); }
  public final void setRevealOnFocusHint(boolean revealOnFocus) { mRevealOnFocusHint = revealOnFocus; }
  public final boolean getRevealOnFocusHint() { return mRevealOnFocusHint; }
  public boolean dispatchUnhandledMove(View focused, int direction) { return false; }

  // ---- drawing hints, shadows, scroll bars
  public void setOutlineAmbientShadowColor(int color) { mOutlineAmbientShadowColor = color; invalidate(); }
  public int getOutlineAmbientShadowColor() { return mOutlineAmbientShadowColor; }
  public void setOutlineSpotShadowColor(int color) { mOutlineSpotShadowColor = color; invalidate(); }
  public int getOutlineSpotShadowColor() { return mOutlineSpotShadowColor; }
  public void setAnimationMatrix(Matrix matrix) { mAnimationMatrix = matrix == null ? null : new Matrix(matrix); invalidate(); }
  public Matrix getAnimationMatrix() { return mAnimationMatrix; }
  public void setBackgroundTintBlendMode(android.graphics.BlendMode blendMode) { mBackgroundTintBlendMode = blendMode; }
  public android.graphics.BlendMode getBackgroundTintBlendMode() { return mBackgroundTintBlendMode; }
  public void setForegroundTintBlendMode(android.graphics.BlendMode blendMode) { mForegroundTintBlendMode = blendMode; }
  public android.graphics.BlendMode getForegroundTintBlendMode() { return mForegroundTintBlendMode; }
  public void setVerticalScrollbarThumbDrawable(Drawable drawable) { mVerticalThumb = drawable; }
  public void setVerticalScrollbarTrackDrawable(Drawable drawable) { mVerticalTrack = drawable; }
  public void setHorizontalScrollbarThumbDrawable(Drawable drawable) { mHorizontalThumb = drawable; }
  public void setHorizontalScrollbarTrackDrawable(Drawable drawable) { mHorizontalTrack = drawable; }
  public Drawable getVerticalScrollbarThumbDrawable() { return mVerticalThumb; }
  public Drawable getVerticalScrollbarTrackDrawable() { return mVerticalTrack; }
  public Drawable getHorizontalScrollbarThumbDrawable() { return mHorizontalThumb; }
  public Drawable getHorizontalScrollbarTrackDrawable() { return mHorizontalTrack; }
  public void setRenderEffect(android.graphics.RenderEffect renderEffect) { mRenderEffect = renderEffect; invalidate(); }
  public void setLayerPaint(Paint paint) { mLayerPaint = paint; invalidate(); }
  public int getDrawingCacheBackgroundColor() { return mDrawingCacheBackgroundColor; }
  public int getDrawingCacheQuality() { return mDrawingCacheQuality; }
  private int mDrawingCacheQuality = DRAWING_CACHE_QUALITY_AUTO;
  public void setRequestedFrameRate(float frameRate) { mRequestedFrameRate = frameRate; }
  public float getRequestedFrameRate() { return mRequestedFrameRate; }
  public void setFrameContentVelocity(float pixelsPerSecond) { mFrameContentVelocity = pixelsPerSecond; }
  public float getFrameContentVelocity() { return mFrameContentVelocity; }
  /** a hardware layer is drawn when the view is; there is nothing to build ahead of time */
  public void buildLayer() { if (mAttachInfo == null) throw new IllegalStateException("This view must be attached to a window first"); }
  public boolean gatherTransparentRegion(android.graphics.Region region) {
    if (region != null && mBackground != null && getVisibility() == VISIBLE) {
      int[] loc = new int[2];
      getLocationInWindow(loc);
      region.op(new android.graphics.Rect(loc[0], loc[1], loc[0] + getWidth(), loc[1] + getHeight()), android.graphics.Region.Op.DIFFERENCE);
    }
    return true;
  }
  public void setSystemGestureExclusionRects(java.util.List<android.graphics.Rect> rects) { mSystemGestureExclusionRects = rects == null || rects.isEmpty() ? java.util.Collections.emptyList() : new java.util.ArrayList<>(rects); }
  public java.util.List<android.graphics.Rect> getSystemGestureExclusionRects() { return mSystemGestureExclusionRects; }
  public final void setPreferKeepClear(boolean preferKeepClear) { mPreferKeepClear = preferKeepClear; }
  public final boolean isPreferKeepClear() { return mPreferKeepClear; }
  public final void setPreferKeepClearRects(java.util.List<android.graphics.Rect> rects) { mPreferKeepClearRects = rects == null ? java.util.Collections.emptyList() : new java.util.ArrayList<>(rects); }
  public final java.util.List<android.graphics.Rect> getPreferKeepClearRects() { return mPreferKeepClearRects; }
  /** the debug "show layout bounds" developer option is off */
  public final boolean isShowingLayoutBounds() { return false; }

  // ---- pivots, transforms, geometry
  public boolean isPivotSet() { return mPivotSet; }
  public void resetPivot() { if (mPivotSet) { mPivotSet = false; invalidate(); } }
  public final void setLeftTopRightBottom(int left, int top, int right, int bottom) { setFrame(left, top, right, bottom); }
  public void transformMatrixToGlobal(Matrix matrix) {
    if (mParent instanceof View) {
      View p = (View) mParent;
      p.transformMatrixToGlobal(matrix);
      matrix.preTranslate(-p.mScrollX, -p.mScrollY);
    }
    matrix.preTranslate(mLeft, mTop);
    if (!getMatrix().isIdentity()) matrix.preConcat(getMatrix());
  }
  public void transformMatrixToLocal(Matrix matrix) {
    if (mParent instanceof View) {
      View p = (View) mParent;
      p.transformMatrixToLocal(matrix);
      matrix.postTranslate(p.mScrollX, p.mScrollY);
    }
    matrix.postTranslate(-mLeft, -mTop);
    if (!getMatrix().isIdentity()) { Matrix inv = new Matrix(); if (getMatrix().invert(inv)) matrix.postConcat(inv); }
  }
  /** the window's surface starts at the window's origin */
  public void getLocationInSurface(int[] location) { getLocationInWindow(location); }

  // ---- input, pointer capture, drag and drop
  public boolean hasPointerCapture() { return mHasPointerCapture; }
  private boolean mHasPointerCapture;
  /** pointer capture (relative mouse events) needs the window's input channel: requests are not granted */
  public void requestPointerCapture() {}
  public void releasePointerCapture() { if (mHasPointerCapture) { mHasPointerCapture = false; dispatchPointerCaptureChanged(false); } }
  public void onPointerCaptureChange(boolean hasCapture) {}
  public void dispatchPointerCaptureChanged(boolean hasCapture) { onPointerCaptureChange(hasCapture); }
  public boolean onCapturedPointerEvent(MotionEvent event) { return false; }
  public boolean dispatchCapturedPointerEvent(MotionEvent event) {
    if (mOnCapturedPointerListener != null && mOnCapturedPointerListener.onCapturedPointer(this, event)) return true;
    return onCapturedPointerEvent(event);
  }
  public void setOnCapturedPointerListener(OnCapturedPointerListener l) { mOnCapturedPointerListener = l; }
  public final void requestUnbufferedDispatch(MotionEvent event) {}
  public final void requestUnbufferedDispatch(int source) {}
  public void addOnUnhandledKeyEventListener(OnUnhandledKeyEventListener listener) {
    if (mUnhandledKeyListeners == null) mUnhandledKeyListeners = new java.util.ArrayList<>();
    mUnhandledKeyListeners.add(listener);
  }
  public void removeOnUnhandledKeyEventListener(OnUnhandledKeyEventListener listener) { if (mUnhandledKeyListeners != null) mUnhandledKeyListeners.remove(listener); }
  /** the input method proxies no other view's connection here */
  public boolean checkInputConnectionProxy(View view) { return false; }
  public final void cancelPendingInputEvents() { onCancelPendingInputEvents(); }
  public void onCancelPendingInputEvents() { removeCallbacks(mPerformClick); cancelLongPress(); }
  public void setOnReceiveContentListener(String[] mimeTypes, OnReceiveContentListener listener) {
    if (listener != null && (mimeTypes == null || mimeTypes.length == 0)) throw new IllegalArgumentException("When the listener is set, MIME types must also be set");
    mReceiveContentMimeTypes = listener == null ? null : mimeTypes;
    mOnReceiveContentListener = listener;
  }
  public String[] getReceiveContentMimeTypes() { return mReceiveContentMimeTypes; }
  public ContentInfo performReceiveContent(ContentInfo payload) {
    if (mOnReceiveContentListener != null) {
      ContentInfo rest = mOnReceiveContentListener.onReceiveContent(this, payload);
      return rest == null ? null : onReceiveContent(rest);
    }
    return onReceiveContent(payload);
  }
  public ContentInfo onReceiveContent(ContentInfo payload) { return payload; }
  public final boolean startDrag(android.content.ClipData data, DragShadowBuilder shadowBuilder, Object myLocalState, int flags) { return startDragAndDrop(data, shadowBuilder, myLocalState, flags); }
  /** drags between windows go through the window manager, which a converted app has none of: the drag does not start */
  public final boolean startDragAndDrop(android.content.ClipData data, DragShadowBuilder shadowBuilder, Object myLocalState, int flags) { return false; }
  public final void cancelDragAndDrop() {}
  public final void updateDragShadow(DragShadowBuilder shadowBuilder) {}
  public boolean onDragEvent(DragEvent event) { return false; }
  public boolean dispatchDragEvent(DragEvent event) {
    if (mOnDragListener != null && isEnabled() && mOnDragListener.onDrag(this, event)) return true;
    return onDragEvent(event);
  }
  OnDragListener mOnDragListener;
  /** Creates the image the system shows during a drag. */
  public static class DragShadowBuilder {
    private final java.lang.ref.WeakReference<View> mView;
    public DragShadowBuilder(View view) { mView = new java.lang.ref.WeakReference<>(view); }
    public DragShadowBuilder() { mView = new java.lang.ref.WeakReference<>(null); }
    public final View getView() { return mView.get(); }
    public void onProvideShadowMetrics(android.graphics.Point outShadowSize, android.graphics.Point outShadowTouchPoint) {
      View view = mView.get();
      if (view != null) { outShadowSize.set(view.getWidth(), view.getHeight()); outShadowTouchPoint.set(outShadowSize.x / 2, outShadowSize.y / 2); }
    }
    public void onDrawShadow(Canvas canvas) { View view = mView.get(); if (view != null) view.draw(canvas); }
  }

  // ---- temporary detach, display hints, system UI, insets animation, window
  public void dispatchStartTemporaryDetach() { onStartTemporaryDetach(); }
  public void dispatchFinishTemporaryDetach() { onFinishTemporaryDetach(); }
  public void dispatchDisplayHint(int hint) { onDisplayHint(hint); }
  protected void onDisplayHint(int hint) {}
  public void onScreenStateChanged(int screenState) {}
  public void onRtlPropertiesChanged(int layoutDirection) {}
  public void dispatchWindowSystemUiVisiblityChanged(int visible) { onWindowSystemUiVisibilityChanged(visible); }
  public void onWindowSystemUiVisibilityChanged(int visible) {}
  public void setWindowInsetsAnimationCallback(WindowInsetsAnimation.Callback callback) { mWindowInsetsAnimationCallback = callback; }
  public void dispatchWindowInsetsAnimationPrepare(WindowInsetsAnimation animation) { if (mWindowInsetsAnimationCallback != null) mWindowInsetsAnimationCallback.onPrepare(animation); }
  public WindowInsetsAnimation.Bounds dispatchWindowInsetsAnimationStart(WindowInsetsAnimation animation, WindowInsetsAnimation.Bounds bounds) {
    return mWindowInsetsAnimationCallback != null ? mWindowInsetsAnimationCallback.onStart(animation, bounds) : bounds;
  }
  public WindowInsets dispatchWindowInsetsAnimationProgress(WindowInsets insets, java.util.List<WindowInsetsAnimation> runningAnimations) {
    return mWindowInsetsAnimationCallback != null ? mWindowInsetsAnimationCallback.onProgress(insets, runningAnimations) : insets;
  }
  public void dispatchWindowInsetsAnimationEnd(WindowInsetsAnimation animation) { if (mWindowInsetsAnimationCallback != null) mWindowInsetsAnimationCallback.onEnd(animation); }
  public void setHasTransientState(boolean hasTransientState) { mTransientStateCount = Math.max(0, mTransientStateCount + (hasTransientState ? 1 : -1)); }
  /** sets the visibility flags without the side effects of setVisibility (transitions restore it afterwards) */
  public void setTransitionVisibility(int visibility) { mViewFlags = (mViewFlags & ~VISIBILITY_MASK) | (visibility & VISIBILITY_MASK); invalidate(); }
  public void dispatchDrawableHotspotChanged(float x, float y) {}
  public void drawableHotspotChanged(float x, float y) {
    if (mBackground != null) mBackground.setHotspot(x, y);
    dispatchDrawableHotspotChanged(x, y);
  }

  // ---- scroll capture
  public void setScrollCaptureHint(int hint) { mScrollCaptureHint = hint; }
  public int getScrollCaptureHint() { return mScrollCaptureHint; }
  public final void setScrollCaptureCallback(ScrollCaptureCallback callback) { mScrollCaptureCallback = callback; }
  public void onScrollCaptureSearch(android.graphics.Rect localVisibleRect, android.graphics.Point windowOffset, java.util.function.Consumer<ScrollCaptureTarget> targets) {
    if ((mScrollCaptureHint & SCROLL_CAPTURE_HINT_EXCLUDE) != 0 || mScrollCaptureCallback == null) return;
    targets.accept(new ScrollCaptureTarget(this, localVisibleRect, windowOffset, mScrollCaptureCallback));
  }
  public void dispatchScrollCaptureSearch(android.graphics.Rect localVisibleRect, android.graphics.Point windowOffset, java.util.function.Consumer<ScrollCaptureTarget> targets) {
    onScrollCaptureSearch(localVisibleRect, windowOffset, targets);
  }

  // ---- inspection: the attributes the view was inflated with
  public int getSourceLayoutResId() { return mSourceLayoutResId; }
  public int getExplicitStyle() { return 0; }
  public int[] getAttributeResolutionStack(int attribute) { return new int[0]; }
  public java.util.Map<Integer, Integer> getAttributeSourceResourceMap() { return new java.util.HashMap<>(); }
  /** keeps attribute data only when the debug inspector is on (it is off) */
  public final void saveAttributeDataForStyleable(Context context, int[] styleable, AttributeSet attrs, android.content.res.TypedArray t, int defStyleAttr, int defStyleRes) {}

  @SuppressWarnings("unchecked")
  public final <T extends View> T requireViewById(int id) {
    T v = (T) findViewById(id);
    if (v == null) throw new IllegalArgumentException("ID does not reference a View inside this View");
    return v;
  }
  public final android.window.OnBackInvokedDispatcher findOnBackInvokedDispatcher() {
    Context c = getContext();
    while (c instanceof android.content.ContextWrapper && !(c instanceof android.app.Activity)) c = ((android.content.ContextWrapper) c).getBaseContext();
    if (c instanceof android.app.Activity) return ((android.app.Activity) c).getOnBackInvokedDispatcher();
    return null;
  }
}
