plugins {
    alias(libs.plugins.rickandmorty.android.application)
    alias(libs.plugins.jetbrains.kotlin.android.parcelize)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
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
    compileOnly(libs.ksp.gradle.plugin)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.fragment.ktx)


    implementation(libs.material)

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.picasso)
    implementation(libs.circleimageview)

    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.flipper)
    implementation(libs.flipper.network.plugin)
    implementation(libs.soloader)
}