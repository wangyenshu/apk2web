package android.graphics;

import org.teavm.jso.JSObject;

/** shaderA, then shaderB composited over it with the mode (the page renders both at the canvas's size) */
public class ComposeShader extends Shader {
  final Shader a, b;
  final String mode;
  public ComposeShader(Shader shaderA, Shader shaderB, Xfermode mode) { this(shaderA, shaderB, mode instanceof PorterDuffXfermode ? ((PorterDuffXfermode) mode).mode : PorterDuff.Mode.SRC_OVER); }
  public ComposeShader(Shader shaderA, Shader shaderB, PorterDuff.Mode mode) {
    if (shaderA == null || shaderB == null) throw new IllegalArgumentException("Shader parameters must not be null");
    a = shaderA; b = shaderB; this.mode = mode.name();
  }
  public ComposeShader(Shader shaderA, Shader shaderB, BlendMode blendMode) {
    if (shaderA == null || shaderB == null) throw new IllegalArgumentException("Shader parameters must not be null");
    a = shaderA; b = shaderB;
    String n = blendMode.name();
    mode = n.equals("PLUS") ? "ADD" : n.equals("MODULATE") ? "MULTIPLY" : n;
  }
  @Override int version() { return gen * 961 + a.version() * 31 + b.version(); }
  @Override JSObject js() {
    JSObject ja = a.js(), jb = b.js();
    if (ja == null || jb == null) return ja == null ? jb : ja;
    return make(5, localJs(), null, null, null, ja, jb, 0, 0, mode);
  }
}
