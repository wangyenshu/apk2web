package android.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ResourceCursorAdapter extends CursorAdapter {
  private int mLayout, mDropDownLayout;
  private LayoutInflater mInflater, mDropDownInflater;
  @Deprecated public ResourceCursorAdapter(Context context, int layout, Cursor c) { super(context, c); mLayout = mDropDownLayout = layout; mInflater = mDropDownInflater = LayoutInflater.from(context); }
  @Deprecated public ResourceCursorAdapter(Context context, int layout, Cursor c, boolean autoRequery) { super(context, c, autoRequery); mLayout = mDropDownLayout = layout; mInflater = mDropDownInflater = LayoutInflater.from(context); }
  public ResourceCursorAdapter(Context context, int layout, Cursor c, int flags) { super(context, c, flags); mLayout = mDropDownLayout = layout; mInflater = mDropDownInflater = LayoutInflater.from(context); }
  @Override public View newView(Context context, Cursor cursor, ViewGroup parent) { return mInflater.inflate(mLayout, parent, false); }
  @Override public View newDropDownView(Context context, Cursor cursor, ViewGroup parent) { return mDropDownInflater.inflate(mDropDownLayout, parent, false); }
  public void setViewResource(int layout) { mLayout = layout; }
  public void setDropDownViewResource(int dropDownLayout) { mDropDownLayout = dropDownLayout; }
}
