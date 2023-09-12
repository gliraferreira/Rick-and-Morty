package br.com.lira.rickandmorty.main.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersListResponse(
    val info: PageInfoResponse,
    val results: List<CharacterResponse>
)
