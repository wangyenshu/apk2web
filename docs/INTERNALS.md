# Internals

## Pipeline

| Step | Tool | Output |
| --- | --- | --- |
| 1 | apktool | decoded manifest and resources; the `.dex` files |
| 2 | `tools/build_res.py` | `res.json`: resources resolved for the target device with Android's qualifier precedence (locale, `sw`/`w`/`h`, size, orientation, density, `-vN`, ...), app and framework (`runtime/res`) resources under one id space; the names the app instantiates (manifest components, layout tags, class names in XML, `android:onClick`) |
| 3 | dex2jar (patched, `tools/d2jfix`) | JVM bytecode per `classesN.dex` |
| 4 | javac | the framework, `runtime/framework` and `runtime/framework-gen` (cached by content hash) |
| 5 | `tools/jarproc` (`jp.Link`) | one closed class set for TeaVM, `link-report.txt` |
| 6 | TeaVM 0.16 (`teavm-core` patched: `0.16.0-a2w2`) | `app.wasm` (WebAssembly GC). JDK members TeaVM cannot compile are fed back to step 5 (up to `--max-rounds`); a module that fails validation is rewritten by binaryen's `wasm-opt` |
| 7 | `apk2web.py` | the site (see the README) |

## Linker (`tools/jarproc`)

Inputs: the app's classes, the framework, TeaVM's class library (with its `@Rename`/`@Remove` mappings), bundled
libraries (org.json, kXML; Apache HTTP client 4.0 when the app uses `org.apache.http`), android.jar and the
android-4.1.1.4 jar (for shapes and constant values). It resolves every class, method and field the code references:

* Framework and platform classes win over copies inside the app; among the `--app` jars the first copy of a class
  wins. The Apache HTTP client is passed before the app's jars when Android would load its own copy first (device
  level 27 or lower, or `uses-library org.apache.http.legacy`), after them otherwise. Ads, analytics and
  crash-reporting SDKs (default list in `Link.DEFAULT_DROP`, `--drop`/`--keep`) are removed and regenerated as stubs.
* Members are resolved by name and full descriptor, return type included, as in dex. Methods whose SDK signature
  returns a narrower type than the inherited one (`clone()`, `ConcurrentSkipListMap.headMap`) are declared with the
  SDK's return type in the framework; `javac` adds the bridge for the inherited descriptor.
* An Android or JDK class the framework does not write is taken from `runtime/framework-gen` (below), linked only
  when the app reaches it; a class the app carries replaces that declaration. A class no jar defines is generated
  from how the app uses it (interface or class, throwable or not, supertypes inferred from data flow).
* A missing member becomes a stub that logs `W/apk2web: not implemented: ...` once and returns a default (`this` for
  builder-style methods). A missing constructor delegates to the closest existing one. A missing field of a framework
  class is added, with its constant value from the template jar.
* JDK classes TeaVM lacks are renamed to `a2w.jdk.*` (declared in `runtime/framework-gen` or written in
  `runtime/framework`). `org.threeten.bp` (TeaVM's `java.time`) is linked as `java.time`. JDK members TeaVM lacks are redirected to
  `a2w.jdk.Compat.<Class>_<method>` when it has them, else to stubs. Hand-written replacements under
  `runtime/framework/a2w/jdk/` (path without `java/`) replace TeaVM's class of the same name (for example
  `java.util.concurrent`, `MessageDigest`, reflection); `javax.xml.parsers`, `javax.xml.transform`, `org.xml.sax`,
  `org.w3c.dom`, `javax.xml.validation` and `javax.crypto` are renamed to `a2w.xml.*` and `a2w.jdk.crypto.*` (javac
  reserves those packages). Types in framework signatures must be the renamed ones the app's calls are rewritten to
  (`a2w.jdk.FileInputStream` for `java.io.FileInputStream`); `docs/API.md` lists any mismatch. Framework code is not
  renamed, so it names those classes directly (`a2w.jdk.text.Normalizer`, `a2w.jdk.util.UUID`).
* Native methods become bridges to the emulator (`a2w.Jni`); `natives.json` lists them.
* Some JDK calls are redirected for the page (`Link.RETARGETED`: `System.loadLibrary`, `System.exit`, `Class.forName`
  ...), and constructors TeaVM gets wrong are made as a fixing subclass (`Link.FIXED_CTORS`: `new RandomAccessFile(f,
  "rw")` creates a missing file through `a2w.jdk.ctor.RandomAccessFile`).
