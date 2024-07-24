package br.com.lira.rickandmorty.features.characters.presentation.viewaction

import br.lira.core.presentation.viewmodel.ViewAction

sealed class CharacterDetailsViewAction : ViewAction {

    data class OpenEpisodeDetails(val episodeId: Long) : CharacterDetailsViewAction()
    data class OpenCurrentLocationDetails(val locationId: Long) : CharacterDetailsViewAction()
}