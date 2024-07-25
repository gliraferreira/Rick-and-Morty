package br.com.lira.rickandmorty.characters.domain.usecase

import androidx.paging.PagingData
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import kotlinx.coroutines.flow.Flow

interface GetAllCharactersUseCase {
    suspend operator fun invoke(filter: CharacterFilter?): Flow<PagingData<Character>>
}