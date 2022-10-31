import org.gradle.api.initialization.Settings
import org.gradle.api.initialization.resolve.DependencyResolutionManagement

const val DEFAULT_VERSION_CATALOG_NAME = "libs"

@Suppress("UnstableApiUsage")
inline fun DependencyResolutionManagement.versionCatalog(
    name: String = DEFAULT_VERSION_CATALOG_NAME,
    crossinline build: ExtendedVersionCatalogBuilder.() -> Unit
) = versionCatalogs {
    create(name) {
        ExtendedVersionCatalogBuilder(this)
            .build()
    }
}

@Suppress("UnstableApiUsage")
inline fun Settings.versionCatalog(
    name: String = DEFAULT_VERSION_CATALOG_NAME,
    crossinline build: ExtendedVersionCatalogBuilder.() -> Unit
) = dependencyResolutionManagement
    .versionCatalog(name, build)