* App methods the verifier rejects become stubs that throw `LinkageError`; methods TeaVM turns into invalid
  WebAssembly are stubbed in another round.
* Every backward jump in app code calls `a2w.Preempt.tick()` (thread time slices).

Generated per app (`jp.Gen`), split over companion classes to stay within class-file limits:

* `a2w.gen.Reflect`: `Class.forName` for every class the app can name (strings, manifest, layouts, class literals;
  every class of its packages when it builds names at run time); constructors of the shapes Android instantiates, and
  all constructors of classes the app looks up reflectively, platform classes included; methods named in strings and
  `onClick`; fields when the app uses `getField`/`getDeclaredField` (int constants packed, unpacked on first lookup);
  generic signatures for `getGenericSuperclass`/`getGenericType`; runtime annotations (an implementation class per
  annotation type) when the app reads annotations; array classes made reflectively.
* `a2w.gen.JniTable`: the classes, methods and fields native libraries name in their strings (and Unity assembly
  strings, script bridges such as cocos2d-x `luaj`), with dispatch code.

`link-report.txt` lists generated classes, stubbed members, redirections, renames, and the reflection and JNI tables.

## Declarations from the SDK (`runtime/framework-gen`)

`tools/genapi.sh` writes a Java declaration of every SDK class the framework does not write (`jp.GenApi`): the class
with its modifiers, generics, supertypes, constants, fields, constructors, methods and nested classes, at every API
level (members of older `android.jar`s and of `api-versions.xml` included), checked against the framework and TeaVM's
class library so it compiles with them. Bodies:

* abstract and interface methods stay abstract; empty callbacks (`onX`) of listener and callback classes are empty;
* data classes (`@a2w.Derived`): fields for the properties their getters name; constructors, setters and builders
  that store them; `equals`, `hashCode`, `toString`, `describeContents`, `writeToParcel`, `CREATOR` and `clone` over
  them; constructors whose parameters name public fields assign those fields;
* classes of a feature the page does not have (`tools/genapi-absent.txt`, `@a2w.Absent`): what a device without it
  answers;
* everything else is a stub (`@a2w.NotImplemented`) that logs once and returns a default.

A class written in `runtime/framework` replaces its declaration; `tools/genapi.sh` is rerun after framework changes.
Declarations the runtime must construct (system-service managers) are kept in `runtime/framework` with their
annotations; the declared constructor `X(a2w.Hidden)` is public for that. The API report counts `@a2w.Derived` as
`*`, stubs as `-`.

`jp.TeaVMPatch` fixes two TeaVM 0.16 wasm-gc bugs in a copy of `teavm-core` installed as `0.16.0-a2w2`, which the
build uses: `WasmGCVirtualTableBuilder$Table.merge` could leave `Object` without a virtual table when an interface is
implemented through two paths (`PrivateKey` extends `Destroyable` and `Key`); and the type test of an interface no
class implements was a constant false, so an empty `I[]` from `Array.newInstance(I)` failed a cast to `I[]`
(`getSpans(..., LineHeightSpan.class)`). Known issue: in framework code, a cast of `HashMap` values to `double[][]`
fails, while the same code in app code passes (jnitest "array casts"); the framework stores such values in small
classes.

## Framework (`runtime/framework`)

Java implementation of the Android API over browser services in `runtime/site/hw.js`. Main parts:

* `a2w.ActivityThread`: activity stack and lifecycle, intents (resolved against the manifest), services, broadcasts,
  content providers. `a2w.Windows`/`a2w.ViewRoot`: window stacking, layout, drawing and input per frame
  (`requestAnimationFrame`); `Choreographer` callbacks run at the start of each frame.
