package br.com.lira.rickandmorty.features.locations.di

import br.com.lira.rickandmorty.features.locations.data.remote.api.LocationsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object LocationsApiModule {

    @Singleton
    @Provides
    fun provideLocationsApi(
        retrofit: Retrofit
    ): LocationsApi = retrofit.create(LocationsApi::class.java)
}
