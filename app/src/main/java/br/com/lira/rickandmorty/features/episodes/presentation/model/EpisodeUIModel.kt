package br.com.lira.rickandmorty.features.episodes.presentation.model

sealed class EpisodeUIModel {

    data class EpisodeUI(
        val id: Long,
        val name: String,
        val formattedSeasonNumber: String,
        val formattedEpisodeNumber: String,
        val airDate: String
    ) : EpisodeUIModel()

    data class Header(
        val title: String
    ) : EpisodeUIModel()
}
