import org.gradle.api.initialization.dsl.VersionCatalogBuilder

fun VersionCatalogBuilder.pluginVersioned(
    alias: String,
    id: String,
    version: String
) = plugin(alias, id).version(version)

fun VersionCatalogBuilder.pluginVersionedRef(
    alias: String,
    id: String,
    versionRef: String
) = plugin(alias, id).versionRef(versionRef)
