package br.com.lira.rickandmorty.characterdetails.data.remote.datasource

import br.com.lira.rickandmorty.main.domain.model.Character

interface CharacterDetailsRemoteDataSource {

    suspend fun getCharacter(characterId: Long?): Character
}