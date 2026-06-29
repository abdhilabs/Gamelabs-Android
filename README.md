# MyApp
Short description of the project.
## Tech Stack
- **Language:** Kotlin 2.0.21
- **Build:** Android Gradle Plugin 8.5.2 / Gradle 8.7
- **Min SDK:** 28 (Android 9 Pie)
- **Target SDK:** 34 (Android 14)
- **Compile SDK:** 34
## Installation
1. Install **JDK 17** (required by AGP 8.x).
2. Install **Android Studio Iguana (2023.2.1)** or newer.
3. Clone this repository.
4. Open the project in Android Studio. The Gradle wrapper will download Gradle 8.7 on first sync.
5. Connect a device or emulator running **Android 9.0+** (API level 28 or higher).
6. Run the `app` configuration.
### Updating the SDK
All SDK levels and library versions are declared in
[`build-systems/dependencies.gradle`](build-systems/dependencies.gradle).
Modules inherit these values via `apply from: ...` and MUST NOT override
`minSdk` locally.
## Building from the command line
```bash
./gradlew assembleDebug      # build a debug APK
./gradlew assembleRelease    # build a release APK (R8/ProGuard enabled)
./gradlew test               # run unit tests
```
## License
[MIT](LICENSE)