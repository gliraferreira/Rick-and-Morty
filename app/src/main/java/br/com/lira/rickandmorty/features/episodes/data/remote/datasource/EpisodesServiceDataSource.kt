package br.com.lira.rickandmorty.features.episodes.data.remote.datasource

import br.com.lira.rickandmorty.features.episodes.data.remote.api.EpisodesApi
import br.com.lira.rickandmorty.features.episodes.data.remote.api.response.EpisodeResponse
import br.com.lira.rickandmorty.core.data.response.PageResponse
import javax.inject.Inject

class EpisodesServiceDataSource @Inject constructor(
    private val episodesApi: EpisodesApi
) : EpisodesRemoteDataSource {

    override suspend fun getMultipleEpisodes(episodeIds: List<String>): List<EpisodeResponse> {
        return episodesApi.getMultipleEpisodes(episodeIds)
    }

    override suspend fun getAllEpisodes(page: Int): PageResponse<EpisodeResponse> {
        return episodesApi.getAllEpisodes(page)
    }

    override suspend fun getEpisode(episodeId: Long?): EpisodeResponse {
        return episodesApi.getEpisodeById(episodeId)
    }
}
