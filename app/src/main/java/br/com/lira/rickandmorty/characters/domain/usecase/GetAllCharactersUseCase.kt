package br.com.lira.rickandmorty.characters.domain.usecase

import androidx.paging.PagingData
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterError
import br.com.lira.rickandmorty.core.Result
import kotlinx.coroutines.flow.Flow

interface GetAllCharactersUseCase {

    suspend operator fun invoke(): Flow<PagingData<Character>>
}