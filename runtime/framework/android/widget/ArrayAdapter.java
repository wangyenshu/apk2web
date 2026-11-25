package android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;

public class ArrayAdapter<T> extends BaseAdapter implements Filterable, ThemedSpinnerAdapter {
  final Context mContext; final int mResource, mFieldId; List<T> mObjects; int mDropDownResource; boolean mNotifyOnChange = true;
  public ArrayAdapter(Context c, int res) { this(c, res, 0, new ArrayList<T>()); }
  public ArrayAdapter(Context c, int res, int field) { this(c, res, field, new ArrayList<T>()); }
  public ArrayAdapter(Context c, int res, T[] objs) { this(c, res, 0, new ArrayList<>(Arrays.asList(objs))); }
  public ArrayAdapter(Context c, int res, int field, T[] objs) { this(c, res, field, new ArrayList<>(Arrays.asList(objs))); }
  public ArrayAdapter(Context c, int res, List<T> objs) { this(c, res, 0, objs); }
  public ArrayAdapter(Context c, int res, int field, List<T> objs) { mContext = c; mResource = mDropDownResource = res; mFieldId = field; mObjects = objs; }
  public void add(T o) { mObjects.add(o); if (mNotifyOnChange) notifyDataSetChanged(); }
  public void addAll(Collection<? extends T> c) { mObjects.addAll(c); if (mNotifyOnChange) notifyDataSetChanged(); }
  public void insert(T o, int i) { mObjects.add(i, o); if (mNotifyOnChange) notifyDataSetChanged(); }
  public void remove(T o) { mObjects.remove(o); if (mNotifyOnChange) notifyDataSetChanged(); }
  public void clear() { mObjects.clear(); if (mNotifyOnChange) notifyDataSetChanged(); }
  public void sort(Comparator<? super T> c) { Collections.sort(mObjects, c); if (mNotifyOnChange) notifyDataSetChanged(); }
  @Override public void notifyDataSetChanged() { super.notifyDataSetChanged(); mNotifyOnChange = true; }
  public void setNotifyOnChange(boolean n) { mNotifyOnChange = n; }
  public Context getContext() { return mContext; }
  public int getCount() { return mObjects.size(); }
  public T getItem(int p) { return mObjects.get(p); }
  public int getPosition(T o) { return mObjects.indexOf(o); }
  public long getItemId(int p) { return p; }
  public View getView(int p, View convertView, ViewGroup parent) { return create(p, convertView, parent, mResource); }
  private View create(int p, View convertView, ViewGroup parent, int res) {
    View view = convertView != null ? convertView : LayoutInflater.from(mContext).inflate(res, parent, false);
    TextView text = mFieldId == 0 ? (TextView) view : (TextView) view.findViewById(mFieldId);
    T item = getItem(p);
    text.setText(item instanceof CharSequence ? (CharSequence) item : String.valueOf(item));
    return view;
  }
  public void setDropDownViewResource(int r) { mDropDownResource = r; }
  @Override public View getDropDownView(int p, View c, ViewGroup parent) { return create(p, c, parent, mDropDownResource); }
  public static ArrayAdapter<CharSequence> createFromResource(Context context, int textArrayResId, int textViewResId) {
    CharSequence[] strings = context.getResources().getTextArray(textArrayResId);
    return new ArrayAdapter<>(context, textViewResId, 0, new ArrayList<>(Arrays.asList(strings)));
  }
  public void addAll(T... items) { Collections.addAll(mObjects, items); if (mNotifyOnChange) notifyDataSetChanged(); }
  public int getDropDownViewResource() { return mDropDownResource; }
  private ArrayList<T> mOriginalValues;
  private Filter mFilter;
  /** prefix matching on the items' text and on each of its words, as Android's ArrayFilter */
  public Filter getFilter() {
    if (mFilter == null) mFilter = new Filter() {
      @Override protected FilterResults performFiltering(CharSequence prefix) {
        FilterResults results = new FilterResults();
        if (mOriginalValues == null) mOriginalValues = new ArrayList<>(mObjects);
        if (prefix == null || prefix.length() == 0) {
          ArrayList<T> list = new ArrayList<>(mOriginalValues);
          results.values = list; results.count = list.size();
        } else {
          String prefixString = prefix.toString().toLowerCase();
          ArrayList<T> newValues = new ArrayList<>();
          for (T value : mOriginalValues) {
            String valueText = String.valueOf(value).toLowerCase();
            if (valueText.startsWith(prefixString)) newValues.add(value);
            else for (String word : valueText.split(" ")) if (word.startsWith(prefixString)) { newValues.add(value); break; }
          }
          results.values = newValues; results.count = newValues.size();
        }
        return results;
      }
      @SuppressWarnings("unchecked") @Override protected void publishResults(CharSequence constraint, FilterResults results) {
        mObjects = (List<T>) results.values;
        if (results.count > 0) notifyDataSetChanged(); else notifyDataSetInvalidated();
      }
    };
    return mFilter;
  }
}
