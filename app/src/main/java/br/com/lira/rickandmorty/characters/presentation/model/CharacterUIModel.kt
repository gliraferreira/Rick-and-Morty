package br.com.lira.rickandmorty.characters.presentation.model

import androidx.annotation.IntegerRes

data class CharacterUIModel(
    val id: Long,
    val name: String,
    @IntegerRes val statusText: Int,
    @IntegerRes val statusColor: Int,
    val species: String,
    val type: String,
    @IntegerRes val gender: Int,
    val image: String,
)
