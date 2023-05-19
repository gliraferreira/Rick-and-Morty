package br.com.lira.rickandmorty.features.episodes.domain.repository

import br.com.lira.rickandmorty.main.domain.model.Episode

interface EpisodesRepository {

    suspend fun getMultipleEpisodes(episodeIds: List<String>): List<Episode>
}