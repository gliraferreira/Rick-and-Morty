package br.com.lira.rickandmorty.features.characterslist.domain.usecase

import androidx.paging.PagingData
import br.com.lira.rickandmorty.features.characterslist.domain.repository.CharactersRepository
import br.com.lira.rickandmorty.main.domain.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCharacters @Inject constructor(
    private val repository: CharactersRepository
) : SearchCharactersUseCase {

    override suspend fun invoke(name: String?): Flow<PagingData<Character>> {
        return repository.searchCharacters(name)
    }
}