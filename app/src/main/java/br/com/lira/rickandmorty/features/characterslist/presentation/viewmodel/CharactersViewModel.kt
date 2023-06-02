package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterModelToUIMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharactersErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val NAME_MIN_LENGTH = 3
private const val DELAY_INTERVAL = 500L

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharactersUseCase,
    private val mutableState: CharactersDefaultViewState,
    private val characterUiMapper: CharacterModelToUIMapper,
    private val errorMapper: CharactersErrorMapper
) : ViewModel() {
    val viewState: CharactersViewState get() = mutableState

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            loadCharacters()
        }
        toggleToolbarAndSearchVisibility(isSearchVisible = false)
    }

    fun onLoadStateChanged(loadState: CombinedLoadStates) {
        when (val refresh = loadState.source.refresh) {
            is LoadState.NotLoading -> {
                mutableState.setSuccessState()
            }

            is LoadState.Loading -> {
                mutableState.setLoadingState()
            }

            is LoadState.Error -> {
                handleErrorState(refresh)
            }
        }
    }

    fun onSearchClicked() {
        toggleToolbarAndSearchVisibility(isSearchVisible = true)
        mutableState.sendAction(CharactersViewAction.FocusOnSearch)
    }

    fun onSearchFocusChanged(hasFocus: Boolean) {
        val name = mutableState.filter.value?.name.orEmpty()
        val isSearchVisible = hasFocus || name.isNotEmpty()

        toggleToolbarAndSearchVisibility(isSearchVisible)
        mutableState.sendAction(CharactersViewAction.UpdateSearchKeyboardFocus(hasFocus))
    }

    fun onSearchBackClicked() {
        toggleToolbarAndSearchVisibility(isSearchVisible = false)
        mutableState.sendAction(CharactersViewAction.UpdateSearchText(""))
    }

    fun onSearchClearTextClicked() {
        mutableState.sendAction(CharactersViewAction.FocusOnSearch)
        mutableState.sendAction(CharactersViewAction.UpdateSearchText(""))
    }

    fun onSearchTextChanged(text: Editable?) {
        val name = text?.toString().orEmpty()
        mutableState.updateClearTextVisibility(name.isNotEmpty())

        if (name != viewState.filter.value?.name) {
            searchByName(name)
        }
    }

    fun onTryAgainClicked() {
        viewModelScope.launch {
            mutableState.setLoadingState()
            delay(DELAY_INTERVAL)
            loadCharacters()
        }
    }

    fun onCharacterClicked(characterId: Long) {
        mutableState.sendAction(CharactersViewAction.OpenCharacterDetails(characterId))
    }

    private suspend fun loadCharacters() {
        mutableState.clearCharactersList()
        mutableState.setLoadingState()
        val filter = mutableState.filter.value

        getAllCharacters(filter).cachedIn(viewModelScope).collect { result ->
            val uiPagingData = result.map { characterUiMapper.mapFrom(it) }
            mutableState.postCharacters(uiPagingData)
        }
    }

    private fun toggleToolbarAndSearchVisibility(isSearchVisible: Boolean) {
        mutableState.postSearchStatus(isSearchVisible)
        mutableState.updateToolbarVisibility(!isSearchVisible)
    }

    private fun handleErrorState(refresh: LoadState.Error) {
        val error = errorMapper.mapFrom(refresh.error)
        mutableState.setErrorState(error)
    }

    private fun searchByName(name: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            mutableState.postName(name)

            if (name.length > NAME_MIN_LENGTH) delay(DELAY_INTERVAL)

            loadCharacters()
        }
    }
}