* Input: `hw.js` turns pointer, wheel and key events into calls on `a2w.Main` (pointer ids, tool type, buttons,
  pressure and coalesced samples; key codes by `KeyboardEvent.code` with the meta state). `a2w.Windows` routes them
  as `MotionEvent`s (to the window under the first pointer; hover and wheel to the window under the mouse) and
  `KeyEvent`s (to the focused window). `ViewRoot.processKey` follows `ViewRootImpl`'s input stages: leave touch mode
  (consuming a navigation key that moved focus), the view tree, shortcuts for modified keys, then focus navigation
  (`FocusFinder`). Touch mode is one global flag; a pointer down or wheel enters it in every window. A new window's
  first layout gives it initial focus out of touch mode, or always for apps targeting API < 28 (the platform's rule).
  `Instrumentation.sendKeySync`/`sendPointerSync` go through the same path. While a page text field is open, Tab
  and (single-line) up/down are forwarded as keys so focus can leave it.
* Widget looks: framework drawables the platform ships as images are drawn in code (`a2w.Drawables`,
  `a2w.WidgetDrawables`: buttons, check boxes, radio buttons, stars, the toggle bar, switch thumbs and tracks, list
  selectors). `a2w.Styles` holds the widgets' default styles (`Widget.CompoundButton.CheckBox`...).
* Bars: the action bar is `a2w.ActionBarImpl`. Action modes (`a2w.ActionModeImpl`) are a bar at the top of the decor
  view, or a small window for `TYPE_FLOATING`. `Toolbar` follows the platform's layout (content insets, title margins,
  collapsing margins, custom views by gravity); its `ActionMenuView` shows the action items and an overflow popup and
  is rebuilt when the menu changes (`MenuBuilder.addWatcher`). `Activity.setActionBar` wraps a toolbar in
  `a2w.ToolbarActionBar` (title, options menu, navigation button as `android.R.id.home`).
* `GridLayout` sizes each column (row) from its single-span children, widens a span's last flexible column when the
  span needs more, and gives excess space by weight, else to the last flexible column. This approximates the
  platform's constraint solver; grids with spanning cells can be sized differently.
* Saved state: `View.onSaveInstanceState`/`onRestoreInstanceState` work as on Android (ids, `BaseSavedState`, the
  "did not call super" checks); `TextView` keeps its selection and, when frozen (always for `EditText`), its text;
  `CompoundButton` its checked state. `Activity.recreate` saves the state, destroys the instance and makes a new one
  of the class in its place with the state and `onRetainNonConfigurationInstance` (without saved state when it has
  platform fragments, whose state is not kept).
* System services: `a2w.SystemServices` returns an object for every service a device has, and `null` for services of
  absent features (fingerprint, Wi-Fi P2P/Aware/RTT, lights, IMS), as on a device without them. Managers the
  framework does not implement are SDK declarations kept in `runtime/framework` (see below); their stubs log.
  `a2w.JobSchedulerImpl` runs jobs while the page is open (latency, deadlines, periodic intervals, back-off, work
  queues, a 10-minute timeout); network constraints follow `navigator.onLine`, idle and content triggers never hold.
  `SearchManager` reads an activity's `android.app.searchable` meta-data and shows the search dialog (a `SearchView`).
* Resources: `a2w.Res` reads `res.json`. Themes: `Resources.Theme` stacks style items; `?attr` references resolve
  against the context theme in layouts, styles, drawables and color lists; `obtainStyledAttributes` takes values from
  the element, its `style`, the default style (`defStyleAttr` in the theme, else `defStyleRes`), then the theme. A
  context without a theme gets the platform default for the app's target API level. Platform themes, colors and
  layouts are in `runtime/res` (public ids from android.jar in `fwids.txt`, private ones in `fwids-private.txt`).
* Drawing: `Canvas` over the 2D canvas API; offscreen bitmaps are canvases. `Canvas` tracks the clip's device
  bounds per save level (for `getClipBounds` and `quickReject`); the page's clip can only shrink, so `UNION`, `XOR`
  and `REVERSE_DIFFERENCE` clips are not applied. A perspective matrix (`Camera`, `View.setRotationX/Y`) is drawn as
  the affine map that agrees with it at the center and two edge midpoints of the view's bounds (or of the clip).
  Shaders become canvas gradients and patterns (`hw.js shaderStyle`): local matrices map a linear gradient exactly
  and a radial one by the matrix's scale; `REPEAT` and `MIRROR` repeat the color stops 64 times each way; `CLAMP`
  bitmap patterns leave the outside empty; `ComposeShader` renders both shaders at the canvas's size for each draw.
  `Region` keeps SkRegion's banded rectangles; `Path.op` cuts both paths, flattened, into scan beams. Bitmaps keep
  8-bit RGBA whatever the config; `copyPixelsToBuffer`/`FromBuffer` convert to the config's memory layout and
  `compress` uses the browser's encoder. `BitmapFactory` reads a compiled nine-patch's `npTc`, `npLb` and `npOl`
  chunks (scaled with the bitmap); `NinePatch` stretches like Skia's lattice. `Picture` records into a bitmap.
  `Movie` and `AnimatedImageDrawable` decode GIFs in Java. OpenGL ES: `gles.js` over WebGL 2, 1.1
  emulated with generated shaders; `GLES30` and the WebGL 2 parts of `GLES31`/`GLES32` for Java (native code gets
  1.1 and 2.0); `EGL14` over the `EGL10` implementation. Java bindings generated by `tools/gen_gl.py`.
