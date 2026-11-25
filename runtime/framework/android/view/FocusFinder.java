package android.view;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Finds the next view to take focus in a direction, after AOSP's FocusFinder: user-set
 * nextFocus*Id links first, then for FOCUS_FORWARD/BACKWARD the reading order of the focusables
 * (rows top to bottom, left to right), and for the arrow directions the nearest view by the beam
 * rules (a view overlapping the source's beam wins, then a weighted major/minor axis distance).
 */
public class FocusFinder {
  private static FocusFinder sInstance;
  FocusFinder() {}
  public static FocusFinder getInstance() { if (sInstance == null) sInstance = new FocusFinder(); return sInstance; }

  final Rect mFocusedRect = new Rect(), mOtherRect = new Rect(), mBestCandidateRect = new Rect();
  private final ArrayList<View> mTempList = new ArrayList<>();

  public final View findNextFocus(ViewGroup root, View focused, int direction) { return findNextFocus(root, focused, null, direction); }
  public View findNextFocusFromRect(ViewGroup root, Rect focusedRect, int direction) {
    Rect r = new Rect(focusedRect);
    return findNextFocus(root, null, r, direction);
  }

  private ViewGroup getEffectiveRoot(ViewGroup root, View focused) {
    if (focused == null || focused == root) return root;
    ViewGroup effective = null;
    ViewParent next = focused.getParent();
    do {
      if (next == root) return effective != null ? effective : root;
      ViewGroup vg = (ViewGroup) next;
      if (vg.getTouchscreenBlocksFocus() && vg.isKeyboardNavigationCluster()) effective = vg;   // the outermost such cluster
      next = next.getParent();
    } while (next instanceof ViewGroup);
    return root;
  }

  private View findNextFocus(ViewGroup root, View focused, Rect focusedRect, int direction) {
    ViewGroup effectiveRoot = getEffectiveRoot(root, focused);
    View next = null;
    if (focused != null) next = findNextUserSpecifiedFocus(effectiveRoot, focused, direction);
    if (next != null) return next;
    ArrayList<View> focusables = new ArrayList<>();
    effectiveRoot.addFocusables(focusables, direction);
    if (!focusables.isEmpty()) next = findNextFocus(effectiveRoot, focused, focusedRect, direction, focusables);
    return next;
  }

  private View findNextUserSpecifiedFocus(ViewGroup root, View focused, int direction) {
    View userSetNextFocus = focused.findUserSetNextFocus(root, direction);
    View cycleCheck = userSetNextFocus;
    boolean cycleStep = true;
    while (userSetNextFocus != null) {
      if (userSetNextFocus.isFocusable() && userSetNextFocus.getVisibility() == View.VISIBLE && (!userSetNextFocus.isInTouchMode() || userSetNextFocus.isFocusableInTouchMode())) return userSetNextFocus;
      userSetNextFocus = userSetNextFocus.findUserSetNextFocus(root, direction);
      if (cycleStep = !cycleStep) {
        cycleCheck = cycleCheck.findUserSetNextFocus(root, direction);
        if (cycleCheck == userSetNextFocus) break;
      }
    }
    return null;
  }

  private View findNextFocus(ViewGroup root, View focused, Rect focusedRect, int direction, ArrayList<View> focusables) {
    if (focused != null) {
      if (focusedRect == null) focusedRect = mFocusedRect;
      focused.getFocusedRect(focusedRect);
      root.offsetDescendantRectToMyCoords(focused, focusedRect);
    } else if (focusedRect == null) {
      focusedRect = mFocusedRect;
      switch (direction) {
        case View.FOCUS_RIGHT: case View.FOCUS_DOWN: setFocusTopLeft(root, focusedRect); break;
        case View.FOCUS_FORWARD: if (root.isLayoutRtl()) setFocusBottomRight(root, focusedRect); else setFocusTopLeft(root, focusedRect); break;
        case View.FOCUS_LEFT: case View.FOCUS_UP: setFocusBottomRight(root, focusedRect); break;
        case View.FOCUS_BACKWARD: if (root.isLayoutRtl()) setFocusTopLeft(root, focusedRect); else setFocusBottomRight(root, focusedRect); break;
      }
    }
    switch (direction) {
      case View.FOCUS_FORWARD: case View.FOCUS_BACKWARD: return findNextFocusInRelativeDirection(focusables, root, focused, focusedRect, direction);
      case View.FOCUS_UP: case View.FOCUS_DOWN: case View.FOCUS_LEFT: case View.FOCUS_RIGHT: return findNextFocusInAbsoluteDirection(focusables, root, focused, focusedRect, direction);
      default: throw new IllegalArgumentException("Unknown direction: " + direction);
    }
  }

  private View findNextFocusInRelativeDirection(ArrayList<View> focusables, ViewGroup root, View focused, Rect focusedRect, int direction) {
    // nextFocusForwardId chains keep their order: a linked view follows the view linking to it
    sortByForwardLinks(focusables, root);
    final int count = focusables.size();
    switch (direction) {
      case View.FOCUS_FORWARD: return getNextFocusable(focused, focusables, count);
      case View.FOCUS_BACKWARD: return getPreviousFocusable(focused, focusables, count);
    }
    return focusables.get(count - 1);
  }
  private static void sortByForwardLinks(ArrayList<View> focusables, ViewGroup root) {
    boolean any = false;
    for (View v : focusables) if (v.getNextFocusForwardId() != View.NO_ID) { any = true; break; }
    if (!any) return;
    ArrayList<View> out = new ArrayList<>(focusables.size());
    java.util.HashSet<View> placed = new java.util.HashSet<>();
    java.util.HashSet<View> linkedTo = new java.util.HashSet<>();
    for (View v : focusables) {
      View n = v.getNextFocusForwardId() == View.NO_ID ? null : v.findUserSetNextFocus(root, View.FOCUS_FORWARD);
      if (n != null && n != v && focusables.contains(n)) linkedTo.add(n);
    }
    for (View v : focusables) {
      if (placed.contains(v) || linkedTo.contains(v)) continue;
      for (View c = v; c != null && !placed.contains(c) && focusables.contains(c); ) {
        out.add(c); placed.add(c);
        c = c.getNextFocusForwardId() == View.NO_ID ? null : c.findUserSetNextFocus(root, View.FOCUS_FORWARD);
      }
    }
    for (View v : focusables) if (!placed.contains(v)) out.add(v);
    focusables.clear();
    focusables.addAll(out);
  }
  private static View getNextFocusable(View focused, ArrayList<View> focusables, int count) {
    if (count < 2) return null;
    if (focused != null) {
      int position = focusables.lastIndexOf(focused);
      if (position >= 0 && position + 1 < count) return focusables.get(position + 1);
    }
    return focusables.get(0);
  }
  private static View getPreviousFocusable(View focused, ArrayList<View> focusables, int count) {
    if (count < 2) return null;
    if (focused != null) {
      int position = focusables.indexOf(focused);
      if (position > 0) return focusables.get(position - 1);
    }
    return focusables.get(count - 1);
  }

  private void setFocusBottomRight(ViewGroup root, Rect focusedRect) {
    final int rootBottom = root.getScrollY() + root.getHeight(), rootRight = root.getScrollX() + root.getWidth();
    focusedRect.set(rootRight, rootBottom, rootRight, rootBottom);
  }
  private void setFocusTopLeft(ViewGroup root, Rect focusedRect) {
    final int rootTop = root.getScrollY(), rootLeft = root.getScrollX();
    focusedRect.set(rootLeft, rootTop, rootLeft, rootTop);
  }

  View findNextFocusInAbsoluteDirection(ArrayList<View> focusables, ViewGroup root, View focused, Rect focusedRect, int direction) {
    mBestCandidateRect.set(focusedRect);
    switch (direction) {
      case View.FOCUS_LEFT: mBestCandidateRect.offset(focusedRect.width() + 1, 0); break;
      case View.FOCUS_RIGHT: mBestCandidateRect.offset(-(focusedRect.width() + 1), 0); break;
      case View.FOCUS_UP: mBestCandidateRect.offset(0, focusedRect.height() + 1); break;
      case View.FOCUS_DOWN: mBestCandidateRect.offset(0, -(focusedRect.height() + 1)); break;
    }
    View closest = null;
    for (int i = 0, n = focusables.size(); i < n; i++) {
      View focusable = focusables.get(i);
      if (focusable == focused || focusable == root) continue;
      focusable.getFocusedRect(mOtherRect);
      root.offsetDescendantRectToMyCoords(focusable, mOtherRect);
      if (isBetterCandidate(direction, focusedRect, mOtherRect, mBestCandidateRect)) {
        mBestCandidateRect.set(mOtherRect);
        closest = focusable;
      }
    }
    return closest;
  }

  boolean isBetterCandidate(int direction, Rect source, Rect rect1, Rect rect2) {
    if (!isCandidate(source, rect1, direction)) return false;
    if (!isCandidate(source, rect2, direction)) return true;
    if (beamBeats(direction, source, rect1, rect2)) return true;
    if (beamBeats(direction, source, rect2, rect1)) return false;
    return getWeightedDistanceFor(majorAxisDistance(direction, source, rect1), minorAxisDistance(direction, source, rect1))
        < getWeightedDistanceFor(majorAxisDistance(direction, source, rect2), minorAxisDistance(direction, source, rect2));
  }
  boolean beamBeats(int direction, Rect source, Rect rect1, Rect rect2) {
    final boolean rect1InSrcBeam = beamsOverlap(direction, source, rect1), rect2InSrcBeam = beamsOverlap(direction, source, rect2);
    if (rect2InSrcBeam || !rect1InSrcBeam) return false;
    if (!isToDirectionOf(direction, source, rect2)) return true;
    if (direction == View.FOCUS_LEFT || direction == View.FOCUS_RIGHT) return true;
    return majorAxisDistance(direction, source, rect1) < majorAxisDistanceToFarEdge(direction, source, rect2);
  }
  int getWeightedDistanceFor(long majorAxisDistance, long minorAxisDistance) {
    long d = 13 * majorAxisDistance * majorAxisDistance + minorAxisDistance * minorAxisDistance;
    return d > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) d;
  }
  boolean isCandidate(Rect srcRect, Rect destRect, int direction) {
    switch (direction) {
      case View.FOCUS_LEFT: return (srcRect.right > destRect.right || srcRect.left >= destRect.right) && srcRect.left > destRect.left;
      case View.FOCUS_RIGHT: return (srcRect.left < destRect.left || srcRect.right <= destRect.left) && srcRect.right < destRect.right;
      case View.FOCUS_UP: return (srcRect.bottom > destRect.bottom || srcRect.top >= destRect.bottom) && srcRect.top > destRect.top;
      case View.FOCUS_DOWN: return (srcRect.top < destRect.top || srcRect.bottom <= destRect.top) && srcRect.bottom < destRect.bottom;
    }
    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
  }
  boolean beamsOverlap(int direction, Rect rect1, Rect rect2) {
    switch (direction) {
      case View.FOCUS_LEFT: case View.FOCUS_RIGHT: return rect2.bottom > rect1.top && rect2.top < rect1.bottom;
      case View.FOCUS_UP: case View.FOCUS_DOWN: return rect2.right > rect1.left && rect2.left < rect1.right;
    }
    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
  }
  boolean isToDirectionOf(int direction, Rect src, Rect dest) {
    switch (direction) {
      case View.FOCUS_LEFT: return src.left >= dest.right;
      case View.FOCUS_RIGHT: return src.right <= dest.left;
      case View.FOCUS_UP: return src.top >= dest.bottom;
      case View.FOCUS_DOWN: return src.bottom <= dest.top;
    }
    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
  }
  static int majorAxisDistance(int direction, Rect source, Rect dest) { return Math.max(0, majorAxisDistanceRaw(direction, source, dest)); }
  static int majorAxisDistanceRaw(int direction, Rect source, Rect dest) {
    switch (direction) {
      case View.FOCUS_LEFT: return source.left - dest.right;
      case View.FOCUS_RIGHT: return dest.left - source.right;
      case View.FOCUS_UP: return source.top - dest.bottom;
      case View.FOCUS_DOWN: return dest.top - source.bottom;
    }
    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
  }
  static int majorAxisDistanceToFarEdge(int direction, Rect source, Rect dest) { return Math.max(1, majorAxisDistanceToFarEdgeRaw(direction, source, dest)); }
  static int majorAxisDistanceToFarEdgeRaw(int direction, Rect source, Rect dest) {
    switch (direction) {
      case View.FOCUS_LEFT: return source.left - dest.left;
      case View.FOCUS_RIGHT: return dest.right - source.right;
      case View.FOCUS_UP: return source.top - dest.top;
      case View.FOCUS_DOWN: return dest.bottom - source.bottom;
    }
    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
  }
  static int minorAxisDistance(int direction, Rect source, Rect dest) {
    switch (direction) {
      case View.FOCUS_LEFT: case View.FOCUS_RIGHT: return Math.abs((source.top + source.height() / 2) - (dest.top + dest.height() / 2));
      case View.FOCUS_UP: case View.FOCUS_DOWN: return Math.abs((source.left + source.width() / 2) - (dest.left + dest.width() / 2));
    }
    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
  }

  /** the touchable view nearest to (x, y) in a direction, within the edge slop; deltas gets the offset to it */
  public View findNearestTouchable(ViewGroup root, int x, int y, int direction, int[] deltas) {
    ArrayList<View> touchables = root.getTouchables();
    int minDistance = Integer.MAX_VALUE;
    View closest = null;
    int edgeSlop = ViewConfiguration.get(root.getContext()).getScaledEdgeSlop();
    Rect closestBounds = new Rect(), touchableBounds = mOtherRect;
    for (int i = 0, n = touchables.size(); i < n; i++) {
      View touchable = touchables.get(i);
      touchable.getDrawingRect(touchableBounds);
      root.offsetDescendantRectToMyCoords(touchable, touchableBounds);
      if (!isTouchCandidate(x, y, touchableBounds, direction)) continue;
      int distance = Integer.MAX_VALUE;
      switch (direction) {
        case View.FOCUS_LEFT: distance = x - touchableBounds.right + 1; break;
        case View.FOCUS_RIGHT: distance = touchableBounds.left; break;
        case View.FOCUS_UP: distance = y - touchableBounds.bottom + 1; break;
        case View.FOCUS_DOWN: distance = touchableBounds.top; break;
      }
      if (distance < edgeSlop) {
        if (closest == null || closestBounds.contains(touchableBounds) || (!touchableBounds.contains(closestBounds) && distance < minDistance)) {
          minDistance = distance;
          closest = touchable;
          closestBounds.set(touchableBounds);
          switch (direction) {
            case View.FOCUS_LEFT: deltas[0] = -distance; break;
            case View.FOCUS_RIGHT: deltas[0] = distance; break;
            case View.FOCUS_UP: deltas[1] = -distance; break;
            case View.FOCUS_DOWN: deltas[1] = distance; break;
          }
        }
      }
    }
    return closest;
  }
  private boolean isTouchCandidate(int x, int y, Rect destRect, int direction) {
    switch (direction) {
      case View.FOCUS_LEFT: return destRect.left <= x && destRect.top <= y && y <= destRect.bottom;
      case View.FOCUS_RIGHT: return destRect.left >= x && destRect.top <= y && y <= destRect.bottom;
      case View.FOCUS_UP: return destRect.top <= y && destRect.left <= x && x <= destRect.right;
      case View.FOCUS_DOWN: return destRect.top >= y && destRect.left <= x && x <= destRect.right;
    }
    throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
  }

  /** the next keyboard navigation cluster (Meta+Tab), in reading order, wrapping around; null when there is none */
  public View findNextKeyboardNavigationCluster(View root, View currentCluster, int direction) {
    ArrayList<View> clusters = new ArrayList<>();
    root.addKeyboardNavigationClusters(clusters, direction);
    if (clusters.isEmpty()) return null;
    if (root instanceof ViewGroup) {
      View[] a = clusters.toArray(new View[0]);
      sort(a, 0, a.length, (ViewGroup) root, root.isLayoutRtl());
      clusters.clear();
      clusters.addAll(Arrays.asList(a));
    }
    int n = clusters.size();
    int i = currentCluster == null ? -1 : clusters.indexOf(currentCluster);
    if (direction == View.FOCUS_BACKWARD) return clusters.get(i <= 0 ? n - 1 : i - 1);
    return clusters.get(i < 0 || i + 1 >= n ? 0 : i + 1);
  }

  /** sorts views in reading order: rows (views overlapping vertically) top to bottom, each row left to right */
  static void sort(View[] views, int start, int end, ViewGroup root, boolean isRtl) {
    int count = end - start;
    if (count < 2) return;
    final HashMap<View, Rect> rects = new HashMap<>();
    for (int i = start; i < end; ++i) {
      Rect r = new Rect();
      views[i].getDrawingRect(r);
      root.offsetDescendantRectToMyCoords(views[i], r);
      rects.put(views[i], r);
    }
    final int rtl = isRtl ? -1 : 1;
    Comparator<View> tops = (a, b) -> {
      if (a == b) return 0;
      Rect ra = rects.get(a), rb = rects.get(b);
      int d = ra.top - rb.top;
      return d == 0 ? ra.bottom - rb.bottom : d;
    };
    Comparator<View> sides = (a, b) -> {
      if (a == b) return 0;
      Rect ra = rects.get(a), rb = rects.get(b);
      int d = ra.left - rb.left;
      return d == 0 ? ra.right - rb.right : rtl * d;
    };
    Arrays.sort(views, start, end, tops);
    int sweepBottom = rects.get(views[start]).bottom;
    int rowStart = start;
    int sweepIdx = start + 1;
    for (; sweepIdx < end; ++sweepIdx) {
      Rect curr = rects.get(views[sweepIdx]);
      if (curr.top >= sweepBottom) {
        if (sweepIdx - rowStart > 1) Arrays.sort(views, rowStart, sweepIdx, sides);
        sweepBottom = curr.bottom;
        rowStart = sweepIdx;
      } else sweepBottom = Math.max(sweepBottom, curr.bottom);
    }
    if (sweepIdx - rowStart > 1) Arrays.sort(views, rowStart, sweepIdx, sides);
  }
}
