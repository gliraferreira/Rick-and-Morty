package br.com.lira.rickandmorty.characters.di

import br.com.lira.rickandmorty.characters.data.remote.datasource.CharactersRemoteDataSource
import br.com.lira.rickandmorty.characters.data.remote.datasource.CharactersServiceDataSource
import br.com.lira.rickandmorty.characters.data.repository.CharactersDefaultRepository
import br.com.lira.rickandmorty.characters.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharactersDataModule {

    @Binds
    @Reusable
    fun bindsCharactersRemoteDataSource(
        serviceDataSource: CharactersServiceDataSource
    ): CharactersRemoteDataSource

    @Binds
    @Reusable
    fun bindsRepository(
        repository: CharactersDefaultRepository
    ): CharactersRepository
}