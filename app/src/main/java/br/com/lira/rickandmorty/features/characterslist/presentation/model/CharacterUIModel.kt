package br.com.lira.rickandmorty.features.characterslist.presentation.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class CharacterUIModel(
    val id: Long,
    val name: String,
    @StringRes val statusText: Int,
    @ColorRes val statusColor: Int,
    val species: String,
    val type: String,
    @StringRes val gender: Int,
    val image: String,
)
