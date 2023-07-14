package br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.lira.rickandmorty.core.viewmodel.ViewModel
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterFilterToTextMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharacterModelToUIMapper
import br.com.lira.rickandmorty.features.characterslist.presentation.mapper.CharactersErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DELAY_INTERVAL = 500L

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharactersUseCase,
    private val characterUiMapper: CharacterModelToUIMapper,
    private val errorMapper: CharactersErrorMapper,
    private val displayableFilterMapper: CharacterFilterToTextMapper,
) : ViewModel<CharactersViewState, CharactersViewAction>(CharactersViewState()) {

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
        sendAction { CharactersViewAction.OpenCharacterFilter(currentFilter) }
    }

    fun onTryAgainClicked() {
        loadCharacters(isTryAgain = true)
    }

    fun onCharacterClicked(characterId: Long) {
        sendAction { CharactersViewAction.OpenCharacterDetails(characterId) }
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
                val uiPagingData = result.map { characterUiMapper.mapFrom(it) }
                setState { it.copy(characters = uiPagingData) }
            }
        }
    }

    private fun setLoadingState(filter: CharacterFilter?) {
        val filterDetails = filter?.let(displayableFilterMapper::mapFrom).orEmpty()
        val isFilterOn = isFilteringResults(filter)

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

    private fun isFilteringResults(currentFilter: CharacterFilter?): Boolean {
        val hasAnyFilter = currentFilter?.let {
            it.name?.isNotEmpty() ?: false || it.status != null || it.gender != null
        } ?: false

        return hasAnyFilter
    }
}
