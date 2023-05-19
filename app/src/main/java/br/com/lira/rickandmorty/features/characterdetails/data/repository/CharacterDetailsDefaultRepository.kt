package br.com.lira.rickandmorty.features.characterdetails.data.repository

import br.com.lira.rickandmorty.features.characterdetails.data.remote.datasource.CharacterDetailsRemoteDataSource
import br.com.lira.rickandmorty.features.characterdetails.domain.repository.CharacterDetailsRepository
import br.com.lira.rickandmorty.main.domain.model.Character
import javax.inject.Inject

class CharacterDetailsDefaultRepository @Inject constructor(
    private val remoteDataSource: CharacterDetailsRemoteDataSource
) : CharacterDetailsRepository {

    override suspend fun getCharacter(characterId: Long?): Character {
        return remoteDataSource.getCharacter(characterId)
    }
}