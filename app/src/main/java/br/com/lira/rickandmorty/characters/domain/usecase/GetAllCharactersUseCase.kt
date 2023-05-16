package br.com.lira.rickandmorty.characters.domain.usecase

import androidx.paging.PagingData
import br.com.lira.rickandmorty.main.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface GetAllCharactersUseCase {

    suspend operator fun invoke(): Flow<PagingData<Character>>
}