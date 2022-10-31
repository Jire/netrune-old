import org.gradle.api.Plugin
import org.gradle.api.Project

class NetruneProjectPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.netrune()
    }

}
