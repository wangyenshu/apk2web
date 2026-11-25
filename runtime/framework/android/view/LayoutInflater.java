package android.view;

import android.content.Context;
import android.util.AttributeSet;
import a2w.Attrs;
import a2w.Values;
import a2w.XmlNode;

public class LayoutInflater {
  public interface Factory { View onCreateView(String name, Context context, AttributeSet attrs); }
  public interface Factory2 extends Factory { View onCreateView(View parent, String name, Context context, AttributeSet attrs); }
  public interface Filter { boolean onLoadClass(Class clazz); }

  protected final Context mContext;
  private Factory mFactory;
  private Factory2 mFactory2;
  private boolean mFactorySet;
  private Filter mFilter;

  protected LayoutInflater(Context c) { mContext = c; }
  protected LayoutInflater(LayoutInflater o, Context c) { mContext = c; mFactory = o.mFactory; mFactory2 = o.mFactory2; mFilter = o.mFilter; mPrivateFactory = o.mPrivateFactory; }
  public static LayoutInflater from(Context c) {
    LayoutInflater l = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (l == null) throw new AssertionError("LayoutInflater not found.");
    return l;
  }
  /** the application-level inflater */
  public static LayoutInflater create(Context c) { return new LayoutInflater(c); }
  public LayoutInflater cloneInContext(Context c) { return new LayoutInflater(this, c); }
  public Context getContext() { return mContext; }
  public final Factory getFactory() { return mFactory; }
  public final Factory2 getFactory2() { return mFactory2; }
  public void setFactory(Factory f) { mFactorySet = true; mFactory = f; }
  public void setFactory2(Factory2 f) { mFactorySet = true; mFactory2 = f; mFactory = f; }
  private Factory2 mPrivateFactory;
  /** (hidden API) the activity: it creates <fragment> views when no app factory does */
  public void setPrivateFactory(Factory2 f) { mPrivateFactory = f; }
  public Filter getFilter() { return mFilter; }
  public void setFilter(Filter f) { mFilter = f; }

  public View inflate(int resource, ViewGroup root) { return inflate(resource, root, root != null); }
  public View inflate(int resource, ViewGroup root, boolean attachToRoot) {
    XmlNode n = mContext.getResources().layoutNode(resource);
    return inflate(n, root, attachToRoot);
  }
  public View inflate(XmlNode node, ViewGroup root, boolean attachToRoot) {
    // attribute sets made during the inflation resolve "?attr/x" against this inflater's theme
    android.content.res.Resources.Theme wasAttrs = Attrs.current, wasDrawables = a2w.Drawables.theme;
    android.content.res.Resources.Theme t = mContext.getTheme();
    Attrs.current = t;
    a2w.Drawables.theme = t;
    try { return inflateNode(node, root, attachToRoot); }
    finally { Attrs.current = wasAttrs; a2w.Drawables.theme = wasDrawables; }
  }
  private View inflateNode(XmlNode node, ViewGroup root, boolean attachToRoot) {
    if (node.tag.equals("merge")) {
      if (root == null || !attachToRoot) throw new InflateException("<merge /> can be used only with a valid ViewGroup root and attachToRoot=true");
      rInflate(node, root, false);
      return root;
    }
    Attrs attrs = new Attrs(node);
    View temp = createViewFromTag(root, node.tag, attrs, node);
    ViewGroup.LayoutParams params = null;
    if (root != null) {
      params = root.generateLayoutParams(attrs);
      if (!attachToRoot) temp.setLayoutParams(params);
    }
    rInflate(node, temp, true);
    if (root != null && attachToRoot) { root.addView(temp, params); return root; }
    return temp;
  }

  void rInflate(XmlNode node, View parent, boolean finishInflate) {
    for (XmlNode c : node.children()) {
      switch (c.tag) {
        case "requestFocus": parent.requestFocus(); break;
        case "include": parseInclude(c, parent); break;
        case "merge": throw new InflateException("<merge /> must be the root element");
        default: {
          // children inflate in their parent's context (a view with android:theme passes its theme down)
          Context pc = parent.getContext();
          if (pc != null && pc != mContext) {
            LayoutInflater sub = cloneInContext(pc);
            android.content.res.Resources.Theme was = Attrs.current, wasD = a2w.Drawables.theme;
            Attrs.current = pc.getTheme(); a2w.Drawables.theme = Attrs.current;
            try { sub.inflateChild(c, parent); } finally { Attrs.current = was; a2w.Drawables.theme = wasD; }
          } else inflateChild(c, parent);
        }
      }
    }
    if (finishInflate) parent.onFinishInflate();
  }

