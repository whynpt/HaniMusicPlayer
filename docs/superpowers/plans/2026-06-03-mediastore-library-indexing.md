# MediaStore Library Indexing Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add the first local music indexing slice: a testable song model, MediaStore row mapping, filtering rules, and an Android scanner implementation.

**Architecture:** Keep Android framework APIs at the data boundary. The mapping/filtering logic stays pure Kotlin so it can be tested without a device, while `MediaStoreAudioScanner` is the only class that touches `ContentResolver`, `Cursor`, and `MediaStore`.

**Tech Stack:** Kotlin, Android MediaStore, `READ_MEDIA_AUDIO`, JUnit 4.

---

## Files

- Create: `app/src/main/java/li/maojie/localmusic/domain/model/Song.kt`
- Create: `app/src/main/java/li/maojie/localmusic/data/media/MediaStoreAudioRow.kt`
- Create: `app/src/main/java/li/maojie/localmusic/data/media/MediaStoreAudioMapper.kt`
- Create: `app/src/main/java/li/maojie/localmusic/data/media/MediaStoreAudioScanner.kt`
- Create: `app/src/test/java/li/maojie/localmusic/data/media/MediaStoreAudioMapperTest.kt`

## Task 1: Testable MediaStore Mapping

- [ ] Write tests proving valid music rows map to `Song`.
- [ ] Write tests proving non-music, short, and empty-title rows are filtered out.
- [ ] Implement `Song`, `MediaStoreAudioRow`, and `MediaStoreAudioMapper`.
- [ ] Verify with `QueueManagerTest` plus `MediaStoreAudioMapperTest`.

## Task 2: Android MediaStore Scanner Boundary

- [ ] Implement `MediaStoreAudioScanner` using `ContentResolver.query`.
- [ ] Use `MediaStore.Audio.Media.EXTERNAL_CONTENT_URI`.
- [ ] Project only the columns needed by `MediaStoreAudioRow`.
- [ ] Sort by `DATE_MODIFIED DESC`.
- [ ] Map cursor rows through `MediaStoreAudioMapper`.

## Task 3: Commit

- [ ] Ask Android Studio to build if command-line Gradle hangs.
- [ ] Commit after build/test evidence is available.
