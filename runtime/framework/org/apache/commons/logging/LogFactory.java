package org.apache.commons.logging;

/** org.apache.commons.logging.LogFactory: logs to android.util.Log (info and above, as Android's own build does). */
public abstract class LogFactory {
  public static final String PRIORITY_KEY = "priority", TCCL_KEY = "use_tccl", FACTORY_PROPERTY = "org.apache.commons.logging.LogFactory",
      FACTORY_DEFAULT = "org.apache.commons.logging.impl.LogFactoryImpl", FACTORY_PROPERTIES = "commons-logging.properties";
  static final LogFactory INSTANCE = new LogFactory() {
    @Override public Object getAttribute(String name) { return null; }
    @Override public String[] getAttributeNames() { return new String[0]; }
    @Override public Log getInstance(Class clazz) { return getInstance(clazz.getName()); }
    @Override public Log getInstance(String name) { return new AndroidLog(name); }
    @Override public void release() {}
    @Override public void removeAttribute(String name) {}
    @Override public void setAttribute(String name, Object value) {}
  };
  protected LogFactory() {}
  public abstract Object getAttribute(String name);
  public abstract String[] getAttributeNames();
  public abstract Log getInstance(Class clazz) throws LogConfigurationException;
  public abstract Log getInstance(String name) throws LogConfigurationException;
  public abstract void release();
  public abstract void removeAttribute(String name);
  public abstract void setAttribute(String name, Object value);
  public static LogFactory getFactory() throws LogConfigurationException { return INSTANCE; }
  public static Log getLog(Class clazz) throws LogConfigurationException { return INSTANCE.getInstance(clazz); }
  public static Log getLog(String name) throws LogConfigurationException { return INSTANCE.getInstance(name); }
  public static void release(ClassLoader classLoader) {}
  public static void releaseAll() {}
  public static String objectId(Object o) { return o == null ? "null" : o.getClass().getName() + "@" + System.identityHashCode(o); }

  static final class AndroidLog implements Log {
    final String tag;
    AndroidLog(String name) { int d = name.lastIndexOf('.'); String t = d >= 0 ? name.substring(d + 1) : name; tag = t.length() > 23 ? t.substring(0, 23) : t; }
    public boolean isDebugEnabled() { return false; }
    public boolean isErrorEnabled() { return true; }
    public boolean isFatalEnabled() { return true; }
    public boolean isInfoEnabled() { return true; }
    public boolean isTraceEnabled() { return false; }
    public boolean isWarnEnabled() { return true; }
    public void trace(Object m) {}
    public void trace(Object m, Throwable t) {}
    public void debug(Object m) {}
    public void debug(Object m, Throwable t) {}
    public void info(Object m) { android.util.Log.i(tag, String.valueOf(m)); }
    public void info(Object m, Throwable t) { android.util.Log.i(tag, String.valueOf(m), t); }
    public void warn(Object m) { android.util.Log.w(tag, String.valueOf(m)); }
    public void warn(Object m, Throwable t) { android.util.Log.w(tag, String.valueOf(m), t); }
    public void error(Object m) { android.util.Log.e(tag, String.valueOf(m)); }
    public void error(Object m, Throwable t) { android.util.Log.e(tag, String.valueOf(m), t); }
    public void fatal(Object m) { android.util.Log.e(tag, String.valueOf(m)); }
    public void fatal(Object m, Throwable t) { android.util.Log.e(tag, String.valueOf(m), t); }
  }
}
