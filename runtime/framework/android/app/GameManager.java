// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app;

public final class GameManager {
  public static final int GAME_MODE_BATTERY = 3;
  public static final int GAME_MODE_CUSTOM = 4;
  public static final int GAME_MODE_PERFORMANCE = 2;
  public static final int GAME_MODE_STANDARD = 1;
  public static final int GAME_MODE_UNSUPPORTED = 0;
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public GameManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public int getGameMode() { a2w.Stubs.hit("android.app.GameManager.getGameMode"); return 0; }
  @a2w.NotImplemented public void setGameState(android.app.GameState gameState) { a2w.Stubs.hit("android.app.GameManager.setGameState"); }
}
