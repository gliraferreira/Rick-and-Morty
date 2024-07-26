package br.com.lira.rickandmorty.characters.domain.usecase

import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.characters.domain.usecase.GetAllCharactersUseCase
import br.com.lira.rickandmorty.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class GetAllCharacters @Inject constructor(
    private val repository: CharactersRepository
) : GetAllCharactersUseCase {

    override suspend operator fun invoke(
        filter: CharacterFilter?
    ) = repository.getAllCharacters(filter)
}
