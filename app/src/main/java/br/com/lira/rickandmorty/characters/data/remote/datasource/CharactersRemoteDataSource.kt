package br.com.lira.rickandmorty.characters.data.remote.datasource

import br.com.lira.rickandmorty.main.domain.model.Character

interface CharactersRemoteDataSource {

    suspend fun getAllCharacters(): List<Character>
}