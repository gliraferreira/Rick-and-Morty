package br.com.lira.rickandmorty.features.locations.data.remote.datasource

import br.com.lira.rickandmorty.features.locations.data.remote.api.response.LocationResponse
import br.com.lira.rickandmorty.core.data.response.PageResponse

interface LocationsRemoteDataSource {

    suspend fun getAllLocations(page: Int): PageResponse<LocationResponse>

    suspend fun getLocationById(locationId: Long?): LocationResponse
}
