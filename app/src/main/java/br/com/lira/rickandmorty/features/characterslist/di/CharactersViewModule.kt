package br.com.lira.rickandmorty.features.characterslist.di

import br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel.CharactersDefaultViewState
import br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel.CharactersViewState
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