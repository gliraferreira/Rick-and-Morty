package br.com.lira.rickandmorty.characters.di

import br.com.lira.rickandmorty.characters.presentation.viewmodel.CharactersDefaultViewState
import br.com.lira.rickandmorty.characters.presentation.viewmodel.CharactersViewState
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharactersViewModule {

    @Binds
    @Reusable
    fun bindsCharactersViewState(
        viewState: CharactersDefaultViewState
    ): CharactersViewState
}