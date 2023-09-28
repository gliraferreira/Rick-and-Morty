package br.com.lira.rickandmorty.features.characters.data.remote.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterLocationResponse(
    val name: String,
    val url: String
)