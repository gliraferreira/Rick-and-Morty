package br.com.lira.rickandmorty.features.episodes.data.remote.datasource

import br.com.lira.rickandmorty.main.domain.model.Episode

interface EpisodesRemoteDataSource {

    suspend fun getMultipleEpisodes(episodeIds: List<String>): List<Episode>
}