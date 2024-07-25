package br.com.lira.rickandmorty.features.characters.domain.di

import br.com.lira.rickandmorty.features.characters.domain.usecase.GetAllCharacters
import br.com.lira.rickandmorty.characters.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.features.characters.domain.usecase.GetCharacterById
import br.com.lira.rickandmorty.characters.domain.usecase.GetCharacterByIdUseCase
import br.com.lira.rickandmorty.features.characters.domain.usecase.GetMultipleCharacters
import br.com.lira.rickandmorty.characters.domain.usecase.GetMultipleCharactersUseCase
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharactersDomainModule {

    @Binds
    @Reusable
    fun bindsGetAllCharacters(
        useCase: GetAllCharacters
    ): GetAllCharactersUseCase

    @Binds
    @Reusable
    fun bindsGetCharacterById(
        useCase: GetCharacterById
    ): GetCharacterByIdUseCase

    @Binds
    @Reusable
    fun bindsGetMultipleCharacters(
        useCase: GetMultipleCharacters
    ): GetMultipleCharactersUseCase
}