package br.com.lira.rickandmorty.features.locations.di

import br.com.lira.rickandmorty.features.episodes.data.remote.datasource.EpisodesRemoteDataSource
import br.com.lira.rickandmorty.features.episodes.data.remote.datasource.EpisodesServiceDataSource
import br.com.lira.rickandmorty.features.episodes.data.repository.EpisodesDefaultRepository
import br.com.lira.rickandmorty.features.episodes.domain.repository.EpisodesRepository
import br.com.lira.rickandmorty.features.locations.data.remote.datasource.LocationsRemoteDataSource
import br.com.lira.rickandmorty.features.locations.data.remote.datasource.LocationsServiceDataSource
import br.com.lira.rickandmorty.features.locations.data.repository.LocationsDefaultRepository
import br.com.lira.rickandmorty.features.locations.domain.repository.LocationsRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocationsDataModule {

    @Binds
    @Reusable
    fun bindsLocationsRemoteDataSource(
        serviceDataSource: LocationsServiceDataSource
    ): LocationsRemoteDataSource

    @Binds
    @Reusable
    fun bindsRepository(
        repository: LocationsDefaultRepository
    ): LocationsRepository
}