  private void inflateChild(XmlNode c, View parent) {
    Attrs a = new Attrs(c);
    View v = createViewFromTag(parent, c.tag, a, c);
    ViewGroup vg = (ViewGroup) parent;
    ViewGroup.LayoutParams p = vg.generateLayoutParams(a);
    rInflate(c, v, true);
    vg.addView(v, p);
  }

  void parseInclude(XmlNode inc, View parent) {
    if (!(parent instanceof ViewGroup)) throw new InflateException("<include /> can only be used inside of a ViewGroup");
    String layout = inc.get("", "layout");
    if (layout == null) throw new InflateException("You must specifiy a layout in the include tag: <include layout=\"@layout/layoutID\" />");
    int id = Values.refId(layout);
    XmlNode child = mContext.getResources().layoutNode(id);
    ViewGroup group = (ViewGroup) parent;
    if (child.tag.equals("merge")) { rInflate(child, parent, false); return; }
    Attrs childAttrs = new Attrs(child);
    View view = createViewFromTag(parent, child.tag, childAttrs, child);
    Attrs incAttrs = new Attrs(inc);
    ViewGroup.LayoutParams params = inc.a("layout_width") != null && inc.a("layout_height") != null
        ? group.generateLayoutParams(incAttrs) : group.generateLayoutParams(childAttrs);
    view.setLayoutParams(params);
    rInflate(child, view, true);
    String vid = inc.a("id");
    if (vid != null) view.setId(Values.refId(vid));
    String vis = inc.a("visibility");
    if (vis != null) view.setVisibility(vis.equals("gone") ? View.GONE : vis.equals("invisible") ? View.INVISIBLE : View.VISIBLE);
    group.addView(view);
  }

  View createViewFromTag(View parent, String name, Attrs attrs, XmlNode node) {
    if (name.equals("view")) name = node.get("", "class");
    // android:theme on an element: the view (and its children) get a context with that theme over the inflater's
    Context ctx = mContext;
    String th = attrs.a("theme");
    if (th != null && Values.isRef(th) && Values.refId(th) != 0) {
      ctx = new android.view.ContextThemeWrapper(mContext, Values.refId(th));
      attrs.theme = ctx.getTheme();
    }
    if (ctx != mContext) {
      LayoutInflater sub = cloneInContext(ctx);
      android.content.res.Resources.Theme was = Attrs.current, wasD = a2w.Drawables.theme;
      Attrs.current = attrs.theme; a2w.Drawables.theme = attrs.theme;
      try { return sub.createViewIn(parent, name, attrs); }
      finally { Attrs.current = was; a2w.Drawables.theme = wasD; }
    }
    return createViewIn(parent, name, attrs);
  }
  private View createViewIn(View parent, String name, Attrs attrs) {
    View v = null;
    if (mFactory2 != null) v = mFactory2.onCreateView(parent, name, mContext, attrs);
    else if (mFactory != null) v = mFactory.onCreateView(name, mContext, attrs);
    if (v == null && mPrivateFactory != null) v = mPrivateFactory.onCreateView(parent, name, mContext, attrs);
    if (v == null && name.equals("fragment")) {
      // an inflater of another context (a dialog, the application): the nearest activity hosts the fragment
      for (Context k = mContext; k instanceof android.content.ContextWrapper; k = ((android.content.ContextWrapper) k).getBaseContext())
        if (k instanceof android.app.Activity) { v = ((android.app.Activity) k).onCreateView(parent, name, mContext, attrs); break; }
    }
    if (v == null && name.indexOf('.') < 0) {
      // PhoneLayoutInflater: a bare tag is a platform widget
      for (String p : PREFIXES) if (a2w.Reflection.known(p + name)) return createView(name, p, attrs);
      v = onCreateView(name, attrs);
    }
    if (v == null) v = createView(name, null, attrs);
    return v;
  }
  static final String[] PREFIXES = {"android.widget.", "android.webkit.", "android.app.", "android.view."};
  static final Class<?>[] VIEW_SIG = {Context.class, AttributeSet.class};
  public final View createView(String name, String prefix, AttributeSet attrs) {
    View v = (View) a2w.Reflection.newInstance(name, VIEW_SIG, mContext, attrs);
    if (v == null && prefix != null) v = (View) a2w.Reflection.newInstance(prefix + name, VIEW_SIG, mContext, attrs);
    if (v == null) throw new InflateException("Binary XML file: Error inflating class " + name);
    return v;
  }
  protected View onCreateView(String name, AttributeSet attrs) { return createView(name, "android.view.", attrs); }

}
