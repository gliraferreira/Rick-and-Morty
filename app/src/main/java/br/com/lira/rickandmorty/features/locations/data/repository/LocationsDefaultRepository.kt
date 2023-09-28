package br.com.lira.rickandmorty.features.locations.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import br.com.lira.rickandmorty.features.episodes.data.remote.datasource.EpisodesPagingDataSource
import br.com.lira.rickandmorty.features.locations.data.remote.datasource.LocationsPagingDataSource
import br.com.lira.rickandmorty.features.locations.data.remote.datasource.LocationsRemoteDataSource
import br.com.lira.rickandmorty.features.locations.data.remote.mapper.LocationResponseToShortModelMapper
import br.com.lira.rickandmorty.features.locations.domain.repository.LocationsRepository
import br.com.lira.rickandmorty.features.shared.domain.model.LocationShort
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LocationsDefaultRepository @Inject constructor(
    private val remoteDataSource: LocationsRemoteDataSource,
    private val urlMapper: UrlMapper,
    private val locationMapper: LocationResponseToShortModelMapper
) : LocationsRepository {

    override suspend fun getAllLocations() = Pager(PagingConfig(pageSize = 20)) {
        LocationsPagingDataSource(
            remoteDataSource = remoteDataSource,
            urlMapper = urlMapper,
            locationMapper = locationMapper
        )
    }.flow
}
