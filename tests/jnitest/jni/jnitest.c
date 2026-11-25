// Native half of the apk2web JNI conformance test (ARMv5TE, built with clang; libc comes from the emulator's host).
#include <jni.h>

int snprintf(char *, __SIZE_TYPE__, const char *, ...);
int strcmp(const char *, const char *);
int open(const char *, int, ...);
int *__errno(void);
char *strerror(int);


static char out[4096];
static int pos;
static void check(const char *what, int ok) {
  pos += snprintf(out + pos, sizeof out - pos, "%s %s\n", ok ? "PASS" : "FAIL", what);
}

JNIEXPORT jstring JNICALL Java_com_a2w_jnitest_MainActivity_nativeRun(JNIEnv *env, jobject self) {
  pos = 0;
  out[0] = 0;
  jclass cls = (*env)->FindClass(env, "com/a2w/jnitest/MainActivity");
  check("FindClass", cls != 0);
  jclass oc = (*env)->GetObjectClass(env, self);
  check("GetObjectClass + IsInstanceOf", oc && (*env)->IsInstanceOf(env, self, cls));
  check("IsAssignableFrom", (*env)->IsAssignableFrom(env, cls, (*env)->FindClass(env, "android/app/Activity")));

  // instance method with int, String, long and double arguments (varargs)
  jmethodID cb = (*env)->GetMethodID(env, cls, "callback", "(ILjava/lang/String;JD)Ljava/lang/String;");
  jstring s = (*env)->NewStringUTF(env, "x");
  jstring r = cb ? (jstring)(*env)->CallObjectMethod(env, self, cb, (jint)7, s, (jlong)0x123456789LL, 0.5) : 0;
  const char *rs = r ? (*env)->GetStringUTFChars(env, r, 0) : "";
  check("CallObjectMethod (int, String, long, double)", r && !strcmp(rs, "x:7:123456789:0.5"));
  if (r) (*env)->ReleaseStringUTFChars(env, r, rs);
  // the same through jvalue[]
  jvalue a[4];
  a[0].i = 8; a[1].l = s; a[2].j = 0x10; a[3].d = 1.5;
  r = cb ? (jstring)(*env)->CallObjectMethodA(env, self, cb, a) : 0;
  rs = r ? (*env)->GetStringUTFChars(env, r, 0) : "";
  check("CallObjectMethodA (jvalue[])", r && !strcmp(rs, "x:8:10:1.5"));

  jmethodID tw = (*env)->GetStaticMethodID(env, cls, "twice", "(I)I");
  check("CallStaticIntMethod", tw && (*env)->CallStaticIntMethod(env, cls, tw, 21) == 42);
  jmethodID half = (*env)->GetMethodID(env, cls, "half", "(F)F");
  check("CallFloatMethod (float promoted through varargs)", half && (*env)->CallFloatMethod(env, self, half, 3.0f) == 1.5f);

  // fields
  jfieldID fc = (*env)->GetFieldID(env, cls, "counter", "I");
  check("GetIntField", fc && (*env)->GetIntField(env, self, fc) == 41);
  if (fc) (*env)->SetIntField(env, self, fc, 42);
  jfieldID fb = (*env)->GetFieldID(env, cls, "big", "J");
  check("GetLongField", fb && (*env)->GetLongField(env, self, fb) == 0x1122334455667788LL);
  jfieldID fr = (*env)->GetFieldID(env, cls, "ratio", "D");
  check("GetDoubleField", fr && (*env)->GetDoubleField(env, self, fr) == 0.25);
  if (fr) (*env)->SetDoubleField(env, self, fr, 0.75);
  check("SetDoubleField", fr && (*env)->GetDoubleField(env, self, fr) == 0.75);
  jfieldID fl = (*env)->GetStaticFieldID(env, cls, "label", "Ljava/lang/String;");
  if (fl) (*env)->SetStaticObjectField(env, cls, fl, (*env)->NewStringUTF(env, "native"));
  check("Get/SetStaticObjectField", fl != 0);

  // strings
  jstring u = (*env)->NewStringUTF(env, "h\xc3\xa9llo");   // "héllo"
  check("GetStringLength (UTF-16 units)", (*env)->GetStringLength(env, u) == 5);
  check("GetStringUTFLength", (*env)->GetStringUTFLength(env, u) == 6);
  const jchar *wc = (*env)->GetStringChars(env, u, 0);
  check("GetStringChars", wc && wc[1] == 0xe9);
  (*env)->ReleaseStringChars(env, u, wc);

  // arrays
  jintArray ia = (*env)->NewIntArray(env, 3);
  jint v[3] = {5, 6, 7};
  (*env)->SetIntArrayRegion(env, ia, 0, 3, v);
  jint *e = (*env)->GetIntArrayElements(env, ia, 0);
  check("int[] region and elements", (*env)->GetArrayLength(env, ia) == 3 && e[2] == 7);
  e[0] = 50;
  (*env)->ReleaseIntArrayElements(env, ia, e, 0);
  jint back[1];
  (*env)->GetIntArrayRegion(env, ia, 0, 1, back);
  check("ReleaseIntArrayElements copies back", back[0] == 50);
  jclass strc = (*env)->FindClass(env, "java/lang/String");
  jobjectArray oa = (*env)->NewObjectArray(env, 2, strc, 0);
  (*env)->SetObjectArrayElement(env, oa, 1, u);
  check("object arrays", oa && (*env)->GetObjectArrayElement(env, oa, 0) == 0 && (*env)->IsSameObject(env, (*env)->GetObjectArrayElement(env, oa, 1), u));

  // JDK objects: NewObject and calls on them
  jclass sbc = (*env)->FindClass(env, "java/lang/StringBuilder");
  jmethodID init = sbc ? (*env)->GetMethodID(env, sbc, "<init>", "(Ljava/lang/String;)V") : 0;
  jmethodID app = sbc ? (*env)->GetMethodID(env, sbc, "append", "(I)Ljava/lang/StringBuilder;") : 0;
  jmethodID ts = sbc ? (*env)->GetMethodID(env, sbc, "toString", "()Ljava/lang/String;") : 0;
  jobject sb = init ? (*env)->NewObject(env, sbc, init, (*env)->NewStringUTF(env, "n=")) : 0;
  if (sb && app) (*env)->CallObjectMethod(env, sb, app, 5);
  jstring t = sb && ts ? (jstring)(*env)->CallObjectMethod(env, sb, ts) : 0;
  const char *tc = t ? (*env)->GetStringUTFChars(env, t, 0) : "";
  check("NewObject + calls on a JDK class", t && !strcmp(tc, "n=5"));

  // exceptions from Java
  jmethodID f = (*env)->GetMethodID(env, cls, "fail", "()V");
  if (f) (*env)->CallVoidMethod(env, self, f);
  jthrowable ex = (*env)->ExceptionOccurred(env);
  check("ExceptionOccurred after a throwing call", ex != 0 && (*env)->ExceptionCheck(env));
  (*env)->ExceptionClear(env);
  check("ExceptionClear", !(*env)->ExceptionCheck(env));
  jmethodID missing = (*env)->GetMethodID(env, cls, "noSuchMethod", "()V");
  check("GetMethodID of a missing method -> NULL + NoSuchMethodError", missing == 0 && (*env)->ExceptionCheck(env));
  (*env)->ExceptionClear(env);

  // references
  jobject g = (*env)->NewGlobalRef(env, self);
  check("global refs", g && (*env)->IsSameObject(env, g, self) && (*env)->GetObjectRefType(env, g) == JNIGlobalRefType);
  (*env)->DeleteGlobalRef(env, g);

  // errno has the numbers bionic's headers give (Linux's), whatever the host's libc uses
  int fd = open("/no/such/dir/file", 0);
  int err = *__errno();
  check("errno in Linux numbering (ENOENT = 2)", fd == -1 && err == 2 && !strcmp(strerror(2), "No such file or directory"));
  return (*env)->NewStringUTF(env, out);
}

