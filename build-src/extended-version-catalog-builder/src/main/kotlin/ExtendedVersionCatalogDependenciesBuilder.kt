import org.gradle.api.initialization.dsl.VersionCatalogBuilder

class ExtendedVersionCatalogDependenciesBuilder(
    ref: VersionCatalogBuilder
) : VersionCatalogBuilder by ref {

    operator fun String.invoke(
        group: String, version: String,
        artifact: String = this
    ) = libraryVersioned(group, artifact, version, this)

    inline fun group(
        group: String,

        versionAlias: String = group,
        version: String,

        build: GroupVersionCatalogBuilder.() -> Unit
    ): GroupVersionCatalogBuilder {
        version(versionAlias, version) // first we need to establish version alias

        return GroupVersionCatalogBuilder(this, group, versionAlias, version)
            .apply(build)
    }

}
