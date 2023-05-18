package br.com.lira.rickandmorty.main.domain.model

import java.util.Date

data class Episode(
    val id: Long,
    val name: String,
    val airDate: String,
    val episode: String,
    val characterIds: List<String>,
)