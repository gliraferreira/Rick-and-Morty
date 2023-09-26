package br.com.lira.rickandmorty.features.episodes.domain.usecase

import br.com.lira.rickandmorty.features.episodes.domain.repository.EpisodesRepository
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject

class GetMultipleEpisodesUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    suspend operator fun invoke(episodeIds: List<String>): List<Episode> {
        return repository.getMultipleEpisodes(episodeIds)
    }
}