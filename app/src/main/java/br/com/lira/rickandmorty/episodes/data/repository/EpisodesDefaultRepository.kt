package br.com.lira.rickandmorty.episodes.data.repository

import br.com.lira.rickandmorty.episodes.data.remote.datasource.EpisodesRemoteDataSource
import br.com.lira.rickandmorty.episodes.domain.repository.EpisodesRepository
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject

class EpisodesDefaultRepository @Inject constructor(
    private val remoteDataSource: EpisodesRemoteDataSource
) : EpisodesRepository {

    override suspend fun getMultipleEpisodes(episodeIds: List<String>): List<Episode> {
        return remoteDataSource.getMultipleEpisodes(episodeIds)
    }
}