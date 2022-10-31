import org.gradle.api.initialization.dsl.VersionCatalogBuilder

inline fun VersionCatalogBuilder.buildGroup(
    group: String, versionRef: String,

    build: BuildGroup.() -> Unit
) = BuildGroup(this, group, versionRef)
    .build()
