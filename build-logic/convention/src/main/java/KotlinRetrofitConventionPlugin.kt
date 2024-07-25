
import br.lira.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class KotlinRetrofitConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                "implementation"(libs.findLibrary("retrofit").get())
                "implementation"(libs.findLibrary("okhttp").get())
                "implementation"(libs.findLibrary("converter.moshi").get())
                "implementation"(libs.findLibrary("moshi.kotlin").get())
                "ksp"(libs.findLibrary("moshi.kotlin.codegen").get())
            }
        }
    }
}