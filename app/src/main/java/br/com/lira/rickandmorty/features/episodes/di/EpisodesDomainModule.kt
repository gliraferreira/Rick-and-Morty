package br.com.lira.rickandmorty.features.episodes.di

import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharacters
import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.features.episodes.domain.usecase.GetAllEpisodes
import br.com.lira.rickandmorty.features.episodes.domain.usecase.GetAllEpisodesUseCase
import br.com.lira.rickandmorty.features.episodes.domain.usecase.GetMultipleEpisodes
import br.com.lira.rickandmorty.features.episodes.domain.usecase.GetMultipleEpisodesUseCase
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface EpisodesDomainModule {

    @Binds
    @Reusable
    fun bindsGetMultipleEpisodesUseCase(
        usecase: GetMultipleEpisodes
    ): GetMultipleEpisodesUseCase

    @Binds
    @Reusable
    fun bindsGetAllEpisodesUseCase(
        usecase: GetAllEpisodes
    ): GetAllEpisodesUseCase
}
