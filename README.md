# netrune

_High-performance networking layer designed for RuneScape protocols._

---

Snapshots only, project is not ready for use.

---

### Using in Gradle

You need to add these repositories:

```kotlin
repositories {
    maven(url = "https://repo.runelite.net/")
    maven(url = "https://repo.openrs2.org/repository/openrs2-snapshots/")

    maven(url = "https://jitpack.io")
}
```

Then add the dependency (this will add all modules):

```kotlin
dependencies {
    implementation("org.jire", "netrune", "main-SNAPSHOT")
}
```

To add a specific module, for example `buffer`:

```kotlin
dependencies {
    implementation("org.jire.netrune", "buffer", "main-SNAPSHOT")
}
```