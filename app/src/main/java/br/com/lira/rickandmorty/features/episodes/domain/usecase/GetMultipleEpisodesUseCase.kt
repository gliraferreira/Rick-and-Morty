package br.com.lira.rickandmorty.features.episodes.domain.usecase

import br.com.lira.rickandmorty.main.domain.model.Episode

interface GetMultipleEpisodesUseCase {

    suspend operator fun invoke(episodeIds: List<String>): List<Episode>
}