package br.com.lira.rickandmorty.characters.data.remote.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationResponse(
    val name: String,
    val url: String
)