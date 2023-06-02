package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagingData
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.core.toolkit.OneShotLiveData
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterError
import javax.inject.Inject

class CharactersDefaultViewState @Inject constructor() : CharactersViewState {

    private val name = MutableLiveData<String?>().apply { value = null }

    private val _characters = MutableLiveData<PagingData<CharacterUIModel>?>()
    private val _state = MutableLiveData<CharactersViewState.State>()
    private val _action = OneShotLiveData<CharactersViewAction>()
    private val _isSearchEnabled = MutableLiveData<Boolean>().apply { value = false }
    private val _filter = MutableLiveData<CharacterFilter>().apply { value = CharacterFilter() }
    private val _error = MutableLiveData<CharacterError>().apply { value = null }

    override val characters: LiveData<PagingData<CharacterUIModel>?> get() = _characters
    override val state: LiveData<CharactersViewState.State> get() = _state
    override val action: LiveData<CharactersViewAction> get() = _action
    override val filter: LiveData<CharacterFilter> get() = _filter
    override val error: LiveData<CharacterError> get() = _error

    override val isLoading: LiveData<Boolean>
        get() = Transformations.map(_state) { it == CharactersViewState.State.LOADING }

    override val isError
        get() = Transformations.map(_state) { it == CharactersViewState.State.ERROR }

    override val shouldDisplayContent
        get() = Transformations.map(_state) { it == CharactersViewState.State.SUCCESS }

    override val isToolbarVisible
        get() = Transformations.map(isSearchEnabled) { !it }

    override val isSearchEnabled
        get() = Transformations.map(_isSearchEnabled) { it || name.value.isNullOrBlank().not() }

    override val isSearchClearTextVisible
        get() = Transformations.map(name) { it.isNullOrBlank().not() }

    fun postCharacters(charactersList: PagingData<CharacterUIModel>?) {
        _characters.value = charactersList
    }

    fun postState(newState: CharactersViewState.State) = _state.postValue(newState)

    fun sendAction(action: CharactersViewAction) {
        _action.value = action
    }

    fun postSearchStatus(isEnabled: Boolean) {
        _isSearchEnabled.value = isEnabled
    }

    fun postName(characterName: String?) {
        name.value = characterName
        postNameFilter(characterName)
    }

    fun postError(error: CharacterError) {
        _error.value = error
    }

    private fun postNameFilter(name: String?) {
        val currentFilter = _filter.value
        _filter.value = currentFilter?.copy(
            name = name
        )
    }
}