plugins {
    `kotlin-dsl`
}

group = "br.com.lira.rickandmorty.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "rickandmorty.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "rickandmorty.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "rickandmorty.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("kotlinRetrofit") {
            id = "rickandmorty.kotlin.retrofit"
            implementationClass = "KotlinRetrofitConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "rickandmorty.kotlin.library"
            implementationClass = "AndroidRetrofitConventionPlugin"
        }
    }
}