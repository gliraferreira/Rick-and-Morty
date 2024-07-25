package br.com.lira.rickandmorty.locations.domain.model

data class Location(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residentIds: List<String>
)
