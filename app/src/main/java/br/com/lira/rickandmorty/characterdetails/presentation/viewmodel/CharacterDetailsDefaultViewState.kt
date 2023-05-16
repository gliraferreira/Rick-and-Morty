package br.com.lira.rickandmorty.characterdetails.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.com.lira.rickandmorty.characterdetails.presentation.model.CharacterDetailsUIModel
import javax.inject.Inject

class CharacterDetailsDefaultViewState @Inject constructor() : CharacterDetailsViewState {

    private val _character = MutableLiveData<CharacterDetailsUIModel>()
    private val _state = MutableLiveData<CharacterDetailsViewState.State>()

    override val character: LiveData<CharacterDetailsUIModel> get() = _character
    override val state: LiveData<CharacterDetailsViewState.State> get() = _state

    override fun isLoading() = Transformations.map(_state) {
        it == CharacterDetailsViewState.State.LOADING
    }

    override fun shouldDisplayContent() = Transformations.map(_state) {
        it == CharacterDetailsViewState.State.SUCCESS
    }

    fun postState(newState: CharacterDetailsViewState.State) = _state.postValue(newState)

    fun postCharacter(
        character: CharacterDetailsUIModel
    ) = _character.postValue(character)
}