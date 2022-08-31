package br.com.lira.rickandmorty.characters.data.remote.api

import br.com.lira.rickandmorty.characters.data.remote.api.response.CharactersListResponse
import retrofit2.http.GET

interface CharactersApi {

    @GET("character")
    suspend fun getAllCharacters(): CharactersListResponse
}