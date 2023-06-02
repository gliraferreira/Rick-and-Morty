package br.com.lira.rickandmorty.features.characterslist.presentation.model

import androidx.annotation.IntegerRes

data class CharacterError(
    @IntegerRes val message: Int,
    @IntegerRes val image: Int,
    val isTryAgainVisible: Boolean
)
