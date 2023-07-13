package br.com.lira.rickandmorty.features.characterdetails.presentation.viewmodel

import androidx.lifecycle.LiveData
import br.com.lira.rickandmorty.features.characterdetails.presentation.model.CharacterDetailsUIModel
import br.com.lira.rickandmorty.features.characterdetails.presentation.model.CharacterEpisodeUIModel
import br.com.lira.rickandmorty.core.viewmodel.ViewState

interface CharacterDetailsViewState : ViewState {
    val character: LiveData<CharacterDetailsUIModel>
    val episodes: LiveData<List<CharacterEpisodeUIModel>>
    val state: LiveData<State>
    val isLoading: LiveData<Boolean>
    val shouldDisplayContent: LiveData<Boolean>

    enum class State {
        LOADING, SUCCESS, ERROR
    }
}