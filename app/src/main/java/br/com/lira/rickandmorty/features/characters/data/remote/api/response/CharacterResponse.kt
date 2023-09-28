package br.com.lira.rickandmorty.features.characters.data.remote.api.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterLocationResponse,
    val location: CharacterLocationResponse,
    val image: String,
    val episode: List<String>
)
