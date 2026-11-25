package android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.Map;

/** Binds a list of maps to views: each "from" key's value goes to the view with the matching "to" id. */
public class SimpleAdapter extends BaseAdapter implements Filterable {
  public interface ViewBinder { boolean setViewValue(View view, Object data, String textRepresentation); }
  private final List<? extends Map<String, ?>> data;
  private final int resource;
  private int dropDownResource;
  private final String[] from;
  private final int[] to;
  private final LayoutInflater inflater;
  private ViewBinder binder;
  public SimpleAdapter(Context c, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
    this.data = data; this.resource = this.dropDownResource = resource; this.from = from; this.to = to;
    inflater = LayoutInflater.from(c);
  }
  public int getCount() { return data.size(); }
  public Object getItem(int p) { return data.get(p); }
  public long getItemId(int p) { return p; }
  public View getView(int p, View convert, ViewGroup parent) { return make(p, convert, parent, resource); }
  @Override public View getDropDownView(int p, View convert, ViewGroup parent) { return make(p, convert, parent, dropDownResource); }
  public void setDropDownViewResource(int r) { dropDownResource = r; }
  private View make(int p, View convert, ViewGroup parent, int res) {
    View v = convert != null ? convert : inflater.inflate(res, parent, false);
    Map<String, ?> m = data.get(p);
    for (int i = 0; i < to.length && i < from.length; i++) {
      View t = v.findViewById(to[i]);
      if (t == null) continue;
      Object d = m.get(from[i]);
      String text = d == null ? "" : d.toString();
      if (binder != null && binder.setViewValue(t, d, text)) continue;
      if (t instanceof Checkable && (d instanceof Boolean || t instanceof CompoundButton && !(d instanceof String))) ((Checkable) t).setChecked(Boolean.TRUE.equals(d));
      else if (t instanceof TextView) ((TextView) t).setText(text);
      else if (t instanceof ImageView) {
        if (d instanceof Integer) ((ImageView) t).setImageResource((Integer) d);
        else if (d instanceof android.graphics.Bitmap) ((ImageView) t).setImageBitmap((android.graphics.Bitmap) d);
        else { try { ((ImageView) t).setImageResource(Integer.parseInt(text)); } catch (NumberFormatException e) { ((ImageView) t).setImageURI(android.net.Uri.parse(text)); } }
      }
    }
    return v;
  }
  public ViewBinder getViewBinder() { return binder; }
  public void setViewBinder(ViewBinder b) { binder = b; }
  public void setViewImage(ImageView v, int value) { v.setImageResource(value); }
  public void setViewImage(ImageView v, String value) { try { v.setImageResource(Integer.parseInt(value)); } catch (NumberFormatException e) { v.setImageURI(android.net.Uri.parse(value)); } }
  public void setViewText(TextView v, String text) { v.setText(text); }
  public Filter getFilter() { return null; }
}
