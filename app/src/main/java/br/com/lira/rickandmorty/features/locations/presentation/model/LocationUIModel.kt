package br.com.lira.rickandmorty.features.locations.presentation.model

data class LocationUIModel(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String?
)
