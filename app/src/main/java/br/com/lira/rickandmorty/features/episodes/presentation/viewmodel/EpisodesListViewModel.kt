package br.com.lira.rickandmorty.features.episodes.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewModel
import br.com.lira.rickandmorty.features.episodes.domain.usecase.GetAllEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesListViewModel @Inject constructor(
    private val getAllEpisodes: GetAllEpisodesUseCase
) : ViewModel<EpisodesListViewState, EpisodesListViewAction>(EpisodesListViewState()) {
}
