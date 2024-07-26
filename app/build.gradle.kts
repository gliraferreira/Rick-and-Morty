plugins {
    alias(libs.plugins.rickandmorty.android.application)
    alias(libs.plugins.rickandmorty.android.hilt)
}

android {
    namespace = "br.com.lira.rickandmorty"

    defaultConfig {

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.flipper)
    implementation(libs.flipper.network.plugin)
    implementation(libs.soloader)

    implementation(projects.core.presentation)
    implementation(projects.core.data)
    implementation(projects.core.navigation)
    implementation(projects.features.locations.impl)
    implementation(projects.features.locations.publicAndroid)
    implementation(projects.features.characters.publicAndroid)
    implementation(projects.features.characters.impl)
    implementation(projects.features.episodes.impl)
    implementation(projects.features.episodes.publicAndroid)
}