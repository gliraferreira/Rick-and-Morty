package br.com.lira.rickandmorty.features.characterdetails.domain.usecase

import br.com.lira.rickandmorty.features.characterdetails.domain.repository.CharacterDetailsRepository
import br.com.lira.rickandmorty.main.domain.model.Character
import javax.inject.Inject

class GetCharacterById @Inject constructor(
    private val repository: CharacterDetailsRepository
) : GetCharacterByIdUseCase {

    override suspend fun invoke(characterId: Long?): Character {
        return repository.getCharacter(characterId)
    }
}