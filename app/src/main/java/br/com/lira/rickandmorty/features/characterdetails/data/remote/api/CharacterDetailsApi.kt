package br.com.lira.rickandmorty.features.characterdetails.data.remote.api

import br.com.lira.rickandmorty.main.data.remote.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailsApi {

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: Long?
    ): CharacterResponse
}