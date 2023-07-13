package br.com.lira.rickandmorty.features.characterdetails.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.com.lira.rickandmorty.features.characterdetails.presentation.model.CharacterDetailsUIModel
import br.com.lira.rickandmorty.features.characterdetails.presentation.model.CharacterEpisodeUIModel
import javax.inject.Inject

class CharacterDetailsDefaultViewState @Inject constructor() : CharacterDetailsViewState {

    private val _character = MutableLiveData<CharacterDetailsUIModel>()
    private val _episodes = MutableLiveData<List<CharacterEpisodeUIModel>>()
    private val _state = MutableLiveData<CharacterDetailsViewState.State>()

    override val character: LiveData<CharacterDetailsUIModel> get() = _character
    override val episodes: LiveData<List<CharacterEpisodeUIModel>> get() = _episodes
    override val state: LiveData<CharacterDetailsViewState.State> get() = _state
    override val isLoading = Transformations.map(_state) {
        it == CharacterDetailsViewState.State.LOADING
    }
    override val shouldDisplayContent = Transformations.map(_state) {
        it == CharacterDetailsViewState.State.SUCCESS
    }

    fun postState(newState: CharacterDetailsViewState.State) = _state.postValue(newState)

    fun postCharacter(
        character: CharacterDetailsUIModel
    ) = _character.postValue(character)

    fun postEpisodes(episodes: List<CharacterEpisodeUIModel>) {
        _episodes.postValue(episodes)
    }
}