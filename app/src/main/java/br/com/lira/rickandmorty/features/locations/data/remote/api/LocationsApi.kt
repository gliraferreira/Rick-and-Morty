package br.com.lira.rickandmorty.features.locations.data.remote.api

import br.com.lira.rickandmorty.features.locations.data.remote.api.response.LocationResponse
import br.com.lira.rickandmorty.features.shared.remote.response.PageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsApi {

    @GET("episode")
    suspend fun getAllLocations(
        @Query("page") page: Int
    ): PageResponse<LocationResponse>
}
