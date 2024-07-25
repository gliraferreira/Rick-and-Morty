plugins {
    alias(libs.plugins.rickandmorty.android.library)
}

android {
    namespace = "br.com.lira.rickandmorty.navigation"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}