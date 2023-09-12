package br.com.lira.rickandmorty.features.episodes.data.remote.datasource

import br.com.lira.rickandmorty.features.episodes.data.remote.api.response.EpisodeResponse
import br.com.lira.rickandmorty.main.data.remote.response.PageResponse
import br.com.lira.rickandmorty.main.domain.model.Episode

interface EpisodesRemoteDataSource {

    suspend fun getMultipleEpisodes(episodeIds: List<String>): List<Episode>

    suspend fun getAllEpisodes(page: Int): PageResponse<EpisodeResponse>
}