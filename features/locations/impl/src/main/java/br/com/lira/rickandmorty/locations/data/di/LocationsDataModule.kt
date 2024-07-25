package br.com.lira.rickandmorty.locations.data.di

import br.com.lira.rickandmorty.locations.data.remote.datasource.LocationsRemoteDataSource
import br.com.lira.rickandmorty.locations.data.remote.datasource.LocationsServiceDataSource
import br.com.lira.rickandmorty.locations.data.repository.LocationsDefaultRepository
import br.com.lira.rickandmorty.locations.domain.repository.LocationsRepository
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