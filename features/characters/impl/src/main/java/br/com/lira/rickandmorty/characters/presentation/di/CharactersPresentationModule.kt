package br.com.lira.rickandmorty.characters.presentation.di

import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterShortToUIMapper
import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterShortToUIMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharactersPresentationModule {

    @Binds
    @Reusable
    fun bindsCharacterShortUiMapper(
        mapper: CharacterShortToUIMapperImpl
    ): CharacterShortToUIMapper
}