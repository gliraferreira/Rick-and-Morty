plugins {
    alias(libs.plugins.rickandmorty.android.library)
    alias(libs.plugins.rickandmorty.android.hilt)
    alias(libs.plugins.rickandmorty.kotlin.retrofit)
}

android {
    namespace = "br.com.lira.rickandmorty.locations.impl"
}

dependencies {

    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.runtime)
    implementation(projects.core.data)
    implementation(projects.core.presentation)
    implementation(projects.features.locations.publicAndroid)
}