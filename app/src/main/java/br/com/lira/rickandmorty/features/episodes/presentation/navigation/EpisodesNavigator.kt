package br.com.lira.rickandmorty.features.episodes.presentation.navigation

import androidx.fragment.app.Fragment
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.SlideFromRightAnimation
import br.com.lira.rickandmorty.core.toolkit.navigateToFragment
import br.com.lira.rickandmorty.features.episodes.presentation.ui.EpisodeDetailsFragment
import javax.inject.Inject

class EpisodesNavigator @Inject constructor() {

    fun openEpisodeDetails(fragment: Fragment, episodeId: Long) {
        fragment.navigateToFragment(
            hostRes = R.id.app_nav_host_fragment,
            destination = EpisodeDetailsFragment.newInstance(episodeId),
            fragmentAnimation = SlideFromRightAnimation
        )
    }
}