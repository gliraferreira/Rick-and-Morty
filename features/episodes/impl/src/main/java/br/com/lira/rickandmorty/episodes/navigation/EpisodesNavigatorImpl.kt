package br.com.lira.rickandmorty.episodes.navigation

import androidx.fragment.app.Fragment
import br.com.lira.rickandmorty.episodes.presentation.ui.fragment.EpisodeDetailsFragment
import br.com.lira.rickandmorty.navigation.NavigationHostProvider
import br.com.lira.rickandmorty.navigation.animation.SlideFromRightAnimation
import br.com.lira.rickandmorty.navigation.navigateToFragment
import javax.inject.Inject

class EpisodesNavigatorImpl @Inject constructor(
    private val hostProvider: NavigationHostProvider
) : EpisodesNavigator {

    override fun openEpisodeDetails(fragment: Fragment, episodeId: Long) {
        fragment.navigateToFragment(
            hostRes = hostProvider.getNavHostId(),
            destination = EpisodeDetailsFragment.newInstance(episodeId),
            fragmentAnimation = SlideFromRightAnimation
        )
    }
}