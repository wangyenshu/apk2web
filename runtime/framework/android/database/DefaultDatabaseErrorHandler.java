package android.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/** As on Android: a corrupt database file is deleted (so the app recreates it). */
public final class DefaultDatabaseErrorHandler implements DatabaseErrorHandler {
  public void onCorruption(SQLiteDatabase dbObj) {
    Log.e("DefaultDatabaseErrorHandler", "Corruption reported by sqlite on database: " + dbObj.getPath());
    String path = dbObj.getPath();
    if (path == null || path.equalsIgnoreCase(":memory:") || path.trim().isEmpty()) return;
    try { dbObj.close(); } catch (RuntimeException e) {}
    SQLiteDatabase.deleteDatabase(new java.io.File(path));
  }
}
