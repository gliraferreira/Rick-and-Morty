package br.com.lira.rickandmorty.characters.domain.repository

import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterError
import br.com.lira.rickandmorty.core.Result

interface CharactersRepository {

    suspend fun getAllCharacters(): Result<Character, CharacterError>
}