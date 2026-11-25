package android.os;

import java.io.File;

public class Environment {
  public static final String MEDIA_MOUNTED = "mounted", MEDIA_REMOVED = "removed", MEDIA_UNMOUNTED = "unmounted",
      MEDIA_SHARED = "shared", MEDIA_MOUNTED_READ_ONLY = "mounted_ro";
  public static String DIRECTORY_DOWNLOADS = "Download", DIRECTORY_PICTURES = "Pictures", DIRECTORY_MUSIC = "Music", DIRECTORY_MOVIES = "Movies",
      DIRECTORY_DCIM = "DCIM", DIRECTORY_DOCUMENTS = "Documents", DIRECTORY_PODCASTS = "Podcasts", DIRECTORY_RINGTONES = "Ringtones",
      DIRECTORY_ALARMS = "Alarms", DIRECTORY_NOTIFICATIONS = "Notifications";
  public static File getExternalStorageDirectory() { return new File("/sdcard"); }
  public static File getDataDirectory() { return new File("/data"); }
  public static File getRootDirectory() { return new File("/system"); }
  public static File getDownloadCacheDirectory() { return new File("/cache"); }
  public static File getExternalStoragePublicDirectory(String t) { return new File("/sdcard/" + t); }
  public static String getExternalStorageState() { return MEDIA_MOUNTED; }
  public static String getExternalStorageState(File path) { return MEDIA_MOUNTED; }
  public static String getStorageState(File path) { return MEDIA_MOUNTED; }
  public static boolean isExternalStorageRemovable() { return false; }
  public static boolean isExternalStorageEmulated() { return true; }
}
