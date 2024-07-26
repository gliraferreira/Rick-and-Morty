package br.com.lira.rickandmorty.episodes.data.remote.datasource


import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.lira.rickandmorty.core.data.mapper.UrlMapper
import br.com.lira.rickandmorty.episodes.data.remote.mapper.EpisodeResponseToModelMapper
import br.com.lira.rickandmorty.episodes.domain.model.Episode

class EpisodesPagingDataSource constructor(
    private val remoteDataSource: EpisodesRemoteDataSource,
    private val urlMapper: UrlMapper,
    private val episodeMapper: EpisodeResponseToModelMapper,
) : PagingSource<Int, Episode>() {

    override fun getRefreshKey(state: PagingState<Int, Episode>) = 1

    override suspend fun load(params: LoadParams<Int>) = try {
        val pageNumber = params.key ?: 1
        val data = remoteDataSource.getAllEpisodes(pageNumber)
        val nextPageNumber = urlMapper.mapPage(data.info.next)
        val episodes = data.results.map(episodeMapper::mapFrom)

        LoadResult.Page(
            data = episodes,
            prevKey = null,
            nextKey = nextPageNumber
        )

    } catch (exception: Exception) {
        LoadResult.Error(exception)
    }
}
