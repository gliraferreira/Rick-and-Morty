package br.com.lira.rickandmorty.episodes.data.di

import br.com.lira.rickandmorty.episodes.data.remote.datasource.EpisodesRemoteDataSource
import br.com.lira.rickandmorty.episodes.data.remote.datasource.EpisodesServiceDataSource
import br.com.lira.rickandmorty.episodes.data.repository.EpisodesDefaultRepository
import br.com.lira.rickandmorty.episodes.domain.repository.EpisodesRepository
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