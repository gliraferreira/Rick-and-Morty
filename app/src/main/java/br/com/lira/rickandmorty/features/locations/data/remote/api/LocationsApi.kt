package br.com.lira.rickandmorty.features.locations.data.remote.api

import br.com.lira.rickandmorty.features.locations.data.remote.api.response.LocationResponse
import br.com.lira.rickandmorty.core.data.response.PageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationsApi {

    @GET("location")
    suspend fun getAllLocations(
        @Query("page") page: Int
    ): PageResponse<LocationResponse>

    @GET("location/{id}")
    suspend fun getLocationById(
        @Path("id") locationId: Long?
    ): LocationResponse
}
