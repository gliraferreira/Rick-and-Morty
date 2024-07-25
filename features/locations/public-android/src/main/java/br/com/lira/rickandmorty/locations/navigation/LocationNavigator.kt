package br.com.lira.rickandmorty.locations.navigation

import androidx.fragment.app.Fragment

interface LocationNavigator {

    fun openLocationDetails(fragment: Fragment, locationId: Long)
}