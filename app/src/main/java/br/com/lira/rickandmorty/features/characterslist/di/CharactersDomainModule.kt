package br.com.lira.rickandmorty.features.characterslist.di

import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharacters
import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharactersUseCase
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
    fun bindsGetAllCharactersUseCase(
        usecase: GetAllCharacters
    ): GetAllCharactersUseCase
}
