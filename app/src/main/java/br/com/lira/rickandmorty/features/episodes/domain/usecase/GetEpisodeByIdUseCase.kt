package br.com.lira.rickandmorty.features.episodes.domain.usecase

import br.com.lira.rickandmorty.features.episodes.domain.repository.EpisodesRepository
import br.com.lira.rickandmorty.main.domain.model.Episode
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    suspend operator fun invoke(episodeId: Long?): Episode {
        return repository.getEpisode(episodeId)
    }
}