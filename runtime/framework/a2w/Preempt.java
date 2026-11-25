package a2w;

import org.teavm.jso.JSBody;

/**
 * Time slices for Java threads. TeaVM's threads only switch when one waits (sleep, wait, a monitor, I/O); a loop that
 * waits for a flag without waiting (a game loop while paused, a spin lock) would hold the page forever. The linker
 * calls tick() on every backward jump in the app's code; a thread that has run for a slice without waiting sleeps
 * for a moment, so the page and the other threads run, as a preemptive scheduler would let them.
 */
public final class Preempt {
  private Preempt() {}
  private static int budget = 2000;
  private static long last, sliceStart;
  @JSBody(script = "return typeof HW !== 'undefined' && !!HW.inNative && HW.inNative();")
  private static native boolean inNative();

  public static void tick() {
    if (--budget > 0) return;
    budget = 2000;
    long now = System.currentTimeMillis();
    if (now - last > 8) sliceStart = now;   // the thread waited since the last look: a new slice
    last = now;
    if (now - sliceStart < 40 || inNative()) return;   // (Java code native code called cannot suspend)
    try { Thread.sleep(1); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    last = sliceStart = System.currentTimeMillis();
  }
}
