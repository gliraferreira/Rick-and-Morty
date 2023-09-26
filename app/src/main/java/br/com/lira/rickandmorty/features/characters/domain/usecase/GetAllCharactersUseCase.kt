package br.com.lira.rickandmorty.features.characters.domain.usecase

import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke(filter: CharacterFilter?) = repository.getAllCharacters(filter)
}
