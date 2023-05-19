package br.com.lira.rickandmorty.features.episodes.data.remote.mapper

import br.com.lira.rickandmorty.core.mapper.UrlMapper
import br.com.lira.rickandmorty.features.episodes.data.remote.api.response.EpisodeResponse
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject

class EpisodeResponseToModelMapper @Inject constructor(
    private val urlMapper: UrlMapper
) {

    fun mapFrom(response: EpisodeResponse) = Episode(
        id = response.id,
        name = response.name,
        airDate = response.airDate,
        episode = response.episode,
        characterIds = response.characters.map(urlMapper::mapId)
    )
}