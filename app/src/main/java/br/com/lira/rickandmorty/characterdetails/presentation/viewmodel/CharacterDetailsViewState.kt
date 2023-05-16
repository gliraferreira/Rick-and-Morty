package br.com.lira.rickandmorty.characterdetails.presentation.viewmodel

import androidx.lifecycle.LiveData
import br.com.lira.rickandmorty.characterdetails.presentation.model.CharacterDetailsUIModel
import br.com.lira.rickandmorty.core.viewmodel.ViewState

interface CharacterDetailsViewState : ViewState {
    val character: LiveData<CharacterDetailsUIModel>
    val state: LiveData<State>

    fun isLoading(): LiveData<Boolean>
    fun shouldDisplayContent(): LiveData<Boolean>

    enum class State {
        LOADING, SUCCESS, ERROR
    }
}