import org.gradle.api.initialization.dsl.VersionCatalogBuilder

class ExtendedVersionCatalogPluginsBuilder(
    ref: VersionCatalogBuilder
) : VersionCatalogBuilder by ref {

    operator fun String.invoke(
        version: String,
        alias: String = this
    ) = pluginVersioned(alias, this, version)

}
