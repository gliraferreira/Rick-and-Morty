package br.com.lira.rickandmorty.characters.domain.usecase

import br.com.lira.rickandmorty.characters.domain.repository.CharactersRepository
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.usecase.GetCharacterByIdUseCase
import javax.inject.Inject

class GetCharacterById @Inject constructor(
    private val repository: CharactersRepository
) : GetCharacterByIdUseCase {

    override suspend operator fun invoke(characterId: Long?): Character {
        return repository.getCharacter(characterId)
    }
}
