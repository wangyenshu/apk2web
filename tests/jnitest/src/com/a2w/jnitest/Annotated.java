package com.a2w.jnitest;

/** what EventBus and Gson do: find methods and fields by their runtime annotations, read the values, call them */
final class Annotated {
  public static final class Listener {
    String got = "";
    @AnnName("first_name") public String first = "Ada";
    public String plain = "x";
    @AnnSubscribe(type = String.class, priority = 3, tags = {"a", "b"}) public void onMessage(String s) { got += s; }
    public void notAnnotated(String s) { got += "!"; }
  }

  static String run() throws Exception {
    Listener l = new Listener();
    StringBuilder out = new StringBuilder();
    for (java.lang.reflect.Method m : Listener.class.getDeclaredMethods()) {
      AnnSubscribe s = m.getAnnotation(AnnSubscribe.class);
      if (s == null) continue;
      out.append(m.getName()).append(' ').append(s.type().getSimpleName()).append(s.sticky()).append(' ').append(s.priority()).append(' ');
      for (String t : s.tags()) out.append(t);
      m.invoke(l, "hi");
    }
    AnnName n = Listener.class.getDeclaredField("first").getAnnotation(AnnName.class);
    out.append(' ').append(n == null ? "none" : n.value()).append(' ').append(l.got);
    out.append(' ').append(Listener.class.getDeclaredField("plain").getAnnotation(AnnName.class) == null);
    return out.toString();
  }
}
