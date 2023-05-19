package br.com.lira.rickandmorty.features.characterdetails.data.remote.datasource

import br.com.lira.rickandmorty.main.domain.model.Character

interface CharacterDetailsRemoteDataSource {

    suspend fun getCharacter(characterId: Long?): Character
}