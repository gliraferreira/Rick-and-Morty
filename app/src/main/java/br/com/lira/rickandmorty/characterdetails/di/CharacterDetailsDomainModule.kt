package br.com.lira.rickandmorty.characterdetails.di

import br.com.lira.rickandmorty.characterdetails.domain.usecase.GetCharacterById
import br.com.lira.rickandmorty.characterdetails.domain.usecase.GetCharacterByIdUseCase
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharacterDetailsDomainModule {

    @Binds
    @Reusable
    fun bindsGetCharacterByIdUseCase(
        usecase: GetCharacterById
    ): GetCharacterByIdUseCase
}