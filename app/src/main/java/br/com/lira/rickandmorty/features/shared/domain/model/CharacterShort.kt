package br.com.lira.rickandmorty.features.shared.domain.model

data class CharacterShort(
    val id: Long,
    val name: String,
    val status: CharacterStatus,
    val gender: CharacterGender,
    val image: String
)
