package android.text.style;

public interface TabStopSpan extends ParagraphStyle {
  int getTabStop();
  class Standard implements TabStopSpan {
    private final int tab;
    public Standard(int where) { tab = where; }
    @Override public int getTabStop() { return tab; }
  }
}
