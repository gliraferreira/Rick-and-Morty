package br.com.lira.rickandmorty.characters.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.com.lira.rickandmorty.characters.presentation.model.CharacterUIModel
import javax.inject.Inject

class CharactersDefaultViewState @Inject constructor() : CharactersViewState {

    private val _characters = MutableLiveData<List<CharacterUIModel>>()
    private val _state = MutableLiveData<CharactersViewState.State>()

    override val characters: LiveData<List<CharacterUIModel>> get() = _characters
    override val state: LiveData<CharactersViewState.State> get() = _state

    override fun isLoading() = Transformations.map(_state) {
        it == CharactersViewState.State.LOADING
    }

    override fun shouldDisplayContent() = Transformations.map(_state) {
        it == CharactersViewState.State.SUCCESS
    }

    fun postCharacters(
        charactersList: List<CharacterUIModel>
    ) = _characters.postValue(charactersList)

    fun postState(newState: CharactersViewState.State) = _state.postValue(newState)
}