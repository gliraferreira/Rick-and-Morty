package br.com.lira.rickandmorty.features.characterslist.domain.usecase

import androidx.paging.PagingData
import br.com.lira.rickandmorty.main.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface SearchCharactersUseCase {

    suspend operator fun invoke(name: String?): Flow<PagingData<Character>>
}