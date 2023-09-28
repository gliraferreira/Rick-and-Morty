package br.com.lira.rickandmorty.features.characters.domain.usecase

import br.com.lira.rickandmorty.features.characters.domain.repository.CharactersRepository
import br.com.lira.rickandmorty.features.characters.domain.model.Character
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke(characterId: Long?): Character {
        return repository.getCharacter(characterId)
    }
}
