package br.com.lira.rickandmorty.locations.presentation.model

data class LocationUIModel(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String?
)
