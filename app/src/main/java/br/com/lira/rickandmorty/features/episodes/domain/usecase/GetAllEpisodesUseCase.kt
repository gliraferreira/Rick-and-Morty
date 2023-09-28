package br.com.lira.rickandmorty.features.episodes.domain.usecase

import androidx.paging.PagingData
import br.com.lira.rickandmorty.features.episodes.domain.repository.EpisodesRepository
import br.com.lira.rickandmorty.features.shared.domain.model.Episode
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllEpisodesUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    suspend operator fun invoke(): Flow<PagingData<Episode>> {
        return repository.getAllEpisodes()
    }
}
