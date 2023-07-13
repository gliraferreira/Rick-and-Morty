package br.com.lira.rickandmorty.main.domain.model

data class Episode(
    val id: Long,
    val name: String,
    val airDate: String,
    val episodeNumber: String,
    val characterIds: List<String>,
)