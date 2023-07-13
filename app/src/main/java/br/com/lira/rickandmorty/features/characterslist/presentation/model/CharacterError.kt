package br.com.lira.rickandmorty.features.characterslist.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CharacterError(
    @StringRes val message: Int,
    @DrawableRes val image: Int,
    val isTryAgainVisible: Boolean
)
