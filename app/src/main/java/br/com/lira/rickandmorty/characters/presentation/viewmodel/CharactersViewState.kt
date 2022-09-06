package br.com.lira.rickandmorty.characters.presentation.viewmodel

import androidx.lifecycle.LiveData
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.core.viewmodel.ViewState

interface CharactersViewState : ViewState {

    val characters: LiveData<List<Character>>
    val state: LiveData<State>

    fun isLoading(): LiveData<Boolean>
    fun shouldDisplayContent(): LiveData<Boolean>

    enum class State {
        LOADING, SUCCESS, ERROR
    }
}