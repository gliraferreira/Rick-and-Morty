package br.com.lira.rickandmorty.main.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationResponse(
    val name: String,
    val url: String
)