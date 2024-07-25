plugins {
    alias(libs.plugins.rickandmorty.android.library)
    alias(libs.plugins.jetbrains.kotlin.android.parcelize)
}

android {
    namespace = "br.com.lira.rickandmorty.characters"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.circleimageview)

    implementation(projects.core.presentation)
}