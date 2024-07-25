package br.com.lira.rickandmorty.features.episodes.presentation.navigation

import androidx.fragment.app.Fragment
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.episodes.navigation.EpisodesNavigator
import br.com.lira.rickandmorty.navigation.animation.SlideFromRightAnimation
import br.com.lira.rickandmorty.navigation.navigateToFragment
import br.com.lira.rickandmorty.features.episodes.presentation.ui.EpisodeDetailsFragment
import javax.inject.Inject

class EpisodesNavigatorImpl @Inject constructor() : EpisodesNavigator {

    override fun openEpisodeDetails(fragment: Fragment, episodeId: Long) {
        fragment.navigateToFragment(
            hostRes = R.id.app_nav_host_fragment,
            destination = EpisodeDetailsFragment.newInstance(episodeId),
            fragmentAnimation = SlideFromRightAnimation
        )
    }
}