package br.com.lira.rickandmorty.features.episodes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.lira.rickandmorty.core.data.mapper.UrlMapper
import br.com.lira.rickandmorty.features.episodes.data.remote.datasource.EpisodesPagingDataSource
import br.com.lira.rickandmorty.features.episodes.data.remote.datasource.EpisodesRemoteDataSource
import br.com.lira.rickandmorty.features.episodes.data.remote.mapper.EpisodeResponseToModelMapper
import br.com.lira.rickandmorty.features.episodes.domain.repository.EpisodesRepository
import br.com.lira.rickandmorty.features.shared.domain.model.Episode
import javax.inject.Inject

class EpisodesDefaultRepository @Inject constructor(
    private val remoteDataSource: EpisodesRemoteDataSource,
    private val urlMapper: UrlMapper,
    private val episodeMapper: EpisodeResponseToModelMapper,
) : EpisodesRepository {

    override suspend fun getMultipleEpisodes(episodeIds: List<String>): List<Episode> {
        return remoteDataSource.getMultipleEpisodes(episodeIds).map(episodeMapper::mapFrom)
    }

    override suspend fun getAllEpisodes() = Pager(PagingConfig(pageSize = 20)) {
        EpisodesPagingDataSource(
            remoteDataSource = remoteDataSource,
            urlMapper = urlMapper,
            episodeMapper = episodeMapper
        )
    }.flow

    override suspend fun getEpisode(episodeId: Long?): Episode {
        return remoteDataSource.getEpisode(episodeId).let(episodeMapper::mapFrom)
    }
}
