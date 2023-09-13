package br.com.lira.rickandmorty.features.episodes.data.remote.mapper

import br.com.lira.rickandmorty.core.mapper.UrlMapper
import br.com.lira.rickandmorty.features.episodes.data.remote.api.response.EpisodeResponse
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject

private const val EPISODE_PREFIX = 'e'

class EpisodeResponseToModelMapper @Inject constructor(
    private val urlMapper: UrlMapper
) {

    fun mapFrom(response: EpisodeResponse) = Episode(
        id = response.id,
        name = response.name,
        airDate = response.airDate,
        seasonNumber = response.episodeNumber.let(::getSeasonNumber),
        episodeNumber = response.episodeNumber.let(::getEpisodeNumber),
        characterIds = response.characters.map(urlMapper::mapId)
    )

    private fun getSeasonNumber(
        episode: String
    ) = episode
        .indexOfFirst { it.equals(EPISODE_PREFIX, true) }
        .takeIf { it != -1 }
        ?.let { episode.substring(1, it) }
        .orEmpty()

    private fun getEpisodeNumber(
        episode: String
    ) = episode
        .indexOfFirst { it.equals(EPISODE_PREFIX, true) }
        .takeIf { it != -1 }
        ?.let { episode.substring(it + 1) }
        .orEmpty()
}
