package br.com.lira.rickandmorty.features.characterslist.data.remote.datasource

import br.com.lira.rickandmorty.main.domain.model.Character

interface CharactersRemoteDataSource {

    suspend fun getAllCharacters(): List<Character>
}