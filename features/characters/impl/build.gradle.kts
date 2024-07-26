plugins {
    alias(libs.plugins.rickandmorty.android.library)
    alias(libs.plugins.rickandmorty.android.hilt)
    alias(libs.plugins.rickandmorty.kotlin.retrofit)
    alias(libs.plugins.jetbrains.kotlin.android.parcelize)
}

android {
    namespace = "br.com.lira.rickandmorty.characters.impl"
}

dependencies {
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.circleimageview)

    implementation(projects.core.data)
    implementation(projects.core.presentation)
    implementation(projects.core.navigation)
    implementation(projects.features.locations.publicAndroid)
    implementation(projects.features.characters.publicAndroid)
    implementation(projects.features.episodes.publicAndroid)
}