package br.com.lira.rickandmorty.features.episodes.presentation.model

data class EpisodeDetailsUIModel(
    val id: Long,
    val name: String,
    val episodeNumber: String,
    val airDate: String
)
