package br.com.lira.rickandmorty.features.episodes.data.remote.datasource

import br.com.lira.rickandmorty.features.episodes.data.remote.api.EpisodesApi
import br.com.lira.rickandmorty.features.episodes.data.remote.api.response.EpisodeResponse
import br.com.lira.rickandmorty.features.episodes.data.remote.mapper.EpisodeResponseToModelMapper
import br.com.lira.rickandmorty.main.data.remote.response.PageResponse
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject

class EpisodesServiceDataSource @Inject constructor(
    private val episodesApi: EpisodesApi,
    private val episodeMapper: EpisodeResponseToModelMapper
) : EpisodesRemoteDataSource {

    override suspend fun getMultipleEpisodes(episodeIds: List<String>): List<Episode> {
        return episodesApi.getMultipleEpisodes(episodeIds).map(episodeMapper::mapFrom)
    }

    override suspend fun getAllEpisodes(page: Int): PageResponse<EpisodeResponse> {
        return episodesApi.getAllEpisodes(page)
    }
}