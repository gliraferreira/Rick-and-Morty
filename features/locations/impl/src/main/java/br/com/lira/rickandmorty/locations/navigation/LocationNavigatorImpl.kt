package br.com.lira.rickandmorty.locations.navigation

import androidx.fragment.app.Fragment
import br.com.lira.rickandmorty.locations.presentation.ui.fragment.LocationDetailsFragment
import br.com.lira.rickandmorty.navigation.NavigationHostProvider
import br.com.lira.rickandmorty.navigation.animation.SlideFromRightAnimation
import br.com.lira.rickandmorty.navigation.navigateToFragment
import javax.inject.Inject

class LocationNavigatorImpl @Inject constructor(
    private val hostProvider: NavigationHostProvider
) : LocationNavigator {

    override fun openLocationDetails(fragment: Fragment, locationId: Long) {
        fragment.navigateToFragment(
            hostRes = hostProvider.getNavHostId(),
            destination = LocationDetailsFragment.newInstance(locationId),
            fragmentAnimation = SlideFromRightAnimation
        )
    }
}