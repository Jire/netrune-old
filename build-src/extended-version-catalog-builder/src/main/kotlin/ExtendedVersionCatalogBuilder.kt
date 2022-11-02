import org.gradle.api.initialization.dsl.VersionCatalogBuilder

class ExtendedVersionCatalogBuilder(
    ref: VersionCatalogBuilder
) : VersionCatalogBuilder by ref {

    inline fun dependencies(declare: ExtendedVersionCatalogDependenciesBuilder.() -> Unit) {
        ExtendedVersionCatalogDependenciesBuilder(this).declare()
    }

    inline fun plugins(declare: ExtendedVersionCatalogPluginsBuilder.() -> Unit) {
        ExtendedVersionCatalogPluginsBuilder(this).declare()
    }

}
