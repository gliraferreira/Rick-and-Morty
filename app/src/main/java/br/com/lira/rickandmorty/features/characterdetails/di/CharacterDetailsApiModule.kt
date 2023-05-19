package br.com.lira.rickandmorty.features.characterdetails.di

import br.com.lira.rickandmorty.features.characterdetails.data.remote.api.CharacterDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterDetailsApiModule {

    @Singleton
    @Provides
    fun provideCharacterDetailsApi(
        retrofit: Retrofit
    ): CharacterDetailsApi = retrofit.create(CharacterDetailsApi::class.java)
}