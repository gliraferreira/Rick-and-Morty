package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.core.viewmodel.ViewState
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterError

interface CharactersViewState : ViewState {

    val characters: LiveData<PagingData<CharacterUIModel>?>
    val action: LiveData<CharactersViewAction>
    val filter: LiveData<CharacterFilter>
    val error: LiveData<CharacterError>
    val isLoading: LiveData<Boolean>
    val isError: LiveData<Boolean>
    val shouldDisplayContent: LiveData<Boolean>
    val isSearchEnabled: LiveData<Boolean>
}
