# netrune

_High-performance networking layer designed for RuneScape protocols._

---

Snapshots only, project is not ready for use.

---

Use from Gradle KTS

```kotlin
repositories {
    maven(url = "https://repo.runelite.net/")
    maven(url = "https://repo.openrs2.org/repository/openrs2-snapshots/")
    
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("org.jire", "netrune", "main-SNAPSHOT")
}
```