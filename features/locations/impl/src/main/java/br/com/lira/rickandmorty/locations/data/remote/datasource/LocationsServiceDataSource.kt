package br.com.lira.rickandmorty.locations.data.remote.datasource

import br.com.lira.rickandmorty.locations.data.remote.api.LocationsApi
import br.com.lira.rickandmorty.locations.data.remote.api.response.LocationResponse
import br.com.lira.rickandmorty.core.data.response.PageResponse
import javax.inject.Inject

class LocationsServiceDataSource @Inject constructor(
    private val locationsApi: LocationsApi
) : LocationsRemoteDataSource {

    override suspend fun getAllLocations(page: Int): PageResponse<LocationResponse> {
        return locationsApi.getAllLocations(page)
    }

    override suspend fun getLocationById(locationId: Long?): LocationResponse {
        return locationsApi.getLocationById(locationId)
    }
}
