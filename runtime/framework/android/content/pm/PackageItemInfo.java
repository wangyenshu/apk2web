package android.content.pm;

public class PackageItemInfo {
  public String name, packageName;
  public CharSequence nonLocalizedLabel;
  public int labelRes, icon, logo, banner;
  public android.os.Bundle metaData;
  public PackageItemInfo() {}
  public PackageItemInfo(PackageItemInfo o) { name = o.name; packageName = o.packageName; nonLocalizedLabel = o.nonLocalizedLabel; labelRes = o.labelRes; icon = o.icon; logo = o.logo; metaData = o.metaData; }
  public CharSequence loadLabel(PackageManager pm) {
    if (nonLocalizedLabel != null) return nonLocalizedLabel;
    if (labelRes != 0) { try { return pm.ctx.getResources().getText(labelRes); } catch (RuntimeException e) {} }
    return name != null ? name : packageName;
  }
  public android.graphics.drawable.Drawable loadIcon(PackageManager pm) {
    if (icon != 0) { try { return pm.ctx.getResources().getDrawable(icon); } catch (RuntimeException e) {} }
    return pm.getDefaultActivityIcon();
  }
  public android.graphics.drawable.Drawable loadLogo(PackageManager pm) { return logo != 0 ? pm.ctx.getResources().getDrawable(logo) : null; }
  public android.content.res.XmlResourceParser loadXmlMetaData(PackageManager pm, String name) {
    if (metaData == null) return null;
    int id = metaData.getInt(name);
    return id == 0 ? null : pm.ctx.getResources().getXml(id);
  }
}
