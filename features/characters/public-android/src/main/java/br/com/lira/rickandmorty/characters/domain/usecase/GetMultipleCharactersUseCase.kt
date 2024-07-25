package br.com.lira.rickandmorty.characters.domain.usecase

import br.com.lira.rickandmorty.characters.domain.model.CharacterShort

interface GetMultipleCharactersUseCase {
    suspend operator fun invoke(ids: List<String>): List<CharacterShort>
}