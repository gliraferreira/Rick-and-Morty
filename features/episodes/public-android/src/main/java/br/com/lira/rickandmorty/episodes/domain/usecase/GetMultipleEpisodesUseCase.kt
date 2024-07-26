package br.com.lira.rickandmorty.episodes.domain.usecase

import br.com.lira.rickandmorty.episodes.domain.model.Episode

interface GetMultipleEpisodesUseCase {
    suspend operator fun invoke(episodeIds: List<String>): List<Episode>
}