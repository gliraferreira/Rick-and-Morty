package br.com.lira.rickandmorty.features.episodes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import br.com.lira.rickandmorty.features.episodes.data.remote.datasource.EpisodesPagingDataSource
import br.com.lira.rickandmorty.features.episodes.data.remote.datasource.EpisodesRemoteDataSource
import br.com.lira.rickandmorty.features.episodes.data.remote.mapper.EpisodeResponseToModelMapper
import br.com.lira.rickandmorty.features.episodes.domain.repository.EpisodesRepository
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class EpisodesDefaultRepository @Inject constructor(
    private val remoteDataSource: EpisodesRemoteDataSource,
    private val urlMapper: UrlMapper,
    private val episodeMapper: EpisodeResponseToModelMapper,
) : EpisodesRepository {

    override suspend fun getMultipleEpisodes(episodeIds: List<String>): List<Episode> {
        return remoteDataSource.getMultipleEpisodes(episodeIds)
    }

    override suspend fun getAllEpisodes() = Pager(PagingConfig(pageSize = 20)) {
        EpisodesPagingDataSource(
            remoteDataSource = remoteDataSource,
            urlMapper = urlMapper,
            episodeMapper = episodeMapper
        )
    }.flow
}
