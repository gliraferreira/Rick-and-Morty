package br.com.lira.rickandmorty.main.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationResponse,
    val location: LocationResponse,
    val image: String,
    val episode: List<String>
)