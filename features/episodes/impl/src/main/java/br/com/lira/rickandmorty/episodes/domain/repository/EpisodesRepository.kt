package br.com.lira.rickandmorty.episodes.domain.repository

import androidx.paging.PagingData
import br.com.lira.rickandmorty.episodes.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    suspend fun getMultipleEpisodes(episodeIds: List<String>): List<Episode>

    suspend fun getAllEpisodes(): Flow<PagingData<Episode>>

    suspend fun getEpisode(episodeId: Long?): Episode
}