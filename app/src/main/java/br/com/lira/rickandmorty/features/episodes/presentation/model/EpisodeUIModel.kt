package br.com.lira.rickandmorty.features.episodes.presentation.model

sealed class EpisodeUIModel {

    data class Episode(
        val id: Long,
        val name: String,
        val seasonNumber: String,
        val episodeNumber: String
    ) : EpisodeUIModel()

    data class Header(
        val title: String
    ) : EpisodeUIModel()
}
