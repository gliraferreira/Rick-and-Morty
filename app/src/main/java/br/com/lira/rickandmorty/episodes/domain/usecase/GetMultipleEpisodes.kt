package br.com.lira.rickandmorty.episodes.domain.usecase

import br.com.lira.rickandmorty.episodes.domain.repository.EpisodesRepository
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject

class GetMultipleEpisodes @Inject constructor(
    private val repository: EpisodesRepository
) : GetMultipleEpisodesUseCase {

    override suspend fun invoke(episodeIds: List<String>): List<Episode> {
        return repository.getMultipleEpisodes(episodeIds)
    }
}