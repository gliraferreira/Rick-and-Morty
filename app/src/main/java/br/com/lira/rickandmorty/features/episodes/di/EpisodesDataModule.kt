package br.com.lira.rickandmorty.features.episodes.di

import br.com.lira.rickandmorty.features.characterslist.data.remote.datasource.CharactersRemoteDataSource
import br.com.lira.rickandmorty.features.characterslist.data.remote.datasource.CharactersServiceDataSource
import br.com.lira.rickandmorty.features.characterslist.data.repository.CharactersDefaultRepository
import br.com.lira.rickandmorty.features.characterslist.domain.repository.CharactersRepository
import br.com.lira.rickandmorty.features.episodes.data.remote.datasource.EpisodesRemoteDataSource
import br.com.lira.rickandmorty.features.episodes.data.remote.datasource.EpisodesServiceDataSource
import br.com.lira.rickandmorty.features.episodes.data.repository.EpisodesDefaultRepository
import br.com.lira.rickandmorty.features.episodes.domain.repository.EpisodesRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface EpisodesDataModule {

    @Binds
    @Reusable
    fun bindsEpisodesRemoteDataSource(
        serviceDataSource: EpisodesServiceDataSource
    ): EpisodesRemoteDataSource

    @Binds
    @Reusable
    fun bindsRepository(
        repository: EpisodesDefaultRepository
    ): EpisodesRepository
}