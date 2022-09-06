package br.com.lira.rickandmorty.characters.data.remote.datasource

import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterError
import br.com.lira.rickandmorty.core.Result

interface CharactersRemoteDataSource {

    suspend fun getAllCharacters(): Result<List<Character>, CharacterError>
}