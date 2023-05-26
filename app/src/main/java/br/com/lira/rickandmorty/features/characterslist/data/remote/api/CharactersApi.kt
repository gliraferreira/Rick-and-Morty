package br.com.lira.rickandmorty.features.characterslist.data.remote.api

import br.com.lira.rickandmorty.main.data.remote.response.CharactersListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CharactersApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @Query("name") name: String? = null
    ): CharactersListResponse

    @GET("character")
    suspend fun filterCharacters(
        @Query("page") page: Int,
        @Query("name") name: String,
    ): CharactersListResponse
}