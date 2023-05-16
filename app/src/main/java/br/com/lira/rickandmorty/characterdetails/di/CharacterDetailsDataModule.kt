package br.com.lira.rickandmorty.characterdetails.di

import br.com.lira.rickandmorty.characterdetails.data.remote.datasource.CharacterDetailsRemoteDataSource
import br.com.lira.rickandmorty.characterdetails.data.remote.datasource.CharacterDetailsServiceDataSource
import br.com.lira.rickandmorty.characterdetails.data.repository.CharacterDetailsDefaultRepository
import br.com.lira.rickandmorty.characterdetails.domain.repository.CharacterDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharacterDetailsDataModule {

    @Binds
    @Reusable
    fun bindsCharacterDetailsRemoteDataSource(
        serviceDataSource: CharacterDetailsServiceDataSource
    ): CharacterDetailsRemoteDataSource

    @Binds
    @Reusable
    fun bindsRepository(
        repository: CharacterDetailsDefaultRepository
    ): CharacterDetailsRepository
}