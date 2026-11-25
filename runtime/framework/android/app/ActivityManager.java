package android.app;

import android.content.ComponentName;
import android.content.pm.ConfigurationInfo;
import java.util.ArrayList;
import java.util.List;

public class ActivityManager {
  public static class MemoryInfo { public long availMem = 512L << 20, threshold = 64L << 20, totalMem = 1024L << 20; public boolean lowMemory; }
  public static class RunningTaskInfo { public int id = 1, numActivities, numRunning; public ComponentName baseActivity, topActivity; public CharSequence description; }
  public static class RunningAppProcessInfo { public static final int IMPORTANCE_FOREGROUND = 100, IMPORTANCE_VISIBLE = 200, IMPORTANCE_BACKGROUND = 400; public String processName; public int pid, importance = IMPORTANCE_FOREGROUND; public String[] pkgList; }
  public static class RunningServiceInfo { public ComponentName service; public int pid; }
  public static class RecentTaskInfo { public int id; }
  public void getMemoryInfo(MemoryInfo m) { m.availMem = 512L << 20; m.lowMemory = false; }
  public int getMemoryClass() { return 64; }
  public android.os.Debug.MemoryInfo[] getProcessMemoryInfo(int[] pids) {
    android.os.Debug.MemoryInfo[] r = new android.os.Debug.MemoryInfo[pids == null ? 0 : pids.length];
    for (int i = 0; i < r.length; i++) { r[i] = new android.os.Debug.MemoryInfo(); android.os.Debug.getMemoryInfo(r[i]); }
    return r;
  }
  public boolean isLowRamDevice() { return false; }
  public int getLargeMemoryClass() { return 128; }
  public ConfigurationInfo getDeviceConfigurationInfo() { return new ConfigurationInfo(); }
  public List<RunningTaskInfo> getRunningTasks(int max) {
    ArrayList<RunningTaskInfo> l = new ArrayList<>();
    RunningTaskInfo t = new RunningTaskInfo();
    t.topActivity = a2w.ActivityThread.topComponent();
    t.baseActivity = t.topActivity;
    l.add(t);
    return l;
  }
  public List<RunningAppProcessInfo> getRunningAppProcesses() {
    ArrayList<RunningAppProcessInfo> l = new ArrayList<>();
    RunningAppProcessInfo p = new RunningAppProcessInfo();
    p.processName = a2w.Res.pkg(); p.pid = android.os.Process.myPid(); p.pkgList = new String[] {p.processName};
    l.add(p);
    return l;
  }
  public List<RunningServiceInfo> getRunningServices(int max) { return new ArrayList<>(); }
  public void killBackgroundProcesses(String p) {}
  public void restartPackage(String p) {}
  public static boolean isUserAMonkey() { return false; }
  public static boolean isRunningInTestHarness() { return false; }
}
