package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.LiveData
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter

interface CharacterFilterViewState {

    val filter: LiveData<CharacterFilter>
    val action: LiveData<CharacterFilterViewAction>
}
