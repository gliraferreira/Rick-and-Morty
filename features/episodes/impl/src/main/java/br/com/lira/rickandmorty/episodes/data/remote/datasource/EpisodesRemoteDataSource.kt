package br.com.lira.rickandmorty.episodes.data.remote.datasource

import br.com.lira.rickandmorty.episodes.data.remote.api.response.EpisodeResponse
import br.com.lira.rickandmorty.core.data.response.PageResponse

interface EpisodesRemoteDataSource {

    suspend fun getMultipleEpisodes(episodeIds: List<String>): List<EpisodeResponse>

    suspend fun getAllEpisodes(page: Int): PageResponse<EpisodeResponse>

    suspend fun getEpisode(episodeId: Long?): EpisodeResponse
}