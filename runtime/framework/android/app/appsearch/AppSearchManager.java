// From the Android SDK's declaration (tools/genapi.sh), moved here so the runtime can create it as a system service;
// members marked @a2w.NotImplemented are stubs.
package android.app.appsearch;

public class AppSearchManager {
  /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
  public AppSearchManager(a2w.Hidden hidden) { }
  @a2w.NotImplemented public void createEnterpriseGlobalSearchSession(java.util.concurrent.Executor executor, java.util.function.Consumer<android.app.appsearch.AppSearchResult<android.app.appsearch.EnterpriseGlobalSearchSession>> callback) { a2w.Stubs.hit("android.app.appsearch.AppSearchManager.createEnterpriseGlobalSearchSession"); }
  @a2w.NotImplemented public void createGlobalSearchSession(java.util.concurrent.Executor executor, java.util.function.Consumer<android.app.appsearch.AppSearchResult<android.app.appsearch.GlobalSearchSession>> callback) { a2w.Stubs.hit("android.app.appsearch.AppSearchManager.createGlobalSearchSession"); }
  @a2w.NotImplemented public void createSearchSession(android.app.appsearch.AppSearchManager.SearchContext searchContext, java.util.concurrent.Executor executor, java.util.function.Consumer<android.app.appsearch.AppSearchResult<android.app.appsearch.AppSearchSession>> callback) { a2w.Stubs.hit("android.app.appsearch.AppSearchManager.createSearchSession"); }
  @a2w.NotImplemented public android.app.appsearch.functions.AppFunctionManager getAppFunctionManager() { a2w.Stubs.hit("android.app.appsearch.AppSearchManager.getAppFunctionManager"); return null; }

    public static final class SearchContext {
    /** (state of the property databaseName, not part of the API) */ public java.lang.String a2w$databaseName;
    /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
    public SearchContext(a2w.Hidden hidden) { }
    @a2w.Derived public java.lang.String getDatabaseName() { return this.a2w$databaseName; }

        public static final class Builder {
      private java.lang.String a2w$databaseName;
      /** for subclasses, this class's own constructors and the runtime's service objects (not part of the API) */
      public Builder(a2w.Hidden hidden) { }
      @a2w.Derived public Builder(java.lang.String databaseName) { this((a2w.Hidden) null); this.a2w$databaseName = databaseName; }
      @a2w.Derived public android.app.appsearch.AppSearchManager.SearchContext build() { android.app.appsearch.AppSearchManager.SearchContext x = new android.app.appsearch.AppSearchManager.SearchContext((a2w.Hidden) null); x.a2w$databaseName = this.a2w$databaseName; return x; }
    }
  }
}
