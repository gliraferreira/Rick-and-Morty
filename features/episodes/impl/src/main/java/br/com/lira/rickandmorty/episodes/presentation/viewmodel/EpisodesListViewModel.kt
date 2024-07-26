package br.com.lira.rickandmorty.episodes.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.map
import br.lira.core.presentation.viewmodel.ViewModel
import br.com.lira.rickandmorty.episodes.domain.usecase.GetAllEpisodesUseCase
import br.com.lira.rickandmorty.episodes.presentation.mapper.EpisodeModelToUIMapper
import br.com.lira.rickandmorty.episodes.presentation.mapper.EpisodesErrorMapper
import br.com.lira.rickandmorty.episodes.presentation.mapper.EpisodesListSeparatorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private const val DELAY_INTERVAL = 500L

@HiltViewModel
class EpisodesListViewModel @Inject constructor(
    private val getAllEpisodes: GetAllEpisodesUseCase,
    private val episodeUiMapper: EpisodeModelToUIMapper,
    private val errorMapper: EpisodesErrorMapper,
    private val separatorMapper: EpisodesListSeparatorMapper,
) : ViewModel<EpisodesListViewState, EpisodesListViewAction>(EpisodesListViewState()) {

    init {
        loadEpisodes()
    }

    fun onLoadStateChanged(loadState: CombinedLoadStates) {
        when (val refresh = loadState.source.refresh) {
            is LoadState.NotLoading -> setState { it.setSuccessState() }
            is LoadState.Loading -> setState { it.setLoadingState() }
            is LoadState.Error -> handleErrorState(refresh)
        }
    }

    fun onTryAgainClicked() {
        loadEpisodes(isTryAgain = true)
    }

    fun onEpisodeClicked(episodeId: Long) {
        sendAction { EpisodesListViewAction.OpenEpisodeDetails(episodeId) }
    }

    private fun loadEpisodes(isTryAgain: Boolean = false) {
        viewModelScope.launch {

            if (isTryAgain) delay(DELAY_INTERVAL)

            getAllEpisodes()
                .map { it.map(episodeUiMapper::mapFrom) }
                .map(separatorMapper::mapFrom)
                .cachedIn(viewModelScope)
                .collect { result -> setState { it.copy(episodes = result) } }
        }
    }

    private fun handleErrorState(refresh: LoadState.Error) {
        val error = errorMapper.mapFrom(refresh.error)
        setState { it.setErrorState(error) }
    }
}
