package br.com.lira.rickandmorty.features.characterslist.domain.repository

import androidx.paging.PagingData
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.main.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getAllCharacters(filter: CharacterFilter?): Flow<PagingData<Character>>
}
