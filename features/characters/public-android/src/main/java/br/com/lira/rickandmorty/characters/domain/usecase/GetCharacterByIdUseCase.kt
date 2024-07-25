package br.com.lira.rickandmorty.characters.domain.usecase

import br.com.lira.rickandmorty.characters.domain.model.Character

interface GetCharacterByIdUseCase {
    suspend operator fun invoke(characterId: Long?): Character
}