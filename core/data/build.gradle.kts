plugins {
    alias(libs.plugins.rickandmorty.android.library)
    alias(libs.plugins.rickandmorty.android.hilt)
    alias(libs.plugins.rickandmorty.kotlin.retrofit)
}

android {
    namespace = "br.com.lira.rickandmorty.core.data"

}

dependencies {
    implementation(libs.flipper)
    implementation(libs.flipper.network.plugin)
    implementation(libs.soloader)
}