package br.com.lira.rickandmorty.characters.presentation.model

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class CharacterDetailsUIModel(
    val id: Long,
    val name: String,
    val statusText: String,
    @ColorInt val statusColor: Int,
    val species: String,
    val gender: String,
    val image: String,
    val lastLocation: String,
)
