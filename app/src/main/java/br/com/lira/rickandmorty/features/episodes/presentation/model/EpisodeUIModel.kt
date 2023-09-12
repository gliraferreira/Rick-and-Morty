package br.com.lira.rickandmorty.features.episodes.presentation.model

sealed class EpisodeUIModel {

    data class EpisodeUI(
        val name: String
    ) : EpisodeUIModel()

    data class SeasonHeader(
        val seasonName: String
    ) : EpisodeUIModel()
}
