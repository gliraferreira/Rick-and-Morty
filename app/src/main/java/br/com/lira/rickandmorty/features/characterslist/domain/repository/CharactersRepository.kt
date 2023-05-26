package br.com.lira.rickandmorty.features.characterslist.domain.repository

import androidx.paging.PagingData
import br.com.lira.rickandmorty.main.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getAllCharacters(): Flow<PagingData<Character>>

    suspend fun searchCharacters(name: String?): Flow<PagingData<Character>>
}