package br.com.lira.rickandmorty.features.characters.presentation.model

import androidx.annotation.ColorInt

data class CharacterUIModel(
    val id: Long,
    val name: String,
    val statusText: String,
    @ColorInt val statusColor: Int,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
)
