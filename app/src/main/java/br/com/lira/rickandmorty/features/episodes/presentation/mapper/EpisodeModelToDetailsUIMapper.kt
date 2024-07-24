package br.com.lira.rickandmorty.features.episodes.presentation.mapper

import br.com.lira.rickandmorty.R
import br.lira.core.presentation.ResourceProvider
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeDetailsUIModel
import br.com.lira.rickandmorty.features.shared.domain.model.Episode
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
