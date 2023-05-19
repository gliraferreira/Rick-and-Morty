package br.com.lira.rickandmorty.features.characterslist.data.remote.api

import br.com.lira.rickandmorty.main.data.remote.response.CharactersListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): CharactersListResponse
}