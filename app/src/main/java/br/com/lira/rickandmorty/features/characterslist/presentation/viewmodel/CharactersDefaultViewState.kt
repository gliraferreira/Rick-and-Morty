package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import br.com.lira.rickandmorty.core.toolkit.SingleLiveData
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterError
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus
import javax.inject.Inject

class CharactersDefaultViewState @Inject constructor() : CharactersViewState {

    private val _characters = MutableLiveData<PagingData<CharacterUIModel>?>()
    private val _action = SingleLiveData<CharactersViewAction>()
    private val _isSearchEnabled = MutableLiveData<Boolean>().apply { value = true }
    private val _isFilteringResults = MutableLiveData<Boolean>().apply { value = false }
    private val _filter = MutableLiveData<CharacterFilter>().apply { value = CharacterFilter() }

    private val _isSuccess = MutableLiveData<Boolean>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isError = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<CharacterError>().apply { value = null }

    override val characters get() = _characters
    override val action get() = _action
    override val filter get() = _filter

    override val isLoading get() = _isLoading
    override val isError get() = _isError
    override val shouldDisplayContent get() = _isSuccess

    override val error get() = _error
    override val isSearchEnabled get() = _isSearchEnabled
    override val isFilteringResults get() = _isFilteringResults

    fun setSuccessState() {
        _isSuccess.value = true
        _isLoading.value = false
        _isError.value = false
    }

    fun setLoadingState() {
        _isSuccess.value = false
        _isLoading.value = true
        _isError.value = false
    }

    fun setErrorState(error: CharacterError) {
        _isSuccess.value = false
        _isLoading.value = false
        _isError.value = true
        _error.value = error
    }

    fun postCharacters(charactersList: PagingData<CharacterUIModel>?) {
        _characters.value = charactersList
    }

    fun clearCharactersList() {
        _characters.value = PagingData.empty()
    }

    fun sendAction(action: CharactersViewAction) {
        _action.value = action
    }

    fun postFilter(newFilter: CharacterFilter?) {
        _filter.value = newFilter
    }

    fun clearFilter() {
        _filter.value = CharacterFilter()
    }

    fun updateIsFilteringResults(isFilterOn: Boolean) {
        _isFilteringResults.value = isFilterOn
    }
}
