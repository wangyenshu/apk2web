package android.util;

import android.graphics.Path;
import java.util.ArrayList;

/** SVG path data ("M10,10 L20,20 A5,5 0 0 1 30,30 Z"), as vector drawables write it, to a Path (hidden platform API). */
public class PathParser {
  public static Path createPathFromPathData(String pathData) {
    Path p = new Path();
    if (pathData != null) parse(pathData, p);
    return p;
  }
  public static boolean canMorph(PathData a, PathData b) { return a != null && b != null && a.data.equals(b.data); }
  public static boolean interpolatePathData(PathData out, PathData from, PathData to, float fraction) { out.data = fraction < 1 ? from.data : to.data; return true; }

  public static class PathData {
    String data = "";
    public PathData() {}
    public PathData(String s) { data = s == null ? "" : s; }
    public PathData(PathData o) { data = o.data; }
    public void setPathData(PathData o) { data = o.data; }
    public String toString() { return data; }
  }
  public static void createPathFromPathData(Path out, PathData data) { out.reset(); parse(data.data, out); }

  /** parses d into p */
  public static void parse(String d, Path p) {
    int n = d.length(), i = 0;
    float cx = 0, cy = 0, sx = 0, sy = 0, lcx = 0, lcy = 0;   // current point, subpath start, last control point
    char cmd = 0, prev = 0;
    float[] a = new float[7];
    while (i < n) {
      char c = d.charAt(i);
      if (Character.isWhitespace(c) || c == ',') { i++; continue; }
      if (Character.isLetter(c) && c != 'e' && c != 'E') { cmd = c; i++; if (cmd == 'z' || cmd == 'Z') { p.close(); cx = sx; cy = sy; prev = cmd; continue; } }
      else if (cmd == 0) { i++; continue; }
      int need;
      switch (Character.toLowerCase(cmd)) {
        case 'm': case 'l': case 't': need = 2; break;
        case 'h': case 'v': need = 1; break;
        case 'c': need = 6; break;
        case 's': case 'q': need = 4; break;
        case 'a': need = 7; break;
        default: i++; continue;
      }
      int got = 0;
      while (got < need) {
        while (i < n && (Character.isWhitespace(d.charAt(i)) || d.charAt(i) == ',')) i++;
        if (i >= n) break;
        if (Character.toLowerCase(cmd) == 'a' && (got == 3 || got == 4) && (d.charAt(i) == '0' || d.charAt(i) == '1')) {
          a[got++] = d.charAt(i) - '0'; i++; continue;   // arc flags may be written without separators
        }
        int start = i;
        if (i < n && (d.charAt(i) == '-' || d.charAt(i) == '+')) i++;
        boolean dot = false, exp = false;
        while (i < n) {
          char ch = d.charAt(i);
          if (Character.isDigit(ch)) i++;
          else if (ch == '.' && !dot && !exp) { dot = true; i++; }
          else if ((ch == 'e' || ch == 'E') && !exp) { exp = true; i++; if (i < n && (d.charAt(i) == '-' || d.charAt(i) == '+')) i++; }
          else break;
        }
        if (i == start) { i++; break; }
        try { a[got++] = Float.parseFloat(d.substring(start, i)); } catch (NumberFormatException e) { got = -1; break; }
      }
      if (got != need) break;
      boolean rel = Character.isLowerCase(cmd);
      float ox = rel ? cx : 0, oy = rel ? cy : 0;
      switch (Character.toLowerCase(cmd)) {
        case 'm':
          cx = a[0] + ox; cy = a[1] + oy; sx = cx; sy = cy; p.moveTo(cx, cy);
          cmd = rel ? 'l' : 'L';   // further pairs are lines
          break;
        case 'l': cx = a[0] + ox; cy = a[1] + oy; p.lineTo(cx, cy); break;
        case 'h': cx = a[0] + ox; p.lineTo(cx, cy); break;
        case 'v': cy = a[0] + oy; p.lineTo(cx, cy); break;
        case 'c':
          p.cubicTo(a[0] + ox, a[1] + oy, a[2] + ox, a[3] + oy, a[4] + ox, a[5] + oy);
          lcx = a[2] + ox; lcy = a[3] + oy; cx = a[4] + ox; cy = a[5] + oy; break;
        case 's': {
          char pl = Character.toLowerCase(prev);
          float x1 = pl == 'c' || pl == 's' ? 2 * cx - lcx : cx, y1 = pl == 'c' || pl == 's' ? 2 * cy - lcy : cy;
          p.cubicTo(x1, y1, a[0] + ox, a[1] + oy, a[2] + ox, a[3] + oy);
          lcx = a[0] + ox; lcy = a[1] + oy; cx = a[2] + ox; cy = a[3] + oy; break;
        }
        case 'q':
          p.quadTo(a[0] + ox, a[1] + oy, a[2] + ox, a[3] + oy);
          lcx = a[0] + ox; lcy = a[1] + oy; cx = a[2] + ox; cy = a[3] + oy; break;
        case 't': {
          char pl = Character.toLowerCase(prev);
          float x1 = pl == 'q' || pl == 't' ? 2 * cx - lcx : cx, y1 = pl == 'q' || pl == 't' ? 2 * cy - lcy : cy;
          p.quadTo(x1, y1, a[0] + ox, a[1] + oy);
          lcx = x1; lcy = y1; cx = a[0] + ox; cy = a[1] + oy; break;
        }
        case 'a': {
          float x = a[5] + ox, y = a[6] + oy;
          arc(p, cx, cy, x, y, a[0], a[1], a[2], a[3] != 0, a[4] != 0);
          cx = x; cy = y; break;
        }
      }
      prev = cmd;
    }
  }

