package br.com.lira.rickandmorty.episodes.domain.usecase

import br.com.lira.rickandmorty.episodes.domain.repository.EpisodesRepository
import br.com.lira.rickandmorty.episodes.domain.model.Episode
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    suspend operator fun invoke(episodeId: Long?): Episode {
        return repository.getEpisode(episodeId)
    }
}