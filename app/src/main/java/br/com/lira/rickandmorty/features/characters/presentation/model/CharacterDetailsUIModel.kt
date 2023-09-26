package br.com.lira.rickandmorty.features.characters.presentation.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class CharacterDetailsUIModel(
    val id: Long,
    val name: String,
    @StringRes val statusText: Int,
    @ColorRes val statusColor: Int,
    val species: String,
    @StringRes val gender: Int,
    val image: String,
    val lastLocation: String,
)
