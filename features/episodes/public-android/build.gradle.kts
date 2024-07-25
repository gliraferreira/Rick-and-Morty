plugins {
    alias(libs.plugins.rickandmorty.android.library)
}

android {
    namespace = "br.com.lira.rickandmorty.episodes"

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.paging.runtime)
    implementation(projects.core.presentation)
}