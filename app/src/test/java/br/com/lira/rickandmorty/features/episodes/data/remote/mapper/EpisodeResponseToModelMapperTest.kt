package br.com.lira.rickandmorty.features.episodes.data.remote.mapper

import br.com.lira.rickandmorty.core.data.mapper.UrlMapper
import br.com.lira.rickandmorty.episodes.data.remote.mapper.EpisodeResponseToModelMapper
import br.com.lira.rickandmorty.stub.EpisodeStub
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlin.test.assertEquals
import org.junit.Test

class EpisodeResponseToModelMapperTest {

    private val urlMapper: UrlMapper = mockk(relaxed = true)
    private val mapper = EpisodeResponseToModelMapper(urlMapper)

    @Test
    fun `mapFrom when gets a response return basic info`() {
        val expectedId = 1L
        val expectedName = "Pilot"
        val expectedAirDate = "December 2, 2013"
        val characters = listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2"
        )
        val response = EpisodeStub.episodeResponseStub.copy(
            id = expectedId,
            name = expectedName,
            airDate = expectedAirDate,
            characters = characters
        )

        val result = mapper.mapFrom(response)

        assertEquals(expectedId, result.id)
        assertEquals(expectedName, result.name)
        assertEquals(expectedAirDate, result.airDate)
        assertEquals(characters.size, result.characterIds.size)
    }

    @Test
    fun `mapFrom when gets a response return season and episode number separated`() {
        val responseEpisodeNumber = "S01E03"
        val expectedSeasonNumber = 1
        val expectedEpisodeNumber = 3
        val response = EpisodeStub.episodeResponseStub.copy(
            episodeNumber = responseEpisodeNumber
        )

        val result = mapper.mapFrom(response)

        assertEquals(expectedSeasonNumber, result.seasonNumber)
        assertEquals(expectedEpisodeNumber, result.episodeNumber)
    }

    @Test
    fun `mapFrom when gets a response should map all characters`() {
        val characterUrl1 = "https://rickandmortyapi.com/api/character/1"
        val characterUrl2 = "https://rickandmortyapi.com/api/character/2"
        val characterUrl3 = "https://rickandmortyapi.com/api/character/3"
        val characters = listOf(
            characterUrl1,
            characterUrl2,
            characterUrl3
        )
        val response = EpisodeStub.episodeResponseStub.copy(
            characters = characters
        )

        val result = mapper.mapFrom(response)

        verifyOrder {
            urlMapper.mapId(characterUrl1)
            urlMapper.mapId(characterUrl2)
            urlMapper.mapId(characterUrl3)
        }
    }
}
