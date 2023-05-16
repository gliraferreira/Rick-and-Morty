package br.com.lira.rickandmorty.characters.domain.repository

import androidx.paging.PagingData
import br.com.lira.rickandmorty.main.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getAllCharacters(): Flow<PagingData<Character>>
}