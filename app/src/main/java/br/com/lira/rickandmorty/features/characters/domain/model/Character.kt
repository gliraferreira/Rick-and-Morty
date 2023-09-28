package br.com.lira.rickandmorty.features.characters.domain.model

import br.com.lira.rickandmorty.features.shared.domain.model.CharacterGender
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterLocation
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterStatus

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
