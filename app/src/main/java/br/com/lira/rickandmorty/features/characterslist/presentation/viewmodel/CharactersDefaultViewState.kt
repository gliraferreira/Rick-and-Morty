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

    private val _characters = MutableLiveData<PagingData<CharacterUIModel>?>()
    private val _state = MutableLiveData<CharactersViewState.State>()
    private val _action = OneShotLiveData<CharactersViewAction>()
    private val _isSearchEnabled = MutableLiveData<Boolean>().apply { value = false }
    private val _name = MutableLiveData<String?>().apply { value = null }
    private val _filter = MutableLiveData<CharacterFilter>().apply { value = CharacterFilter() }
    private val _error = MutableLiveData<CharacterError>().apply { value = null }

    override val characters: LiveData<PagingData<CharacterUIModel>?> get() = _characters
    override val state: LiveData<CharactersViewState.State> get() = _state
    override val action: LiveData<CharactersViewAction> get() = _action
    override val name: LiveData<String?> get() = _name
    override val filter: LiveData<CharacterFilter> get() = _filter
    override val error: LiveData<CharacterError> get() = _error

    override fun isLoading() = Transformations.map(_state) {
        it == CharactersViewState.State.LOADING
    }

    override fun isEmpty() = Transformations.map(_state) {
        it == CharactersViewState.State.EMPTY
    }

    override fun isError() = Transformations.map(_state) {
        it == CharactersViewState.State.ERROR
    }

    override fun shouldDisplayContent() = Transformations.map(_state) {
        it == CharactersViewState.State.SUCCESS
    }

    override fun isToolbarVisible() = Transformations.map(isSearchEnabled()) {
        !it
    }

    override fun isSearchEnabled() = Transformations.map(_isSearchEnabled) {
        it || _name.value.isNullOrBlank().not()
    }

    override fun isSearchClearTextVisible() = Transformations.map(_name) {
        it.isNullOrBlank().not()
    }

    fun postCharacters(
        charactersList: PagingData<CharacterUIModel>?
    ) {
        _characters.value = charactersList
    }

    fun postState(newState: CharactersViewState.State) = _state.postValue(newState)

    fun sendAction(action: CharactersViewAction) {
        _action.value = action
    }

    fun postSearchStatus(isEnabled: Boolean) {
        _isSearchEnabled.value = isEnabled
    }

    fun postName(name: String?) {
        _name.value = name
        postNameFilter(name)
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