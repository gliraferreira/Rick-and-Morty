package br.com.lira.rickandmorty.features.locations.presentation.viewmodel

import br.com.lira.rickandmorty.core.viewmodel.ViewAction

sealed class LocationsListViewAction : ViewAction {

    data class OpenLocationDetails(val locationId: Long) : LocationsListViewAction()
}
