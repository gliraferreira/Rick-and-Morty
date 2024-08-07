package br.com.lira.rickandmorty.characters.domain.repository

import androidx.paging.PagingData
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterShort
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getAllCharacters(filter: CharacterFilter?): Flow<PagingData<Character>>

    suspend fun getCharacter(characterId: Long?): Character

    suspend fun getMultipleCharacters(ids: List<String>): List<CharacterShort>
}
