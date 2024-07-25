import br.lira.convention.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.run {
            apply("org.jetbrains.kotlin.jvm")
        }
        configureKotlinJvm()
    }
}