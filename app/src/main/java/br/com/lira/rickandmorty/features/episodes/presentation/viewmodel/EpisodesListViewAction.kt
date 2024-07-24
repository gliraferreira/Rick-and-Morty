package br.com.lira.rickandmorty.features.episodes.presentation.viewmodel

import br.lira.core.presentation.viewmodel.ViewAction

sealed class EpisodesListViewAction : ViewAction {

    data class OpenEpisodeDetails(val episodeId: Long) : EpisodesListViewAction()
}
