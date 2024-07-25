package br.com.lira.rickandmorty.features.locations.presentation.navigation

import androidx.fragment.app.Fragment
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.navigation.animation.SlideFromRightAnimation
import br.com.lira.rickandmorty.navigation.navigateToFragment
import br.com.lira.rickandmorty.features.locations.presentation.ui.LocationDetailsFragment
import javax.inject.Inject

class LocationNavigator @Inject constructor() {

    fun openLocationDetails(fragment: Fragment, locationId: Long) {
        fragment.navigateToFragment(
            hostRes = R.id.app_nav_host_fragment,
            destination = LocationDetailsFragment.newInstance(locationId),
            fragmentAnimation = SlideFromRightAnimation
        )
    }
}