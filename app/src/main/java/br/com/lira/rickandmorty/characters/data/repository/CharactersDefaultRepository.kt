package br.com.lira.rickandmorty.characters.data.repository

import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterError
import br.com.lira.rickandmorty.characters.domain.repository.CharactersRepository
import br.com.lira.rickandmorty.core.Result

class CharactersDefaultRepository : CharactersRepository {

    override suspend fun getAllCharacters(): Result<Character, CharacterError> {
        TODO("Not yet implemented")
    }
}