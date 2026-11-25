package a2w.jdk.lang;

/** java.lang.Character.Subset: a named set of characters; equal only to itself. */
public class Character$Subset {
  private final String name;
  protected Character$Subset(String name) { this.name = name; }
  public final boolean equals(Object obj) { return this == obj; }
  public final int hashCode() { return super.hashCode(); }
  public final String toString() { return name; }
}
