package br.com.lira.rickandmorty.characters.data.repository

import br.com.lira.rickandmorty.characters.data.remote.datasource.CharactersRemoteDataSource
import br.com.lira.rickandmorty.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersDefaultRepository @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {

    override suspend fun getAllCharacters() = remoteDataSource.getAllCharacters()
}