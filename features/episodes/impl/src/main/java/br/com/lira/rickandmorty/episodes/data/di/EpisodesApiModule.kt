package br.com.lira.rickandmorty.episodes.data.di

import br.com.lira.rickandmorty.episodes.data.remote.api.EpisodesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EpisodesApiModule {

    @Singleton
    @Provides
    fun provideCharactersApi(
        retrofit: Retrofit
    ): EpisodesApi = retrofit.create(EpisodesApi::class.java)
}