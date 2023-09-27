package br.com.lira.rickandmorty.features.episodes.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.lira.rickandmorty.core.viewmodel.ViewModel
import br.com.lira.rickandmorty.features.episodes.domain.usecase.GetEpisodeByIdUseCase
import br.com.lira.rickandmorty.features.episodes.presentation.mapper.EpisodeModelToDetailsUIMapper
import br.com.lira.rickandmorty.features.episodes.presentation.mapper.EpisodesErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    private val errorMapper: EpisodesErrorMapper,
    private val getEpisodeById: GetEpisodeByIdUseCase,
    private val episodeMapper: EpisodeModelToDetailsUIMapper
) : ViewModel<EpisodeDetailsViewState, EpisodeDetailsViewAction>(EpisodeDetailsViewState()) {

    fun init(episodeId: Long?) {
        setState { it.setLoadingState() }

        viewModelScope.launch {
            runCatching {
                getEpisodeById(episodeId)
            }.onSuccess { episode ->
                val episodeUi = episodeMapper.mapFrom(episode)
                setState { it.setSuccessState(episodeUi) }
            }.onFailure { handleErrorState(it) }
        }
    }

    private fun handleErrorState(error: Throwable) {
        val error = errorMapper.mapFrom(error)
        setState { it.setErrorState(error) }
    }
}