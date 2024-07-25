package br.com.lira.rickandmorty.locations.presentation.viewmodel

import br.lira.core.presentation.viewmodel.ViewAction

sealed class LocationDetailsViewAction : ViewAction {

    data class OpenCharacterDetails(val characterId: Long) : LocationDetailsViewAction()
}
