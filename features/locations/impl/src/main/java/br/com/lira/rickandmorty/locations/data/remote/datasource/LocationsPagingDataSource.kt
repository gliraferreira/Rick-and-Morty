package br.com.lira.rickandmorty.locations.data.remote.datasource


import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.lira.rickandmorty.core.data.mapper.UrlMapper
import br.com.lira.rickandmorty.locations.data.remote.mapper.LocationResponseToShortModelMapper
import br.com.lira.rickandmorty.locations.domain.model.LocationShort

class LocationsPagingDataSource constructor(
    private val remoteDataSource: LocationsRemoteDataSource,
    private val urlMapper: UrlMapper,
    private val locationMapper: LocationResponseToShortModelMapper
) : PagingSource<Int, LocationShort>() {

    override fun getRefreshKey(state: PagingState<Int, LocationShort>) = 1

    override suspend fun load(params: LoadParams<Int>) = try {
        val pageNumber = params.key ?: 1
        val data = remoteDataSource.getAllLocations(pageNumber)
        val nextPageNumber = urlMapper.mapPage(data.info.next)
        val episodes = data.results.map(locationMapper::mapFrom)

        LoadResult.Page(
            data = episodes,
            prevKey = null,
            nextKey = nextPageNumber
        )

    } catch (exception: Exception) {
        LoadResult.Error(exception)
    }
}
