package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterError

interface CharactersViewState : ViewState {

    val characters: LiveData<PagingData<CharacterUIModel>?>
    val state: LiveData<State>
    val action: LiveData<CharactersViewAction>
    val filter: LiveData<CharacterFilter>
    val error: LiveData<CharacterError>

    fun isLoading(): LiveData<Boolean>

    fun isEmpty(): LiveData<Boolean>

    fun isError(): LiveData<Boolean>

    fun shouldDisplayContent(): LiveData<Boolean>

    fun isToolbarVisible(): LiveData<Boolean>

    fun isSearchEnabled(): LiveData<Boolean>

    fun isSearchClearTextVisible(): LiveData<Boolean>

    enum class State {
        LOADING, SUCCESS, ERROR, EMPTY
    }
}