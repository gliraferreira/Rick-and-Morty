package br.com.lira.rickandmorty.features.episodes.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import br.com.lira.rickandmorty.core.viewmodel.ViewModel
import br.com.lira.rickandmorty.features.episodes.domain.usecase.GetAllEpisodesUseCase
import br.com.lira.rickandmorty.features.episodes.presentation.mapper.EpisodeModelToUIMapper
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class EpisodesListViewModel @Inject constructor(
    private val getAllEpisodes: GetAllEpisodesUseCase,
    private val episodeUiMapper: EpisodeModelToUIMapper
) : ViewModel<EpisodesListViewState, EpisodesListViewAction>(EpisodesListViewState()) {

    init {
        loadEpisodes()
    }

    fun onLoadStateChanged(loadState: CombinedLoadStates) {
        when (val refresh = loadState.source.refresh) {
            is LoadState.NotLoading -> setState { it.setSuccessState() }
            is LoadState.Loading -> setState { it.setLoadingState() }
            is LoadState.Error -> {}
        }
    }

    private fun loadEpisodes() {
        viewModelScope.launch {
            getAllEpisodes()
                .map { pagingData -> pagingData.map(episodeUiMapper::mapFrom) }
                .map { mapEpisodeHeader(it) }
                .cachedIn(viewModelScope)
                .collect { result -> setState { it.copy(episodes = result) } }
        }
    }

    private fun mapEpisodeHeader(
        pagingData: PagingData<EpisodeUIModel.EpisodeUI>
    ) = pagingData.insertSeparators { before, after ->
        if (after == null) {
            return@insertSeparators null
        }

        if (before == null || before.seasonNumber != after.seasonNumber) {
            return@insertSeparators EpisodeUIModel.Header(after.seasonNumber)
        }

        return@insertSeparators null
    }
}
