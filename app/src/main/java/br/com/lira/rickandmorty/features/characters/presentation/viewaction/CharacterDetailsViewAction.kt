package br.com.lira.rickandmorty.features.characters.presentation.viewaction

import br.com.lira.rickandmorty.core.viewmodel.ViewAction

sealed class CharacterDetailsViewAction : ViewAction {

    data class OpenEpisodeDetails(val episodeId: Long) : CharacterDetailsViewAction()
    data class OpenCurrentLocationDetails(val locationId: Long) : CharacterDetailsViewAction()
}