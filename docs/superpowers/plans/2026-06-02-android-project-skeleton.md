# Android Project Skeleton Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Create a buildable Android app skeleton for the local music player and verify it on macOS.

**Architecture:** Start with a conventional single-module Android app. Keep UI, domain, data, and playback packages separated from the first commit so later MediaStore, Room, and Media3 work lands without moving files.

**Tech Stack:** Kotlin, Jetpack Compose, Material 3, Android Gradle Plugin 9.1.0, Kotlin 2.2.10, Gradle 9.4.1, `compileSdk 36`, `targetSdk 36`, `minSdk 33`.

---

## Files

- Create: `settings.gradle.kts`
- Create: `build.gradle.kts`
- Create: `gradle/libs.versions.toml`
- Create: `gradle.properties`
- Create: `app/build.gradle.kts`
- Create: `app/src/main/AndroidManifest.xml`
- Create: `app/src/main/java/li/maojie/localmusic/MainActivity.kt`
- Create: `app/src/main/java/li/maojie/localmusic/ui/LocalMusicApp.kt`
- Create: `app/src/main/java/li/maojie/localmusic/ui/theme/Theme.kt`
- Create: `app/src/main/res/values/strings.xml`
- Create: `app/src/test/java/li/maojie/localmusic/domain/playback/QueueManagerTest.kt`
- Create: `app/src/main/java/li/maojie/localmusic/domain/playback/QueueModels.kt`
- Create: `app/src/main/java/li/maojie/localmusic/domain/playback/QueueManager.kt`
- Modify: `README.md`

## Task 1: Gradle Android Skeleton

- [ ] Create version catalog and root Gradle files with fixed plugin versions.
- [ ] Create the `app` module with Compose enabled and SDK values set to 36/36/33.
- [ ] Create a minimal manifest and string resources.
- [ ] Document required macOS environment: Android Studio, SDK 36, JDK from Android Studio, `ANDROID_HOME`.
- [ ] Verify with `./gradlew :app:assembleDebug` after Gradle 9.4.1 or a matching wrapper is available.

## Task 2: First Compose UI Shell

- [ ] Create `MainActivity` using `ComponentActivity` and `setContent`.
- [ ] Create `LocalMusicApp` with a Warm Native library home shell.
- [ ] Create app theme with Material 3 light/dark support.
- [ ] Verify compilation with `./gradlew :app:assembleDebug`.

## Task 3: QueueManager TDD Seed

- [ ] Write failing tests for queue creation from an album/list source.
- [ ] Run `./gradlew :app:testDebugUnitTest --tests '*QueueManagerTest'` and confirm failure.
- [ ] Implement minimal queue models and `QueueManager`.
- [ ] Re-run the same test and confirm pass.
- [ ] Commit the skeleton and queue seed.
