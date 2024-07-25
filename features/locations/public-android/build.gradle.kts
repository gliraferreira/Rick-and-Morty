plugins {
    alias(libs.plugins.rickandmorty.android.library)
}

android {
    namespace = "br.com.lira.rickandmorty.locations"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.paging.runtime)
}