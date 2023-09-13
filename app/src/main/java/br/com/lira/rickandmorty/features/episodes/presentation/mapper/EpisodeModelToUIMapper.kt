package br.com.lira.rickandmorty.features.episodes.presentation.mapper

import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeUIModel
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject

class EpisodeModelToUIMapper @Inject constructor() {

    fun mapFrom(episode: Episode) = EpisodeUIModel.Episode(
        id = episode.id,
        name = episode.name,
        seasonNumber = episode.seasonNumber,
        episodeNumber = episode.episodeNumber
    )
}
