package br.com.lira.rickandmorty.features.episodes.domain.usecase

import br.com.lira.rickandmorty.features.episodes.domain.repository.EpisodesRepository
import javax.inject.Inject

class GetAllEpisodesUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    suspend operator fun invoke() = repository.getAllEpisodes()
}
