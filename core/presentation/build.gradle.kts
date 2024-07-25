plugins {
    alias(libs.plugins.rickandmorty.android.library)
    alias(libs.plugins.rickandmorty.android.hilt)
}

android {
    namespace = "br.com.lira.rickandmorty.core.presentation"
}

dependencies {
    implementation(libs.picasso)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.material)
}