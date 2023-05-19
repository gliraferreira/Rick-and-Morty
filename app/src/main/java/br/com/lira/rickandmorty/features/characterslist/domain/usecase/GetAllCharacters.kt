package br.com.lira.rickandmorty.features.characterslist.domain.usecase

import br.com.lira.rickandmorty.features.characterslist.domain.repository.CharactersRepository
import javax.inject.Inject

class GetAllCharacters @Inject constructor(
    private val repository: CharactersRepository
) : GetAllCharactersUseCase {

    override suspend fun invoke() = repository.getAllCharacters()
}