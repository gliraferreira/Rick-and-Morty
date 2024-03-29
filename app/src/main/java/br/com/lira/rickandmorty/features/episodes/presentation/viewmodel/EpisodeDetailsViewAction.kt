package br.com.lira.rickandmorty.features.episodes.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewAction

sealed class EpisodeDetailsViewAction : ViewAction {

    data class OpenCharacterDetails(val characterId: Long) : EpisodeDetailsViewAction()
}
