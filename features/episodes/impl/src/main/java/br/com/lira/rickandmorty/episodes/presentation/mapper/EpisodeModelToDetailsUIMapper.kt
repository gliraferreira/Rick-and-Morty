package br.com.lira.rickandmorty.episodes.presentation.mapper

import br.lira.core.presentation.ResourceProvider
import br.com.lira.rickandmorty.episodes.presentation.model.EpisodeDetailsUIModel
import br.com.lira.rickandmorty.episodes.domain.model.Episode
import br.com.lira.rickandmorty.episodes.impl.R
import javax.inject.Inject

class EpisodeModelToDetailsUIMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(episode: Episode) = EpisodeDetailsUIModel(
        id = episode.id,
        name = episode.name,
        episodeNumber = resourceProvider.getString(
            R.string.episode_details_episode_number,
            episode.seasonNumber,
            episode.episodeNumber
        ),
        airDate = episode.airDate
    )
}
