import org.gradle.api.initialization.dsl.VersionCatalogBuilder

class BuildGroup(
    val builder: VersionCatalogBuilder,

    val group: String,
    val versionRef: String
) {

    fun artifact(artifactName: String, alias: String = artifactName) =
        builder.libraryVersionedRef(group, artifactName, versionRef, alias)

    operator fun String.invoke() = artifact(this)

}
