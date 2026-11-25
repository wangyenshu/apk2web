package android.graphics.drawable;

import java.util.ArrayList;

public class LevelListDrawable extends DrawableContainer {
  final ArrayList<int[]> ranges = new ArrayList<>();
  public void addLevel(int low, int high, Drawable d) { if (d != null) { ranges.add(new int[] {low, high}); addChild(d); onLevelChange(getLevel()); } }
  @Override protected boolean onLevelChange(int level) {
    for (int i = 0; i < ranges.size(); i++) if (level >= ranges.get(i)[0] && level <= ranges.get(i)[1]) { if (selectDrawable(i)) return true; return super.onLevelChange(level); }
    if (selectDrawable(-1)) return true;
    return super.onLevelChange(level);
  }
}
