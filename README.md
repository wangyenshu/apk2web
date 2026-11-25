# apk2web

Converts an Android app into a static web site:

* Java and Kotlin bytecode is compiled to WebAssembly (TeaVM 0.16, WebAssembly GC).
* 32-bit ARM native libraries run in an ARM interpreter compiled to WebAssembly.
* A Java reimplementation of the Android framework draws into an HTML canvas.

The output needs only static file hosting.

## Requirements

* JDK 17+, Maven, Python 3.
* Optional: an `android.jar` (`--android-jar`, or the newest under `$ANDROID_HOME/platforms`). It supplies the shapes
  of classes the framework does not implement.
* Emscripten, only to rebuild `native/` (`native/prebuilt/` holds a built emulator).

apktool, APKEditor, bundletool and binaryen are downloaded on first use (pinned versions, checksums verified).

## Usage

```
python3 apk2web.py INPUT... OUT [options]
python3 -m http.server -d OUT          # serve over HTTP; file:// URLs do not work
```

| Option | Effect |
| --- | --- |
| `--obb FILE` | add an APK expansion file |
| `--screen WxH@DPI` | device screen (default 800x480@240, rotated to the launcher activity's orientation) |
| `--orientation land\|port` | orientation (default: the launcher activity's, else portrait) |
| `--sdk N` | API level the app sees (default `max(19, minSdkVersion)`; resources are resolved for it) |
| `--abi ABI` | native ABI (default `armeabi-v7a`, else `armeabi`); `--no-native` skips native code |
| `--android-jar PATH` | android.jar for class shapes |
| `--drop PKG` / `--keep PKG` | add to / remove from the SDK packages replaced by no-op stubs (ads, analytics, crash reporting) |
| `--title TEXT` | page title (default: the app label) |
| `--opt LEVEL` | TeaVM optimisation level (default `SIMPLE`) |
| `--max-rounds N` | link/compile rounds for members TeaVM cannot compile (default 6) |
| `--work DIR`, `--cache DIR` | build directory (default `OUT.build`); download and build cache (default `.cache` beside `apk2web.py`) |
| `--maven-args ARGS` | extra Maven options |

Page URL parameters: `?log` (debug log), `?lang=xx` (locale), `?synclog` (synchronous log), `?gltrace` (GL calls).
`tools/symbolize.py OUT [LOG]` resolves `app.wasm:N` stack frames to Java methods. App data (files, preferences,
databases, external storage) is stored in IndexedDB per package.

| Input | Handling |
| --- | --- |
| `.apk` | used as is |
| several `.apk`, or a directory | base and split APKs, merged with APKEditor |
| `.apks` | bundletool set: the universal APK, else the splits for the emulated device, merged |
| `.xapk` | APKPure set: APKs merged, `Android/obb/` expansion files kept |
| `.apkm` | APKMirror set: APKs merged (encrypted files are refused) |
| `.zip` of APKs | merged (SAI and similar) |
| `.aab` | universal APK built with bundletool |
| `.obb` | expansion file, placed at `Android/obb/<package>/main.<version>.<package>.obb` |

Output in `OUT/`: `index.html`, `hw.js` (browser side), `gles.js` (OpenGL ES), `app.wasm`, `res.json` (resolved
resources), `app.apk` (assets, drawables and native libraries are read from it), `emu.js`/`emu.wasm` (ARM
emulator), `config.json`, `symbols.txt`. `OUT.build/link-report.txt` lists every stubbed, renamed and redirected
member.

GitHub Actions: `.github/workflows/convert.yml` converts `app.apk` (or the `APK_URL` variable) and publishes it to
Pages; `.github/workflows/test.yml` builds and converts the test app.

## Compatibility

| Methods | API 19 | API 35 |
| --- | ---: | ---: |
| implemented (`+`) | 21,427 | 31,185 |
| derived data-class accessors (`*`) | 985 | 6,465 |
| fixed answer (`~`) | 1,180 | 1,568 |
| missing (`-`) | 6,965 | 17,768 |
| total | 30,557 | 56,986 |

API 19 is the level an app with `minSdkVersion` up to 19 sees. Across levels 1 to 36 (removed APIs included), 18,316
of 59,281 methods are missing; 14,175 of these are in the 4,101 classes declared from the SDK. `+` means a real
implementation, not identical behaviour (see [Limits](#limits)). [docs/API.md](docs/API.md) lists every class, method
and field with its levels and status, and explains how members that differ between levels are linked.
[docs/INTERNALS.md](docs/INTERNALS.md) describes the converter and the runtime.

Implemented areas:

* App model: activities, fragments, dialogs, options and context menus, the action bar, `Toolbar`, action modes;
  started and bound services, broadcast receivers, the app's content providers, `JobScheduler`, saved instance state
  and `Activity.recreate`.
* UI: the platform widget set, custom views, popups, `WebView` (as an iframe), view, property and layout animations.
  Touch, mouse and keyboard input with touch mode and focus navigation as on a device; text fields are edited
  through a page text field.
* Resources: configuration qualifiers, platform and AppCompat themes, drawables (vector, shape, layer, state, ripple,
  nine-patch, adaptive icons), color state lists, fonts, assets.
* Graphics: `Canvas` on the 2D canvas API, bitmaps, styled text and text layout; OpenGL ES 1.1 and 2.0 (Java and
  native) and 3.0 (Java) on WebGL 2; `SurfaceView` drawn from a thread.
* Media, storage, network: `MediaPlayer`, `SoundPool`, `AudioTrack`; preferences, files, SQLite (sql.js);
  `java.net.URL` and the Apache HTTP client over XMLHttpRequest.
* Java: threads with blocking monitors and `java.util.concurrent`, reflection from per-app tables, XML, JSON,
  `java.time`, Unicode data.
* SDK classes the framework does not implement are declared from `android.jar`: apps that subclass them or read their
  constants link and run, data classes keep their values, and hardware the page lacks answers as on a device without
  it ([tools/genapi-absent.txt](tools/genapi-absent.txt)).
* Native code: ARMv7-A, Thumb-2, VFPv4, NEON and ARMv5TE; all `JNIEnv` functions, pthreads, signals, `dlopen`,
  GLES and EGL, a host libc. Tested engines: Unity 3 (Mono), cocos2d-x, libGDX, SDL, custom C++ engines.

## Limits

* 32-bit ARM only: native code only for `arm64-v8a` or `x86` gets `UnsatisfiedLinkError`. Native code is
  interpreted (tens of millions of ARM instructions per second).
* Not provided: sensors (one accelerometer that never reports), location, camera, Bluetooth, vibration, OpenSL ES,
  `NativeActivity`, Vulkan, GLES 3 for native code (beyond vertex array objects), GLES 3.1 features WebGL 2 lacks
  (compute shaders, storage buffers, indirect draws), ELF TLS, packed relocations, `java.net` sockets, processes
  (`Runtime.exec`), other apps' providers, accessibility services, autofill, drag and drop between windows, an
  on-screen keyboard for custom views.
* Networking is limited to what the browser allows: same-origin or CORS-enabled servers.
* Background work (services, jobs, alarms) runs only while the page is open; scheduled jobs are not kept across
  reloads.
* Perspective transforms (`Camera`, `View.setRotationX/Y`) are drawn as the nearest affine map;
  `drawBitmapMesh` ignores per-vertex colors; `drawVertices` fills each triangle with one color.
* `android.icu` beyond `UCharacter` (no ICU data is bundled); `java.util.Locale` has no scripts or extensions;
  `Collator` uses one root ordering for every locale.
* Reflection reaches only members the linker sees named (strings, class literals, layouts, manifest, annotations).
* Keyboard navigation clusters need Meta+Tab, which browsers usually do not deliver.
* Large AndroidX apps take minutes to convert (about five for 8,749 classes) and up to 70% of RAM for TeaVM.

## Test corpus

24 APKs (F-Droid, Wayback Machine releases, commercial games), converted and run headless for about 30 seconds:

| App | Kind | Reaches |
| --- | --- | --- |
| Subway Surfers 1.0.4 | Unity 3.5 (Mono), armeabi-v7a | title screen |
| Angry Birds Lite 1.3.5 | C++ engine, armeabi-v7a | main menu |
| Cut the Rope Experiments 1.7.3 | C++ engine, armeabi | main menu |
| Baby Panda Care | cocos2d-x with Lua | home screen |
| Frozen Bubble 1.16 | `SurfaceView` from a thread, native MOD player | game |
| Bouncy (Vector Pinball) | canvas, GL | table menu |
| Klooni | libGDX | main menu |
| TuxRider | native GL thread | main menu |
| Glxy | libGDX with native GL bindings | start screen |
| Dodge | canvas | welcome menu over the playfield |
| Käsekästchen | Java UI | game setup screen |
| HashDroid | `TabActivity`, Holo | main screen (action bar, tabs, overflow menu) |
| Andor's Trail 0.6.9, ConnectBot (2010), OI ConvertCSV, Countdown, FileManager, Flashlight | Java UI | welcome and licence dialogs |
| Meritous | SDL | data-download dialog |
| Solitaire CG | canvas | menu hint; the card table stays blank |
| apipepanic | HTML5 game in a `WebView` | game board |
| gobandroid HD | AppCompat, Material Components | game setup screen |
| Privacy Friendly 2048 | AppCompat, Material Components | tutorial, main menu |
| kidsmem | libGDX, FreeType | background only; the menu is not drawn |

## Tests

* `tests/jnitest/build.sh ANDROID_JAR BUILD_TOOLS_DIR [org.apache.http.legacy.jar]` builds
  `tests/jnitest/jnitest.apk`. Converted and opened, it runs 96 checks: 75 of JNI, the Java library and the platform
  (resources, themes, text, graphics, widgets, input, services, saved state), 8 of GL, 13 of threads and timing. The
  log ends with `JNITEST: SUMMARY` and `THREADS DONE`. `tests/jnitest/bundle.sh ANDROID_JAR BUILD_TOOLS_DIR
  BUNDLETOOL_JAR` packages it as `.aab`, `.apks`, `.xapk` (with an expansion file: one more check), `.apkm` and `.obb`.
* `tests/armv7/check.sh` runs 7,737 ARM and Thumb-2 instruction tests in the emulator and in qemu-arm and compares
  the results.
* `tools/apicheck.sh ANDROID_JAR` lists framework methods whose return types differ from android.jar's.
* `tools/apireport.sh ANDROID_JAR [api-versions.xml]` regenerates `docs/API.md` and `docs/api/`. Without the second
  argument it uses `data/api-versions.xml` beside android.jar, else a pinned, checksummed download.

## Repository

```
apk2web.py              driver
tools/build_res.py      resources and manifest -> res.json
tools/jarproc/          linker (jp.Link), per-app tables (jp.Gen), API report (jp.ApiReport), SDK declarations
                        (jp.GenApi), TeaVM fixes (jp.TeaVMPatch), Unicode and locale tables
tools/genapi.sh         runtime/framework-gen from the SDK (all API levels) and tools/genapi-absent.txt
tools/aosp.py           ports AOSP sources (pinned commit) into the framework
tools/ucd.py            a2w.UcdData, a2w.CaseFolding from ICU's Unicode data
tools/unicode.sh        a2w.UnicodeData (blocks, scripts, bidi classes, emoji) from the JDK's tables
tools/localedata.sh     a2w.LocaleData (ISO language and country codes) from the JDK's tables
tools/gen_internal_r.py com.android.internal.R (public attribute ids) from android.jar
tools/osconstants.py    android.system.OsConstants (ARM bionic values)
tools/d2jfix/           dex2jar fix (comparisons with zero on the left)
tools/gen_gl.py         GL bindings from android.jar
tools/gen_android_r.sh  android.R from android.jar
tools/gen_fwres.py      runtime/res/fwids.txt (public framework resource ids)
tools/symbolize.py      resolves app.wasm frames in logs
runtime/framework/      Android framework and JDK additions (Java, written or ported from AOSP)
runtime/framework-gen/  remaining SDK classes, declared from android.jar (generated; stub bodies)
runtime/res/            framework resources (themes, colors, layouts, strings)
runtime/site/           page: index.html, hw.js, gles.js, TeaVM runtime, fonts
native/                 ARM emulator (C, Emscripten), prebuilt/
tests/jnitest/          conformance test app
tests/armv7/            instruction tests
docs/                   API coverage, internals
```
