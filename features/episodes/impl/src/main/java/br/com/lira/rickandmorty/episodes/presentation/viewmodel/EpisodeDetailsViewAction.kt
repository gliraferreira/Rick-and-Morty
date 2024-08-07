package br.com.lira.rickandmorty.episodes.presentation.viewmodel

import br.lira.core.presentation.viewmodel.ViewAction

sealed class EpisodeDetailsViewAction : ViewAction {

    data class OpenCharacterDetails(val characterId: Long) : EpisodeDetailsViewAction()
}
