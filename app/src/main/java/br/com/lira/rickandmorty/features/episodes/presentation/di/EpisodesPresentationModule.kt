package br.com.lira.rickandmorty.features.episodes.presentation.di

import br.com.lira.rickandmorty.characters.navigation.CharacterNavigator
import br.com.lira.rickandmorty.episodes.presentation.mapper.EpisodesErrorMapper
import br.com.lira.rickandmorty.features.episodes.presentation.mapper.EpisodesErrorMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface EpisodesPresentationModule {

    @Binds
    @Reusable
    fun bindsEpisodesErrorMapper(
        mapper: EpisodesErrorMapperImpl
    ): EpisodesErrorMapper
}