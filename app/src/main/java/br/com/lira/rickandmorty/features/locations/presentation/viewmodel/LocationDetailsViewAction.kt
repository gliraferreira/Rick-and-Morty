package br.com.lira.rickandmorty.features.locations.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewAction

sealed class LocationDetailsViewAction : ViewAction {

    data class OpenCharacterDetails(val characterId: Long) : LocationDetailsViewAction()
}
