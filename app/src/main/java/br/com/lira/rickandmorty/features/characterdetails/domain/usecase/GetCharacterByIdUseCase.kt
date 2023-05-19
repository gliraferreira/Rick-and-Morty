package br.com.lira.rickandmorty.features.characterdetails.domain.usecase

import br.com.lira.rickandmorty.main.domain.model.Character

interface GetCharacterByIdUseCase {

    suspend operator fun invoke(characterId: Long?): Character
}