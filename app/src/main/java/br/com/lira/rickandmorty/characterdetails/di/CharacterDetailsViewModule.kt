package br.com.lira.rickandmorty.characterdetails.di

import br.com.lira.rickandmorty.characterdetails.presentation.viewmodel.CharacterDetailsDefaultViewState
import br.com.lira.rickandmorty.characterdetails.presentation.viewmodel.CharacterDetailsViewState
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharacterDetailsViewModule {

    @Binds
    @Reusable
    fun bindsCharacterDetailsViewState(
        viewState: CharacterDetailsDefaultViewState
    ): CharacterDetailsViewState
}