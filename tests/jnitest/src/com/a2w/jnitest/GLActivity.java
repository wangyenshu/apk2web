package com.a2w.jnitest;

import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/** OpenGL ES checks: GLES 2.0 from Java, GL from native code, and GLES 1.1 fixed function; read back with glReadPixels. */
public class GLActivity extends Activity implements GLSurfaceView.Renderer {
  static native void nativeClear(float r, float g, float b);
  GLSurfaceView view;
  int frame, prog;

  @Override protected void onCreate(Bundle b) {
    super.onCreate(b);
    view = new GLSurfaceView(this);
    view.setEGLContextClientVersion(2);
    view.setRenderer(this);
    setContentView(view);
  }
  public void onSurfaceCreated(GL10 gl, EGLConfig c) {
    int vs = shader(GLES20.GL_VERTEX_SHADER, "attribute vec2 p; void main() { gl_Position = vec4(p, 0.0, 1.0); }");
    int fs = shader(GLES20.GL_FRAGMENT_SHADER, "precision mediump float; uniform vec4 c; void main() { gl_FragColor = c; }");
    prog = GLES20.glCreateProgram();
    GLES20.glAttachShader(prog, vs);
    GLES20.glAttachShader(prog, fs);
    GLES20.glBindAttribLocation(prog, 0, "p");
    GLES20.glLinkProgram(prog);
    int[] ok = new int[1];
    GLES20.glGetProgramiv(prog, GLES20.GL_LINK_STATUS, ok, 0);
    check("GLES20 program links", ok[0] == 1, GLES20.glGetProgramInfoLog(prog));
  }
  static int shader(int type, String src) {
    int s = GLES20.glCreateShader(type);
    GLES20.glShaderSource(s, src);
    GLES20.glCompileShader(s);
    return s;
  }
  public void onSurfaceChanged(GL10 gl, int w, int h) { GLES20.glViewport(0, 0, w, h); }
  public void onDrawFrame(GL10 gl) {
    frame++;
    int w = view.getWidth(), h = view.getHeight();
    if (frame == 1) {
      nativeClear(1, 0, 0);                       // red, from native code
      GLES20.glUseProgram(prog);
      GLES20.glUniform4f(GLES20.glGetUniformLocation(prog, "c"), 0, 1, 0, 1);
      FloatBuffer tri = floats(-0.5f, -0.5f, 0.5f, -0.5f, 0f, 0.5f);
      GLES20.glEnableVertexAttribArray(0);
      GLES20.glVertexAttribPointer(0, 2, GLES20.GL_FLOAT, false, 0, tri);   // client-side array
      GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
      check("native glClear (corner red)", pixel(2, 2) == 0xffff0000, hex(pixel(2, 2)));
      check("GLES20 triangle (center green)", pixel(w / 2, h / 2) == 0xff00ff00, hex(pixel(w / 2, h / 2)));
      int[] vbo = new int[1];
      GLES20.glGenBuffers(1, vbo, 0);
      GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
      FloatBuffer quad = floats(-1, -1, 1, -1, -1, 1, 1, 1);
      GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, 32, quad, GLES20.GL_STATIC_DRAW);
      GLES20.glVertexAttribPointer(0, 2, GLES20.GL_FLOAT, false, 0, 0);
      GLES20.glUniform4f(GLES20.glGetUniformLocation(prog, "c"), 0, 0, 1, 1);
      GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
      GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
      check("GLES20 buffer object (all blue)", pixel(2, 2) == 0xff0000ff, hex(pixel(2, 2)));
      check("glGetError", GLES20.glGetError() == 0, null);
      if (android.os.Build.VERSION.SDK_INT >= 18) try {
        int[] vao = new int[1];
        android.opengl.GLES30.glGenVertexArrays(1, vao, 0);
        android.opengl.GLES30.glBindVertexArray(vao[0]);
        boolean isVao = android.opengl.GLES30.glIsVertexArray(vao[0]);
        android.opengl.GLES30.glBindVertexArray(0);
        int[] b = new int[1];
        GLES20.glGenBuffers(1, b, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, b[0]);
        java.nio.ByteBuffer data = java.nio.ByteBuffer.allocateDirect(8);
        data.put(new byte[] {1, 2, 3, 4, 5, 6, 7, 8}).rewind();
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, 8, data, GLES20.GL_DYNAMIC_DRAW);
        java.nio.ByteBuffer m = (java.nio.ByteBuffer) android.opengl.GLES30.glMapBufferRange(GLES20.GL_ARRAY_BUFFER, 2, 4,
            android.opengl.GLES30.GL_MAP_READ_BIT | android.opengl.GLES30.GL_MAP_WRITE_BIT);
        int r0 = m.get(0);
        m.put(1, (byte) 42);
        boolean unmapped = android.opengl.GLES30.glUnmapBuffer(GLES20.GL_ARRAY_BUFFER);
        java.nio.ByteBuffer m2 = (java.nio.ByteBuffer) android.opengl.GLES30.glMapBufferRange(GLES20.GL_ARRAY_BUFFER, 0, 8, android.opengl.GLES30.GL_MAP_READ_BIT);
        int r1 = m2.get(3);
        android.opengl.GLES30.glUnmapBuffer(GLES20.GL_ARRAY_BUFFER);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        long sync = android.opengl.GLES30.glFenceSync(android.opengl.GLES30.GL_SYNC_GPU_COMMANDS_COMPLETE, 0);
        boolean isSync = android.opengl.GLES30.glIsSync(sync);
        android.opengl.GLES30.glDeleteSync(sync);
        String got = isVao + " " + r0 + " " + unmapped + " " + r1 + " " + isSync + " " + GLES20.glGetError();
        check("GLES30 vertex arrays, mapped buffers, sync", got.equals("true 3 true 42 true 0"), got);
      } catch (Throwable t) { check("GLES30 vertex arrays, mapped buffers, sync", false, String.valueOf(t)); }
    } else if (frame == 2) {
      // GLES 1.1 through the GL10 object
      GLES20.glUseProgram(0);
      gl.glClearColor(0, 0, 1, 1);
      gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
      gl.glMatrixMode(GL10.GL_PROJECTION);
      gl.glLoadIdentity();
      gl.glOrthof(-1, 1, -1, 1, -1, 1);
      gl.glMatrixMode(GL10.GL_MODELVIEW);
      gl.glLoadIdentity();
      gl.glColor4f(1, 1, 0, 1);
      gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
      gl.glVertexPointer(2, GL10.GL_FLOAT, 0, floats(-0.5f, -0.5f, 0.5f, -0.5f, 0f, 0.5f));
      gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
      check("GL10 fixed function (center yellow)", pixel(w / 2, h / 2) == 0xffffff00, hex(pixel(w / 2, h / 2)));
      check("GL10 clear (corner blue)", pixel(2, 2) == 0xff0000ff, hex(pixel(2, 2)));
      Log.i("JNITEST", "GL DONE");
      view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
  }
  static FloatBuffer floats(float... v) {
    FloatBuffer b = ByteBuffer.allocateDirect(v.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    b.put(v).position(0);
    return b;
  }
  static int pixel(int x, int y) {
    ByteBuffer b = ByteBuffer.allocateDirect(4);
    GLES20.glReadPixels(x, y, 1, 1, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, b);
    return (b.get(3) & 0xff) << 24 | (b.get(0) & 0xff) << 16 | (b.get(1) & 0xff) << 8 | (b.get(2) & 0xff);
  }
  static String hex(int v) { return Integer.toHexString(v); }
  static void check(String what, boolean ok, String detail) { Log.i("JNITEST", (ok ? "PASS " : "FAIL ") + "GL: " + what + (detail == null || ok ? "" : "  [" + detail + "]")); }
}
