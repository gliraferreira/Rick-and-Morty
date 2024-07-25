package br.com.lira.rickandmorty.episodes.navigation

import androidx.fragment.app.Fragment

interface EpisodesNavigator {
    fun openEpisodeDetails(fragment: Fragment, episodeId: Long)
}