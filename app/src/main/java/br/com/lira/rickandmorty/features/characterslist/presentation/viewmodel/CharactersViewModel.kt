package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterModelToUIMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharactersErrorMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.view.CharactersListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

private const val NAME_MIN_LENGTH = 3
private const val DELAY_INTERVAL = 500L
private const val NOT_FOUND = 404

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharactersUseCase,
    private val mutableState: CharactersDefaultViewState,
    private val characterUiMapper: CharacterModelToUIMapper,
    private val errorMapper: CharactersErrorMapper
) : ViewModel(), CharactersListener {
    val viewState: CharactersViewState get() = mutableState

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            loadCharacters()
        }
    }

    private suspend fun loadCharacters() {
        setLoadingState()
        val filter = viewState.filter.value

        getAllCharacters(filter).cachedIn(viewModelScope).collect { result ->
            val uiPagingData = result.map { characterUiMapper.mapFrom(it) }
            mutableState.postCharacters(uiPagingData)
        }
    }

    fun onLoadStateChanged(loadState: CombinedLoadStates, itemCount: Int) {
        when (val refresh = loadState.source.refresh) {
            is LoadState.NotLoading -> {
                handleSuccessState(loadState, itemCount)
            }

            is LoadState.Loading -> {
                mutableState.postState(CharactersViewState.State.LOADING)
            }

            is LoadState.Error -> {
                handleErrorState(refresh)
            }
        }
    }

    fun onSearchClicked() {
        mutableState.postSearchStatus(true)
        mutableState.sendAction(CharactersViewAction.FocusOnSearch)
    }

    fun onSearchFocusChanged(hasFocus: Boolean) {
        mutableState.postSearchStatus(hasFocus)
        mutableState.sendAction(CharactersViewAction.UpdateSearchKeyboardFocus(hasFocus))
    }

    fun onSearchBackClicked() {
        mutableState.postSearchStatus(false)
        mutableState.sendAction(CharactersViewAction.ClearSearchText)
    }

    fun onSearchClearTextClicked() {
        mutableState.sendAction(CharactersViewAction.ClearSearchText)
    }

    fun onSearchTextChanged(text: Editable?) {
        val name = text?.toString().orEmpty()
        if (name != viewState.filter.value?.name) {

            searchByName(name)
        }
    }

    fun onTryAgainClicked() {
        viewModelScope.launch {
            loadCharacters()
        }
    }

    override fun onCharacterClicked(characterId: Long) {
        mutableState.sendAction(CharactersViewAction.OpenCharacterDetails(characterId))
    }

    private fun handleErrorState(refresh: LoadState.Error) {
        val error = errorMapper.mapFrom(refresh.error)
        mutableState.postError(error)
        mutableState.postState(CharactersViewState.State.ERROR)
    }

    private fun searchByName(name: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            mutableState.postName(name)

            if (name.length > NAME_MIN_LENGTH) delay(DELAY_INTERVAL)

            loadCharacters()
        }
    }

    private fun handleSuccessState(loadState: CombinedLoadStates, itemCount: Int) {
        if (loadState.append.endOfPaginationReached && itemCount == 0) {
            mutableState.postState(CharactersViewState.State.EMPTY)
        } else {
            mutableState.postState(CharactersViewState.State.SUCCESS)
        }
    }

    private fun setLoadingState() {
        mutableState.postCharacters(PagingData.empty())
        mutableState.postState(CharactersViewState.State.LOADING)
    }
}