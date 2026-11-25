package android.view;

import android.content.Context;
import android.util.AttributeSet;
import java.lang.ref.WeakReference;

/** An invisible, zero-sized placeholder that inflates its layout in its place on demand. */
public final class ViewStub extends View {
  public interface OnInflateListener { void onInflate(ViewStub stub, View inflated); }
  private int mInflatedId, mLayoutResource;
  private WeakReference<View> mInflatedViewRef;
  private LayoutInflater mInflater;
  private OnInflateListener mInflateListener;
  public ViewStub(Context c) { this(c, 0); }
  public ViewStub(Context c, int layoutResource) { super(c); mLayoutResource = layoutResource; init(); }
  public ViewStub(Context c, AttributeSet a) { this(c, a, 0); }
  public ViewStub(Context c, AttributeSet a, int d) {
    super(c, a);
    String ns = "http://schemas.android.com/apk/res/android";
    if (a != null) {
      mInflatedId = a.getAttributeResourceValue(ns, "inflatedId", NO_ID);
      mLayoutResource = a.getAttributeResourceValue(ns, "layout", 0);
      if (mLayoutResource == 0) mLayoutResource = a.getAttributeResourceValue(null, "layout", 0);
    }
    init();
  }
  private void init() { setVisibility(GONE); setWillNotDraw(true); }
  public int getInflatedId() { return mInflatedId; }
  public void setInflatedId(int id) { mInflatedId = id; }
  public int getLayoutResource() { return mLayoutResource; }
  public void setLayoutResource(int r) { mLayoutResource = r; }
  public void setLayoutInflater(LayoutInflater i) { mInflater = i; }
  public LayoutInflater getLayoutInflater() { return mInflater; }
  @Override protected void onMeasure(int ws, int hs) { setMeasuredDimension(0, 0); }
  @Override public void draw(android.graphics.Canvas c) {}
  @Override protected void dispatchDraw(android.graphics.Canvas c) {}
  @Override public void setVisibility(int v) {
    if (mInflatedViewRef != null) {
      View view = mInflatedViewRef.get();
      if (view != null) view.setVisibility(v);
      else throw new IllegalStateException("setVisibility called on un-referenced view");
    } else {
      super.setVisibility(v);
      if (v == VISIBLE || v == INVISIBLE) inflate();
    }
  }
  public View inflate() {
    ViewParent vp = getParent();
    if (!(vp instanceof ViewGroup)) throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
    if (mLayoutResource == 0) throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
    ViewGroup parent = (ViewGroup) vp;
    LayoutInflater f = mInflater != null ? mInflater : LayoutInflater.from(getContext());
    View view = f.inflate(mLayoutResource, parent, false);
    if (mInflatedId != NO_ID) view.setId(mInflatedId);
    int index = parent.indexOfChild(this);
    parent.removeViewInLayout(this);
    ViewGroup.LayoutParams lp = getLayoutParams();
    if (lp != null) parent.addView(view, index, lp); else parent.addView(view, index);
    mInflatedViewRef = new WeakReference<>(view);
    if (mInflateListener != null) mInflateListener.onInflate(this, view);
    return view;
  }
  public void setOnInflateListener(OnInflateListener l) { mInflateListener = l; }
}
