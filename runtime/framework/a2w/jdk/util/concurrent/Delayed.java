package a2w.jdk.util.concurrent;

import java.util.concurrent.TimeUnit;

public interface Delayed extends Comparable<Object> {
  long getDelay(TimeUnit unit);
}
