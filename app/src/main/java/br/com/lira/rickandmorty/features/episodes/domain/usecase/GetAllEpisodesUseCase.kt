package br.com.lira.rickandmorty.features.episodes.domain.usecase

import androidx.paging.PagingData
import br.com.lira.rickandmorty.main.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface GetAllEpisodesUseCase {

    suspend operator fun invoke(): Flow<PagingData<Episode>>
}
