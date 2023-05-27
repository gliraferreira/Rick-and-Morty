package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.core.viewmodel.ViewState

interface CharactersViewState : ViewState {

    val characters: LiveData<PagingData<CharacterUIModel>?>
    val state: LiveData<State>
    val action: LiveData<CharactersViewAction>
    val name: LiveData<String?>

    fun isLoading(): LiveData<Boolean>

    fun shouldDisplayContent(): LiveData<Boolean>
    fun isToolbarVisible(): LiveData<Boolean>

    fun isSearchEnabled(): LiveData<Boolean>

    fun isSearchClearTextVisible(): LiveData<Boolean>

    enum class State {
        LOADING, SUCCESS, ERROR
    }
}