package a2w.jdk.util.concurrent;

import java.util.NavigableSet;

/** java.util.concurrent.ConcurrentNavigableMap (TeaVM lacks it) */
public interface ConcurrentNavigableMap<K, V> extends java.util.concurrent.ConcurrentMap<K, V>, java.util.NavigableMap<K, V> {
  @Override ConcurrentNavigableMap<K, V> subMap(K from, boolean fromInclusive, K to, boolean toInclusive);
  @Override ConcurrentNavigableMap<K, V> headMap(K to, boolean inclusive);
  @Override ConcurrentNavigableMap<K, V> tailMap(K from, boolean inclusive);
  @Override ConcurrentNavigableMap<K, V> subMap(K from, K to);
  @Override ConcurrentNavigableMap<K, V> headMap(K to);
  @Override ConcurrentNavigableMap<K, V> tailMap(K from);
  @Override ConcurrentNavigableMap<K, V> descendingMap();
  @Override NavigableSet<K> navigableKeySet();
  @Override NavigableSet<K> keySet();
  @Override NavigableSet<K> descendingKeySet();
}
