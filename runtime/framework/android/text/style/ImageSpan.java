package android.text.style;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class ImageSpan extends DynamicDrawableSpan {
  private Drawable drawable;
  private Uri contentUri;
  private int resourceId;
  private Context context;
  private final String source;
  public ImageSpan(Bitmap b) { this(null, b, ALIGN_BOTTOM); }
  public ImageSpan(Bitmap b, int align) { this(null, b, align); }
  public ImageSpan(Context c, Bitmap b) { this(c, b, ALIGN_BOTTOM); }
  public ImageSpan(Context c, Bitmap b, int align) {
    super(align);
    context = c;
    drawable = c != null ? new BitmapDrawable(c.getResources(), b) : new BitmapDrawable(b);
    drawable.setBounds(0, 0, drawable.getIntrinsicWidth() > 0 ? drawable.getIntrinsicWidth() : 0, drawable.getIntrinsicHeight() > 0 ? drawable.getIntrinsicHeight() : 0);
    source = null;
  }
  public ImageSpan(Drawable d) { this(d, null, ALIGN_BOTTOM); }
  public ImageSpan(Drawable d, int align) { this(d, null, align); }
  public ImageSpan(Drawable d, String source) { this(d, source, ALIGN_BOTTOM); }
  public ImageSpan(Drawable d, String source, int align) { super(align); drawable = d; this.source = source; }
  public ImageSpan(Context c, Uri uri) { this(c, uri, ALIGN_BOTTOM); }
  public ImageSpan(Context c, Uri uri, int align) { super(align); context = c; contentUri = uri; source = uri.toString(); }
  public ImageSpan(Context c, int resourceId) { this(c, resourceId, ALIGN_BOTTOM); }
  public ImageSpan(Context c, int resourceId, int align) { super(align); context = c; this.resourceId = resourceId; source = null; }
  @Override public Drawable getDrawable() {
    if (drawable != null) return drawable;
    try {
      if (contentUri != null) {
        java.io.InputStream is = context.getContentResolver().openInputStream(contentUri);
        Bitmap b = android.graphics.BitmapFactory.decodeStream(is);
        drawable = new BitmapDrawable(context.getResources(), b);
      } else if (resourceId != 0) {
        drawable = context.getResources().getDrawable(resourceId);
      }
      if (drawable != null) drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    } catch (Exception e) { android.util.Log.e("ImageSpan", "Failed to load image", e); }
    return drawable;
  }
  public String getSource() { return source; }
}
