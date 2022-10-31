import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class NetruneSettingsPlugin : Plugin<Settings> {

    override fun apply(target: Settings) {
        target.netrune()
    }

}
