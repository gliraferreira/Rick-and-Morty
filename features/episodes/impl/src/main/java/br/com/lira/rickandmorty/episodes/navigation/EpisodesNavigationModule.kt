package br.com.lira.rickandmorty.episodes.navigation

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface EpisodesNavigationModule {

    @Binds
    @Reusable
    fun bindsEpisodesNavigation(
        navigator: EpisodesNavigatorImpl
    ): EpisodesNavigator
}