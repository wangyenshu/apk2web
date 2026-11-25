package a2w.jdk.util.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * java.util.concurrent.Phaser. A phaser with a parent registers with it as one party while it has parties of its
 * own, arrives there when all of its parties arrived, and shares the root's phase; waits use the root's monitor.
 */
public class Phaser {
  private static final int MAX_PHASE = Integer.MAX_VALUE, MAX_PARTIES = 0xffff;
  private final Phaser parent, root;
  private int phase;            // the root's phase is the current one; negative when terminated
  private int parties, unarrived;

  public Phaser() { this(null, 0); }
  public Phaser(int parties) { this(null, parties); }
  public Phaser(Phaser parent) { this(parent, 0); }
  public Phaser(Phaser parent, int parties) {
    if (parties < 0 || parties > MAX_PARTIES) throw new IllegalArgumentException("Illegal number of parties");
    this.parent = parent;
    this.root = parent == null ? this : parent.root;
    if (parent != null && parties > 0) parent.doRegister(1);
    this.parties = this.unarrived = parties;
  }

  private int rootPhase() { return root.phase; }
  private int doRegister(int n) {
    if (n < 0 || n > MAX_PARTIES) throw new IllegalArgumentException();
    boolean registerParent;
    int p;
    synchronized (root) {
      p = rootPhase();
      if (p < 0) return p;
      if (parties + n > MAX_PARTIES) throw new IllegalStateException("Attempt to register more than " + MAX_PARTIES + " parties for " + this);
      registerParent = parent != null && parties == 0 && n > 0;
      parties += n; unarrived += n;
    }
    if (registerParent) parent.doRegister(1);
    return p;
  }
  public int register() { return doRegister(1); }
  public int bulkRegister(int parties) { if (parties < 0) throw new IllegalArgumentException(); return parties == 0 ? getPhase() : doRegister(parties); }

  private int doArrive(boolean deregister) {
    boolean toParent = false, parentDereg = false;
    int p;
    synchronized (root) {
      p = rootPhase();
      if (p < 0) return p;
      if (unarrived <= 0) throw new IllegalStateException("Attempted arrival of unregistered party for " + this);
      unarrived--;
      if (deregister) parties--;
      if (unarrived == 0) {
        if (parent == null) {
          if (onAdvance(p, parties)) phase = p | Integer.MIN_VALUE;
          else phase = (p + 1) & MAX_PHASE;
          unarrived = parties;
          root.notifyAll();
        } else {
          unarrived = parties;
          toParent = true;
          parentDereg = parties == 0;
        }
      }
    }
    if (toParent) parent.doArrive(parentDereg);
    return p;
  }
  public int arrive() { return doArrive(false); }
  public int arriveAndDeregister() { return doArrive(true); }
  public int arriveAndAwaitAdvance() {
    int p = doArrive(false);
    return p < 0 ? p : awaitAdvance(p);
  }
  public int awaitAdvance(int phase) {
    if (phase < 0) return phase;
    synchronized (root) {
      boolean interrupted = false;
      while (rootPhase() == phase) { try { root.wait(); } catch (InterruptedException e) { interrupted = true; } }
      if (interrupted) Thread.currentThread().interrupt();
      return rootPhase();
    }
  }
  public int awaitAdvanceInterruptibly(int phase) throws InterruptedException {
    if (phase < 0) return phase;
    synchronized (root) {
      while (rootPhase() == phase) root.wait();
      return rootPhase();
    }
  }
  public int awaitAdvanceInterruptibly(int phase, long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
    if (phase < 0) return phase;
    long deadline = System.currentTimeMillis() + Math.max(1, unit.toMillis(timeout));
    synchronized (root) {
      while (rootPhase() == phase) {
        long left = deadline - System.currentTimeMillis();
        if (left <= 0) throw new TimeoutException();
        root.wait(left);
      }
      return rootPhase();
    }
  }
  public void forceTermination() {
    synchronized (root) {
      if (root.phase >= 0) { root.phase |= Integer.MIN_VALUE; root.notifyAll(); }
    }
  }
  public final int getPhase() { synchronized (root) { return rootPhase(); } }
  public int getRegisteredParties() { synchronized (root) { return parties; } }
  public int getArrivedParties() { synchronized (root) { return parties - unarrived; } }
  public int getUnarrivedParties() { synchronized (root) { return unarrived; } }
  public Phaser getParent() { return parent; }
  public Phaser getRoot() { return root; }
  public boolean isTerminated() { return getPhase() < 0; }
  protected boolean onAdvance(int phase, int registeredParties) { return registeredParties == 0; }
  public String toString() {
    synchronized (root) {
      return super.toString() + "[phase = " + rootPhase() + " parties = " + parties + " arrived = " + (parties - unarrived) + "]";
    }
  }
}
