package android.preference;
/** (internal on Android) the tree-building contract the preference inflater uses */
public abstract class GenericInflater<T, P extends GenericInflater.Parent> {
  public interface Parent<T> { void addItemFromInflater(T child); }
  private GenericInflater() {}
}
