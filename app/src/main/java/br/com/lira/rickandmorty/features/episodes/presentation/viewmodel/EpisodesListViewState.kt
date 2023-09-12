package br.com.lira.rickandmorty.features.episodes.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewState

data class EpisodesListViewState(
    val isLoading: Boolean = false
) : ViewState
