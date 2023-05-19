package br.com.lira.rickandmorty.features.characterdetails.presentation.model

import androidx.annotation.IntegerRes

data class CharacterDetailsUIModel(
    val id: Long,
    val name: String,
    @IntegerRes val statusText: Int?,
    @IntegerRes val statusColor: Int?,
    val species: String,
    @IntegerRes val gender: Int?,
    val image: String,
    val lastLocation: String,
)
