package br.com.lira.rickandmorty.characters.domain.usecase

import br.com.lira.rickandmorty.characters.domain.usecase.GetMultipleCharactersUseCase
import br.com.lira.rickandmorty.characters.domain.repository.CharactersRepository
import br.com.lira.rickandmorty.characters.domain.model.CharacterShort
import javax.inject.Inject

class GetMultipleCharacters @Inject constructor(
    private val repository: CharactersRepository
) : GetMultipleCharactersUseCase {

    override suspend operator fun invoke(ids: List<String>): List<CharacterShort> {
        return repository.getMultipleCharacters(ids)
    }
}
