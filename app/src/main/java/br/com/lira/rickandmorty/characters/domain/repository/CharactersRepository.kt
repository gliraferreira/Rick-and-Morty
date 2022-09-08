package br.com.lira.rickandmorty.characters.domain.repository

import androidx.paging.PagingData
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterError
import br.com.lira.rickandmorty.core.Result
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getAllCharacters(): Flow<PagingData<Character>>
}