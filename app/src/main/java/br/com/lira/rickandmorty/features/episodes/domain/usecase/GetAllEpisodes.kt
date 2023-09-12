package br.com.lira.rickandmorty.features.episodes.domain.usecase

import br.com.lira.rickandmorty.features.episodes.domain.repository.EpisodesRepository
import javax.inject.Inject

class GetAllEpisodes @Inject constructor(
    private val repository: EpisodesRepository
) : GetAllEpisodesUseCase {

    override suspend fun invoke() = repository.getAllEpisodes()
}
