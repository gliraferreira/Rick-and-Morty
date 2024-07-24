package br.com.lira.rickandmorty.features.locations.presentation.viewmodel

import br.lira.core.presentation.viewmodel.ViewAction

sealed class LocationsListViewAction : ViewAction {

    data class OpenLocationDetails(val locationId: Long) : LocationsListViewAction()
}
