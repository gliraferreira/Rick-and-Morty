package br.com.lira.rickandmorty.characters.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagingData
import br.com.lira.rickandmorty.characters.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.core.toolkit.OneShotLiveData
import javax.inject.Inject

class CharactersDefaultViewState @Inject constructor() : CharactersViewState {

    private val _characters = MutableLiveData<PagingData<CharacterUIModel>>()
    private val _state = MutableLiveData<CharactersViewState.State>()
    private val _action = OneShotLiveData<CharactersViewAction>()

    override val characters: LiveData<PagingData<CharacterUIModel>> get() = _characters
    override val state: LiveData<CharactersViewState.State> get() = _state
    override val action: LiveData<CharactersViewAction> get() = _action

    override fun isLoading() = Transformations.map(_state) {
        it == CharactersViewState.State.LOADING
    }

    override fun shouldDisplayContent() = Transformations.map(_state) {
        it == CharactersViewState.State.SUCCESS
    }

    fun postCharacters(
        charactersList: PagingData<CharacterUIModel>
    ) = _characters.postValue(charactersList)

    fun postState(newState: CharactersViewState.State) = _state.postValue(newState)

    fun sendAction(action: CharactersViewAction) {
        _action.value = action
    }
}