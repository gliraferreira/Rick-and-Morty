package br.com.lira.rickandmorty.features.episodes.presentation.navigation

import br.com.lira.rickandmorty.episodes.navigation.EpisodesNavigator
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