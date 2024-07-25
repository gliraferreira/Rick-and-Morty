package br.com.lira.rickandmorty.characters.presentation.model

import androidx.annotation.ColorInt

data class CharacterUIModel(
    val id: Long,
    val name: String,
    @ColorInt val statusColor: Int,
    val gender: String,
    val image: String,
)
