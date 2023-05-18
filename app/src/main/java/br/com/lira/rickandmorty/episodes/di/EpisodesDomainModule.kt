package br.com.lira.rickandmorty.episodes.di

import br.com.lira.rickandmorty.characters.domain.usecase.GetAllCharacters
import br.com.lira.rickandmorty.characters.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.episodes.domain.usecase.GetMultipleEpisodes
import br.com.lira.rickandmorty.episodes.domain.usecase.GetMultipleEpisodesUseCase
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
}