  /** an SVG elliptical arc as cubic Béziers */
  static void arc(Path p, float x0, float y0, float x1, float y1, float rx, float ry, float angle, boolean large, boolean sweep) {
    if (rx == 0 || ry == 0) { p.lineTo(x1, y1); return; }
    if (x0 == x1 && y0 == y1) return;
    rx = Math.abs(rx); ry = Math.abs(ry);
    double phi = Math.toRadians(angle), cos = Math.cos(phi), sin = Math.sin(phi);
    double dx = (x0 - x1) / 2.0, dy = (y0 - y1) / 2.0;
    double x1p = cos * dx + sin * dy, y1p = -sin * dx + cos * dy;
    double lambda = x1p * x1p / (rx * rx) + y1p * y1p / (ry * ry);
    if (lambda > 1) { double s = Math.sqrt(lambda); rx *= s; ry *= s; }
    double num = rx * rx * ry * ry - rx * rx * y1p * y1p - ry * ry * x1p * x1p;
    double den = rx * rx * y1p * y1p + ry * ry * x1p * x1p;
    double coef = den == 0 ? 0 : Math.sqrt(Math.max(0, num / den));
    if (large == sweep) coef = -coef;
    double cxp = coef * rx * y1p / ry, cyp = -coef * ry * x1p / rx;
    double cx = cos * cxp - sin * cyp + (x0 + x1) / 2.0, cy = sin * cxp + cos * cyp + (y0 + y1) / 2.0;
    double t1 = Math.atan2((y1p - cyp) / ry, (x1p - cxp) / rx);
    double t2 = Math.atan2((-y1p - cyp) / ry, (-x1p - cxp) / rx);
    double dt = t2 - t1;
    if (sweep && dt < 0) dt += 2 * Math.PI;
    else if (!sweep && dt > 0) dt -= 2 * Math.PI;
    int segs = (int) Math.ceil(Math.abs(dt) / (Math.PI / 2) - 1e-7);
    if (segs < 1) segs = 1;
    double step = dt / segs, k = 4.0 / 3.0 * Math.tan(step / 4);
    double t = t1;
    for (int s = 0; s < segs; s++) {
      double c1 = Math.cos(t), s1 = Math.sin(t), c2 = Math.cos(t + step), s2 = Math.sin(t + step);
      double ax = c1 - k * s1, ay = s1 + k * c1, bx = c2 + k * s2, by = s2 - k * c2;
      p.cubicTo((float) (cx + rx * ax * cos - ry * ay * sin), (float) (cy + rx * ax * sin + ry * ay * cos),
          (float) (cx + rx * bx * cos - ry * by * sin), (float) (cy + rx * bx * sin + ry * by * cos),
          (float) (cx + rx * c2 * cos - ry * s2 * sin), (float) (cy + rx * c2 * sin + ry * s2 * cos));
      t += step;
    }
  }
}
