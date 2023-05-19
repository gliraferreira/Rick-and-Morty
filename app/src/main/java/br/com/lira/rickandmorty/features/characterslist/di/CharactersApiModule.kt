package br.com.lira.rickandmorty.features.characterslist.di

import br.com.lira.rickandmorty.features.characterslist.data.remote.api.CharactersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CharactersApiModule {

    @Singleton
    @Provides
    fun provideCharactersApi(
        retrofit: Retrofit
    ): CharactersApi = retrofit.create(CharactersApi::class.java)
}