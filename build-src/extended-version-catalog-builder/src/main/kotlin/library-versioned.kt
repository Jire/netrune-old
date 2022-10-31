import org.gradle.api.initialization.dsl.VersionCatalogBuilder

fun VersionCatalogBuilder.libraryVersioned(
    group: String, artifact: String,
    version: String,
    alias: String = artifact
) = library(alias, group, artifact)
    .version(version)

fun VersionCatalogBuilder.libraryVersionedRef(
    group: String, artifact: String,
    versionRef: String,
    alias: String = artifact
) = library(alias, group, artifact)
    .versionRef(versionRef)
