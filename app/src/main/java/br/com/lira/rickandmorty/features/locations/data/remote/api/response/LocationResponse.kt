package br.com.lira.rickandmorty.features.locations.data.remote.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationResponse(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)
