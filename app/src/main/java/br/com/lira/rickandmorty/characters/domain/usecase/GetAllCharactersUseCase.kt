package br.com.lira.rickandmorty.characters.domain.usecase

import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterError
import br.com.lira.rickandmorty.core.Result

interface GetAllCharactersUseCase {

    suspend operator fun invoke(): Result<List<Character>, CharacterError>
}