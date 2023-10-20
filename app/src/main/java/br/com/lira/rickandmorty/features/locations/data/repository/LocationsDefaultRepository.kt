package br.com.lira.rickandmorty.features.locations.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import br.com.lira.rickandmorty.features.locations.data.remote.datasource.LocationsPagingDataSource
import br.com.lira.rickandmorty.features.locations.data.remote.datasource.LocationsRemoteDataSource
import br.com.lira.rickandmorty.features.locations.data.remote.mapper.LocationResponseToModelMapper
import br.com.lira.rickandmorty.features.locations.data.remote.mapper.LocationResponseToShortModelMapper
import br.com.lira.rickandmorty.features.locations.domain.model.Location
import br.com.lira.rickandmorty.features.locations.domain.repository.LocationsRepository
import javax.inject.Inject

class LocationsDefaultRepository @Inject constructor(
    private val remoteDataSource: LocationsRemoteDataSource,
    private val urlMapper: UrlMapper,
    private val locationShortMapper: LocationResponseToShortModelMapper,
    private val locationMapper: LocationResponseToModelMapper
) : LocationsRepository {

    override suspend fun getAllLocations() = Pager(PagingConfig(pageSize = 20)) {
        LocationsPagingDataSource(
            remoteDataSource = remoteDataSource,
            urlMapper = urlMapper,
            locationMapper = locationShortMapper
        )
    }.flow

    override suspend fun getLocationById(locationId: Long?): Location {
        return remoteDataSource.getLocationById(locationId).let(locationMapper::mapFrom)
    }
}
