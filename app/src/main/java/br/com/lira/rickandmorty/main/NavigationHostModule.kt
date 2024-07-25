package br.com.lira.rickandmorty.main

import br.com.lira.rickandmorty.characters.navigation.CharacterNavigator
import br.com.lira.rickandmorty.episodes.presentation.mapper.EpisodesErrorMapper
import br.com.lira.rickandmorty.features.episodes.presentation.mapper.EpisodesErrorMapperImpl
import br.com.lira.rickandmorty.navigation.NavigationHostProvider
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationHostModule {

    @Binds
    @Reusable
    fun bindsNavigationHostProvider(
        provider: NavigationHostProviderImpl
    ): NavigationHostProvider
}