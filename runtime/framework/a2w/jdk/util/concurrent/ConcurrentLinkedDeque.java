package a2w.jdk.util.concurrent;

import java.util.Collection;

public class ConcurrentLinkedDeque<E> extends java.util.ArrayDeque<E> {
  public ConcurrentLinkedDeque() {}
  public ConcurrentLinkedDeque(Collection<? extends E> c) { super(c); }
}
