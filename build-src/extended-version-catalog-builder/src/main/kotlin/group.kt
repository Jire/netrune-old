import org.gradle.api.initialization.dsl.VersionCatalogBuilder

inline fun VersionCatalogBuilder.group(
    group: String,

    versionAlias: String = group,
    version: String,

    build: GroupVersionCatalogBuilder.() -> Unit
): GroupVersionCatalogBuilder {
    version(versionAlias, version) // first we need to establish version alias

    return GroupVersionCatalogBuilder(this, group, versionAlias, version)
        .apply(build)
}
