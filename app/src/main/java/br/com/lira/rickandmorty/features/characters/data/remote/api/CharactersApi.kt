package br.com.lira.rickandmorty.features.characters.data.remote.api

import br.com.lira.rickandmorty.main.data.remote.response.PageResponse
import br.com.lira.rickandmorty.features.characters.data.remote.api.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("gender") gender: String? = null
    ): PageResponse<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: Long?
    ): CharacterResponse

    @GET("character/{ids}")
    suspend fun getMultipleCharacters(
        @Path("ids") ids: List<String>
    ): List<CharacterResponse>
}
