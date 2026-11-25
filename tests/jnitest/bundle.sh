#!/bin/bash
# Packages the JNI test app the ways apps are distributed besides one APK, to test apk2web's input formats:
#   build/jnitest.aab    Android App Bundle (bundletool build-bundle)
#   build/jnitest.apks   split APK set (bundletool build-apks: base, ABI, density and language splits; standalones)
#   build/jnitest21.apks the same for minSdk 21 (splits only)
#   build/jnitest.xapk   APKPure's format: the splits, manifest.json and an expansion file in Android/obb/
#   build/jnitest.apkm   APKMirror's format: base.apk, split_config.*.apk and info.json
#   build/main.1.com.a2w.jnitest.obb   an APK expansion file
#   tests/jnitest/bundle.sh ANDROID_JAR BUILD_TOOLS_DIR BUNDLETOOL_JAR   (after build.sh)
set -euo pipefail
cd "$(dirname "$0")"
AJ=${1:?android.jar}; BT=${2:?build-tools directory}; BUNDLETOOL=${3:?bundletool jar}
B=build
[ -f "$B/apk/classes.dex" ] || { echo "run build.sh first" >&2; exit 1; }
rm -rf "$B/bundle" "$B"/jnitest.{aab,apks,xapk,apkm} "$B"/jnitest21.{aab,apks} "$B/set" && mkdir -p "$B/bundle/base" "$B/set"
# the base module: proto-format manifest and resources, dex, native code for two ABIs, assets
"$BT/aapt2" link --proto-format -I "$AJ" --manifest AndroidManifest.xml -A assets -o "$B/proto.apk" "$B/res.zip"
(cd "$B/bundle/base" && unzip -q ../../proto.apk && mkdir manifest dex && mv AndroidManifest.xml manifest/ && cp ../../apk/classes.dex dex/)
for abi in armeabi-v7a arm64-v8a; do mkdir -p "$B/bundle/base/lib/$abi" && cp "$B/apk/lib/armeabi/libjnitest.so" "$B/bundle/base/lib/$abi/"; done
(cd "$B/bundle/base" && zip -q -r ../base.zip .)
java -jar "$BUNDLETOOL" build-bundle --modules="$B/bundle/base.zip" --output="$B/jnitest.aab"
[ -f "$B/test.keystore" ] || keytool -genkeypair -keystore "$B/test.keystore" -storepass android -keypass android -alias test \
  -keyalg RSA -keysize 2048 -validity 10000 -dname CN=test 2>/dev/null
java -jar "$BUNDLETOOL" build-apks --bundle="$B/jnitest.aab" --output="$B/jnitest.apks" --ks="$B/test.keystore" \
  --ks-pass=pass:android --ks-key-alias=test --key-pass=pass:android
# the same for Android 5+ only (minSdk 21): a split set without standalone APKs
sed 's/android:minSdkVersion="14"/android:minSdkVersion="21"/' AndroidManifest.xml > "$B/AndroidManifest21.xml"
"$BT/aapt2" link --proto-format -I "$AJ" --manifest "$B/AndroidManifest21.xml" -A assets -o "$B/proto21.apk" "$B/res.zip"
rm -rf "$B/bundle/base21" && cp -r "$B/bundle/base" "$B/bundle/base21" && rm -rf "$B/bundle/base21/res" "$B/bundle/base21/resources.pb" "$B/bundle/base21/manifest/AndroidManifest.xml"
(cd "$B/bundle/base21" && unzip -q -o ../../proto21.apk && mv AndroidManifest.xml manifest/ && rm -f ../base21.zip && zip -q -r ../base21.zip .)
java -jar "$BUNDLETOOL" build-bundle --modules="$B/bundle/base21.zip" --output="$B/jnitest21.aab" --overwrite
java -jar "$BUNDLETOOL" build-apks --bundle="$B/jnitest21.aab" --output="$B/jnitest21.apks" --overwrite --ks="$B/test.keystore" \
  --ks-pass=pass:android --ks-key-alias=test --key-pass=pass:android
printf 'expansion data\n' > "$B/main.1.com.a2w.jnitest.obb"
# XAPK and APKM hold what one device gets: here an ARMv7 phone at 320 dpi, in English and French
printf '{"supportedAbis":["armeabi-v7a"],"supportedLocales":["en","fr"],"screenDensity":320,"sdkVersion":30}\n' > "$B/set/device.json"
java -jar "$BUNDLETOOL" extract-apks --apks="$B/jnitest.apks" --device-spec="$B/set/device.json" --output-dir="$B/set/splits"
mkdir -p "$B/set/x/Android/obb/com.a2w.jnitest" "$B/set/m"
for f in "$B"/set/splits/*.apk; do
  n=$(basename "$f" .apk); c=${n#base-}; c=${c%_[0-9]}
  if [ "$c" = master ]; then cp "$f" "$B/set/x/com.a2w.jnitest.apk"; cp "$f" "$B/set/m/base.apk"
  else cp "$f" "$B/set/x/config.$c.apk"; cp "$f" "$B/set/m/split_config.$c.apk"; fi
done
cp "$B/main.1.com.a2w.jnitest.obb" "$B/set/x/Android/obb/com.a2w.jnitest/"
printf '{"xapk_version":2,"package_name":"com.a2w.jnitest","name":"apk2web JNI test","version_code":"1","version_name":"1.0","min_sdk_version":"14","split_apks":[{"file":"com.a2w.jnitest.apk","id":"base"}],"expansions":[{"file":"Android/obb/com.a2w.jnitest/main.1.com.a2w.jnitest.obb","install_location":"EXTERNAL_STORAGE","install_path":"Android/obb/com.a2w.jnitest/main.1.com.a2w.jnitest.obb"}]}\n' > "$B/set/x/manifest.json"
printf '{"apkm_version":5,"app_name":"apk2web JNI test","pname":"com.a2w.jnitest","versioncode":"1","release_version":"1.0"}\n' > "$B/set/m/info.json"
(cd "$B/set/x" && zip -q -r ../../jnitest.xapk .) && (cd "$B/set/m" && zip -q -r ../../jnitest.apkm .)
ls -la "$B"/jnitest.* "$B"/*.obb
