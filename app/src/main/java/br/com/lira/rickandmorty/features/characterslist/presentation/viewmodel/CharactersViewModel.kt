package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterModelToUIMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterStatusMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharactersErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DELAY_INTERVAL = 500L

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharactersUseCase,
    private val mutableState: CharactersDefaultViewState,
    private val characterUiMapper: CharacterModelToUIMapper,
    private val errorMapper: CharactersErrorMapper
) : ViewModel() {
    val viewState: CharactersViewState get() = mutableState

    init {
        loadCharacters()
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
        val currentFilter = mutableState.filter.value
        mutableState.sendAction(CharactersViewAction.OpenCharacterFilter(currentFilter))
    }

    fun onTryAgainClicked() {
        loadCharacters(isTryAgain = true)
    }

    fun onCharacterClicked(characterId: Long) {
        mutableState.sendAction(CharactersViewAction.OpenCharacterDetails(characterId))
    }

    fun onCharacterFilterUpdated(selectedFilter: CharacterFilter?) {
        mutableState.postFilter(selectedFilter)
        loadCharacters()
    }

    private fun loadCharacters(isTryAgain: Boolean = false) {
        viewModelScope.launch {
            mutableState.clearCharactersList()
            mutableState.setLoadingState()
            val filter = mutableState.filter.value

            if (isTryAgain) delay(DELAY_INTERVAL)

            getAllCharacters(filter).cachedIn(viewModelScope).collect { result ->
                val uiPagingData = result.map { characterUiMapper.mapFrom(it) }
                mutableState.postCharacters(uiPagingData)
            }
        }
    }

    private fun handleErrorState(refresh: LoadState.Error) {
        val error = errorMapper.mapFrom(refresh.error)
        mutableState.setErrorState(error)
    }
}
