package br.com.lira.rickandmorty.features.episodes.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.ResourceProvider
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeUIModel
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject

class EpisodeModelToUIMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(episode: Episode) = EpisodeUIModel.EpisodeUI(
        id = episode.id,
        name = episode.name,
        seasonNumber = resourceProvider.getString(
            R.string.episode_season_title,
            episode.seasonNumber.toInt()
        ),
        episodeNumber = resourceProvider.getString(
            R.string.episode_number_description,
            episode.episodeNumber.toInt()
        ),
        charactersQuantity = episode.characterIds.size,
        airDate = episode.airDate
    )
}
