# macOS Development Setup

## Current Project Requirements

- Android Studio installed
- Android SDK Platform 36 installed
- Android SDK Build-Tools 36.0.0 installed
- Gradle JVM: Android Studio bundled JBR, or another JDK compatible with the selected Android Gradle Plugin
- Gradle wrapper 9.4.1

This Mac already has:

- Android Studio: `/Applications/Android Studio.app`
- Android SDK: `/Users/maojie.li/Library/Android/sdk`
- SDK platforms: includes `android-36`
- Build tools: includes `36.0.0`
- Android Studio bundled JBR: Java 21

## Shell Environment

Add this to your shell profile if you want command-line builds:

```zsh
export ANDROID_HOME="$HOME/Library/Android/sdk"
export ANDROID_SDK_ROOT="$ANDROID_HOME"
export PATH="$ANDROID_HOME/platform-tools:$ANDROID_HOME/cmdline-tools/latest/bin:$PATH"
```

The current shell does not expose `ANDROID_HOME`, and `sdkmanager` is not on `PATH`.

## Current Gradle Issue

The Homebrew Gradle command currently fails before it reaches the project:

```text
Could not initialize native services.
Failed to load native library 'libnative-platform.dylib' for Mac OS X aarch64.
```

This is a local Gradle installation/cache issue, not an app source issue.

Recommended fix:

1. Open the project in Android Studio.
2. Let Android Studio sync the Gradle project using its configured Gradle JVM.
3. If command-line Gradle is still needed, use the checked-in Gradle wrapper or repair Homebrew Gradle.

After Gradle works, verify with:

```zsh
./gradlew :app:assembleDebug
./gradlew :app:testDebugUnitTest
```
