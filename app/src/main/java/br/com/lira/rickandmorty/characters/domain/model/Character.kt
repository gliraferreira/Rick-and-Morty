package br.com.lira.rickandmorty.characters.domain.model

import java.util.Date

data class Character(
    val id: Long,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: CharacterGender,
    val origin: CharacterLocation,
    val location: CharacterLocation,
    val image: String,
    val episodeIds: List<String>
)
