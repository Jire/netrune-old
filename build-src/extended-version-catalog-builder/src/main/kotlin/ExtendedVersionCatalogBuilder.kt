import org.gradle.api.initialization.dsl.VersionCatalogBuilder

class ExtendedVersionCatalogBuilder(
    ref: VersionCatalogBuilder
) : VersionCatalogBuilder by ref {

    operator fun String.invoke(
        group: String, version: String,
        artifact: String = this
    ) = libraryVersioned(group, artifact, version, this)

}
