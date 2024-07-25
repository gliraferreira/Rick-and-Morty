
import br.lira.convention.ExtensionType
import br.lira.convention.configureBuildTypes
import br.lira.convention.configureKotlinAndroid
import br.lira.convention.libs
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin


class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureBuildTypes(this, ExtensionType.LIBRARY)

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())
                "testImplementation"(kotlin("test"))
            }
        }
    }
}