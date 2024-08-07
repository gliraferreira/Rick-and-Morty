pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "RickAndMortyCharactersGuide"

include(":app")
include(":core:presentation")
include(":core:data")
include(":core:navigation")
include(":features:locations:impl")
include(":features:locations:public-android")
include(":features:characters:public-android")
include(":features:episodes:public-android")
include(":features:episodes:impl")
include(":features:characters:impl")
