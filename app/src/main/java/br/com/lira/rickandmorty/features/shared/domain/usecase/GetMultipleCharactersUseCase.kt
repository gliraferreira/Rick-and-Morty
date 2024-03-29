package br.com.lira.rickandmorty.features.shared.domain.usecase

import br.com.lira.rickandmorty.features.characters.domain.repository.CharactersRepository
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterShort
import javax.inject.Inject

class GetMultipleCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke(ids: List<String>): List<CharacterShort> {
        return repository.getMultipleCharacters(ids)
    }
}
