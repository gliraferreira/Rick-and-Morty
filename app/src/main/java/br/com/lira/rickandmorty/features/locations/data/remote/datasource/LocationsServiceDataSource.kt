package br.com.lira.rickandmorty.features.locations.data.remote.datasource

import br.com.lira.rickandmorty.features.locations.data.remote.api.LocationsApi
import br.com.lira.rickandmorty.features.locations.data.remote.api.response.LocationResponse
import br.com.lira.rickandmorty.features.shared.remote.response.PageResponse
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
