package android.os;

/** android.os.Process: the page is one process with one app uid. */
public class Process {
  public static final int THREAD_PRIORITY_BACKGROUND = 10, THREAD_PRIORITY_DEFAULT = 0, THREAD_PRIORITY_LOWEST = 19,
      THREAD_PRIORITY_DISPLAY = -4, THREAD_PRIORITY_URGENT_DISPLAY = -8, THREAD_PRIORITY_FOREGROUND = -2,
      THREAD_PRIORITY_AUDIO = -16, THREAD_PRIORITY_URGENT_AUDIO = -19, THREAD_PRIORITY_VIDEO = -10,
      THREAD_PRIORITY_MORE_FAVORABLE = -1, THREAD_PRIORITY_LESS_FAVORABLE = 1;
  public static final int ROOT_UID = 0, SYSTEM_UID = 1000, PHONE_UID = 1001, SHELL_UID = 2000, LOG_UID = 1007, WIFI_UID = 1010,
      MEDIA_UID = 1013, NFC_UID = 1027, BLUETOOTH_UID = 1002, FIRST_APPLICATION_UID = 10000, LAST_APPLICATION_UID = 19999,
      FIRST_ISOLATED_UID = 99000, LAST_ISOLATED_UID = 99999, INVALID_UID = -1;
  public static final int SIGNAL_QUIT = 3, SIGNAL_KILL = 9, SIGNAL_USR1 = 10;
  static final long START = SystemClock.elapsedRealtime();
  public static int myPid() { return 4242; }
  public static int myTid() { return 4242; }
  public static int myUid() { return 10042; }
  public static UserHandle myUserHandle() { return UserHandle.SYSTEM; }
  public static boolean isApplicationUid(int uid) { return UserHandle.isApp(uid); }
  public static boolean isIsolated() { return false; }
  public static boolean is64Bit() { return false; }
  public static long getElapsedCpuTime() { return SystemClock.elapsedRealtime() - START; }
  public static long getStartElapsedRealtime() { return START; }
  public static long getStartUptimeMillis() { return START; }
  public static String myProcessName() { return a2w.Res.pkg(); }
  public static int getUidForName(String name) { return -1; }
  public static int getGidForName(String name) { return -1; }
  public static void killProcess(int pid) { if (pid == myPid()) a2w.Host.exitApp(); }
  public static void setThreadPriority(int p) {}
  public static void setThreadPriority(int t, int p) {}
  public static int getThreadPriority(int t) { return 0; }
  public static void setProcessGroup(int pid, int group) {}
  public static void sendSignal(int pid, int sig) { if (pid == myPid() && sig == SIGNAL_KILL) a2w.Host.exitApp(); }
  public static final boolean supportsProcesses() { return true; }
}
