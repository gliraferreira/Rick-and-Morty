package br.com.lira.rickandmorty.features.characterdetails.di

import br.com.lira.rickandmorty.features.characterdetails.domain.usecase.GetCharacterById
import br.com.lira.rickandmorty.features.characterdetails.domain.usecase.GetCharacterByIdUseCase
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