* Text layout: `StaticLayout` breaks lines at spaces, CJK characters and hyphens, measures spans
  (`MetricAffectingSpan`, `ReplacementSpan`, `LineHeightSpan`, `LeadingMarginSpan`), ellipsizes and applies line
  spacing; `DynamicLayout` rebuilds it when its text changes; `BoringLayout` handles one-line text. `TextView` draws
  through them.
* Threads: Java threads are TeaVM coroutines on the page's thread; monitors, `wait`/`notify`, `sleep` and
  `java.util.concurrent` block and wake as on a device. A thread that runs 40 ms without blocking yields
  (`a2w.Preempt`, inserted in app code only, so framework code runs without interruption; atomics and
  `compareAndSetState` rely on that). `jp.teavm.FiberFix` (a TeaVM plugin) fixes `Object.wait` in large programs.
  `AbstractQueuedSynchronizer` queues waiters on one monitor and backs `ReentrantLock`, `ReentrantReadWriteLock` and
  `Semaphore` as in the JDK; `ForkJoinPool` serves one queue with up to `parallelism` workers (a worker joining a task
  runs it or other queued tasks); `CompletableFuture` runs async stages on the common pool, or a thread per task when
  its parallelism is 1 (the JDK's rule).
* Files: `java.io.File` is TeaVM's, over `a2w.Vfs`, shared with the emulator and persisted in IndexedDB.
  `android.system.Os` keeps a descriptor table (offsets, flags, `dup`, in-memory pipes) over `RandomAccessFile`.
  SQLite is sql.js.
* Text and Unicode: `a2w.UnicodeData` (blocks, scripts, bidi classes, emoji properties; generated from the JDK by
  `tools/unicode.sh`) and `a2w.UcdData`/`a2w.CaseFolding` (East Asian width, mirroring, brackets, combining classes,
  decompositions, numeric values, case folding; from ICU's `ppucd.txt` by `tools/ucd.py`) back
  `Character.UnicodeBlock`/`UnicodeScript`, `java.text.Normalizer`, `java.text.Collator` (a simplified Unicode
  Collation Algorithm: one ordering for all locales), `android.text.AndroidCharacter` and ICU's `UCharacter`.
  `a2w.LocaleData` (ISO 639/3166 codes, from the JDK by `tools/localedata.sh`) backs `Locale.getISO3Language` and
  related methods.
* Ported AOSP classes (`tools/aosp.py`, frameworks/base at a pinned commit, annotations removed) carry a header naming
  their source; `com.android.internal.util.Preconditions`, `ArrayUtils` and `libcore.io.IoUtils` are provided for them.
* Network: `java.net.URL` (http, https) and Apache HTTP client requests go through XMLHttpRequest; requests the
  browser refuses (no network, DNS, CORS) throw `IOException`.
* WebView: an iframe. Asset pages are served next to the site (`android_asset/`) and get `addJavascriptInterface`
  objects; scripts sent while a page loads run after it loads; pages of another origin get scripts by `postMessage`.

## Native code (`native/`)

An ARMv7-A interpreter in C, built with Emscripten: ARM, Thumb-2, ARMv6 media instructions, VFPv4, NEON, ARMv5TE.
`tests/armv7` compares it with qemu-arm. Libraries load on demand (`System.loadLibrary`, `dlopen`, `DT_NEEDED`) and
link against host implementations of libc, libm, libdl, liblog, libz, GLES 1/2 and EGL; `dlopen` handles have
bionic's `soinfo` layout. A missing host function logs once and fails with `ENOSYS`. The host provides preemptive
threads, pthread synchronisation, signals, `mmap`, the ARM kernel helpers and `/proc` files, with Linux error numbers.
JNI implements every `JNIEnv` function; direct buffers are mirrored and synchronised at native-call boundaries.
