import org.gradle.api.initialization.dsl.VersionCatalogBuilder

class GroupVersionCatalogBuilder(
    val builder: VersionCatalogBuilder,

    val group: String,

    val versionAlias: String,
    val version: String
) {

    fun artifact(artifactName: String, alias: String = artifactName) =
        builder.libraryVersionedRef(group, artifactName, versionAlias, alias)

    operator fun String.invoke() = artifact(this)

}
