package br.com.lira.rickandmorty.locations.navigation

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocationsNavigationModule {

    @Binds
    @Reusable
    fun bindsLocationsNavigator(
        navigator: LocationNavigatorImpl
    ): LocationNavigator
}