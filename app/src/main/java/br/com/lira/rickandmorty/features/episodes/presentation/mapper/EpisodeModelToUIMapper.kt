package br.com.lira.rickandmorty.features.episodes.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.ResourceProvider
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeUIModel
import br.com.lira.rickandmorty.features.shared.domain.model.Episode
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
