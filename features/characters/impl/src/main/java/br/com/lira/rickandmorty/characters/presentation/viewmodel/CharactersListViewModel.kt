package br.com.lira.rickandmorty.characters.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import br.lira.core.presentation.viewmodel.ViewModel
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.characters.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterFilterToTextMapper
import br.com.lira.rickandmorty.characters.presentation.mapper.CharacterModelToUIMapper
import br.com.lira.rickandmorty.characters.presentation.mapper.CharactersErrorMapper
import br.com.lira.rickandmorty.characters.presentation.viewaction.CharactersListViewAction
import br.com.lira.rickandmorty.characters.presentation.viewstate.CharactersListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DELAY_INTERVAL = 500L

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharactersUseCase,
    private val characterUiMapper: CharacterModelToUIMapper,
    private val errorMapper: CharactersErrorMapper,
    private val displayableFilterMapper: CharacterFilterToTextMapper,
) : ViewModel<CharactersListViewState, CharactersListViewAction>(CharactersListViewState()) {

    init {
        loadCharacters()
    }

    fun onLoadStateChanged(loadState: CombinedLoadStates) {
        when (val refresh = loadState.source.refresh) {
            is LoadState.NotLoading -> setState { it.setSuccessState() }
            is LoadState.Loading -> setState { it.setLoadingState() }
            is LoadState.Error -> handleErrorState(refresh)
        }
    }

    fun onSearchClicked() {
        val currentFilter = state.value?.filter
        sendAction { CharactersListViewAction.OpenCharacterFilter(currentFilter) }
    }

    fun onTryAgainClicked() {
        loadCharacters(isTryAgain = true)
    }

    fun onCharacterClicked(characterId: Long) {
        sendAction { CharactersListViewAction.OpenCharacterDetails(characterId) }
    }

    fun onCharacterFilterUpdated(selectedFilter: CharacterFilter?) {
        setState { it.copy(filter = selectedFilter) }
        loadCharacters()
    }

    fun onClearFiltersClicked() {
        setState { it.copy(filter = CharacterFilter()) }
        loadCharacters()
    }

    private fun loadCharacters(isTryAgain: Boolean = false) {
        viewModelScope.launch {
            val filter = state.value?.filter
            setLoadingState(filter)

            if (isTryAgain) delay(DELAY_INTERVAL)

            getAllCharacters(filter).cachedIn(viewModelScope).collect { result ->
                val uiPagingData = result.map(characterUiMapper::mapFrom)
                setState { it.copy(characters = uiPagingData) }
            }
        }
    }

    private fun setLoadingState(filter: CharacterFilter?) {
        val filterDetails = filter?.let(displayableFilterMapper::mapFrom).orEmpty()
        val isFilterOn = filterDetails.isNotEmpty()

        setState {
            it.copy(
                characters = PagingData.empty(),
                filterDetails = filterDetails,
                isFilteringResults = isFilterOn
            ).setLoadingState()
        }
    }

    private fun handleErrorState(refresh: LoadState.Error) {
        val error = errorMapper.mapFrom(refresh.error)
        setState { it.setErrorState(error) }
    }
}
