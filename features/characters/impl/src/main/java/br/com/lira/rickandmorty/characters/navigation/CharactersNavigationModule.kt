package br.com.lira.rickandmorty.characters.navigation

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharactersNavigationModule {

    @Binds
    @Reusable
    fun bindsCharacterNavigator(
        navigator: CharacterNavigatorImpl
    ): CharacterNavigator
}