package br.com.lira.rickandmorty.main.domain.model

data class CharacterShort(
    val id: Long,
    val name: String,
    val status: CharacterStatus,
    val gender: CharacterGender,
    val image: String
)
