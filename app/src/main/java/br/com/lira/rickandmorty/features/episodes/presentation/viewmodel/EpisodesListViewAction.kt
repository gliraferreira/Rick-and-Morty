package br.com.lira.rickandmorty.features.episodes.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewAction

sealed class EpisodesListViewAction : ViewAction {

    data class OpenEpisodeDetails(val episodeId: Long) : EpisodesListViewAction()
}
