package br.com.lira.rickandmorty.episodes.presentation.mapper

import br.lira.core.presentation.ResourceProvider
import br.com.lira.rickandmorty.episodes.presentation.model.EpisodeUIModel
import br.com.lira.rickandmorty.episodes.domain.model.Episode
import br.com.lira.rickandmorty.episodes.impl.R
import javax.inject.Inject

class EpisodeModelToUIMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(episode: Episode) = EpisodeUIModel.EpisodeUI(
        id = episode.id,
        name = episode.name,
        formattedSeasonNumber = resourceProvider.getString(
            R.string.episode_season_title,
            episode.seasonNumber
        ),
        formattedEpisodeNumber = resourceProvider.getString(
            R.string.episode_number_description,
            episode.episodeNumber.toInt()
        ),
        airDate = episode.airDate
    )
}
