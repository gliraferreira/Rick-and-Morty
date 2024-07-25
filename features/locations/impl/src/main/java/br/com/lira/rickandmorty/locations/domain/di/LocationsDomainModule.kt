package br.com.lira.rickandmorty.locations.domain.di

import br.com.lira.rickandmorty.locations.domain.usecase.GetAllLocations
import br.com.lira.rickandmorty.locations.domain.usecase.GetAllLocationsUseCase
import br.com.lira.rickandmorty.locations.domain.usecase.GetLocationById
import br.com.lira.rickandmorty.locations.domain.usecase.GetLocationByIdUseCase
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocationsDomainModule {

    @Binds
    @Reusable
    fun bindsGetAllLocationsUseCase(
        useCase: GetAllLocations
    ): GetAllLocationsUseCase

    @Binds
    @Reusable
    fun bindsGetLocationById(
        useCase: GetLocationById
    ): GetLocationByIdUseCase
}