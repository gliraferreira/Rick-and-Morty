package br.com.lira.rickandmorty.episodes

import br.com.lira.rickandmorty.episodes.data.remote.api.response.EpisodeResponse
import br.com.lira.rickandmorty.episodes.domain.model.Episode

object EpisodeStub {

    val episodeResponseStub = EpisodeResponse(
        id = 2,
        name = "Pilot",
        airDate = "December 2, 2013",
        episodeNumber = "S01E02",
        characters = listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2"
        )
    )

    val episodeStub = Episode(
        id = 1,
        name = "Pilot",
        airDate = "December 2, 2013",
        seasonNumber = 1,
        episodeNumber = 2,
        characterIds = listOf(
            "1",
            "2"
        )
    )
}