JNIEXPORT jdouble JNICALL Java_com_a2w_jnitest_MainActivity_nativeMix(JNIEnv *env, jclass c, jint a, jlong b, jfloat f, jdouble d) {
  return a + (double)b + f + d;
}
JNIEXPORT void JNICALL Java_com_a2w_jnitest_MainActivity_nativeThrow(JNIEnv *env, jclass c) {
  (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/IllegalStateException"), "from native");
}
JNIEXPORT jintArray JNICALL Java_com_a2w_jnitest_MainActivity_nativeSquares(JNIEnv *env, jclass c, jintArray in) {
  jsize n = (*env)->GetArrayLength(env, in);
  jint buf[64];
  if (n > 64) n = 64;
  (*env)->GetIntArrayRegion(env, in, 0, n, buf);
  for (int i = 0; i < n; i++) buf[i] *= buf[i];
  jintArray outa = (*env)->NewIntArray(env, n);
  (*env)->SetIntArrayRegion(env, outa, 0, n, buf);
  return outa;
}
JNIEXPORT jlong JNICALL Java_com_a2w_jnitest_MainActivity_nativeLong(JNIEnv *env, jclass c, jlong a, jlong b) { return a + b; }

static jint registered_add(JNIEnv *env, jobject self, jint a, jint b) { return a + b; }
JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
  JNIEnv *env;
  if ((*vm)->GetEnv(vm, (void **)&env, JNI_VERSION_1_6) != JNI_OK) return -1;
  JNINativeMethod m[] = {{"registeredAdd", "(II)I", (void *)registered_add}};
  jclass cls = (*env)->FindClass(env, "com/a2w/jnitest/MainActivity");
  if (!cls || (*env)->RegisterNatives(env, cls, m, 1) != 0) return -1;
  return JNI_VERSION_1_6;
}

// GL from native code (libGLESv2 imports), used by GLActivity
void glClearColor(float r, float g, float b, float a);
void glClear(unsigned mask);
JNIEXPORT void JNICALL Java_com_a2w_jnitest_GLActivity_nativeClear(JNIEnv *env, jclass c, jfloat r, jfloat g, jfloat b) {
  glClearColor(r, g, b, 1.0f);
  glClear(0x4000);
}
