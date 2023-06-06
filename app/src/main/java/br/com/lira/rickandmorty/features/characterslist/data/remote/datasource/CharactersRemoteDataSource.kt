package br.com.lira.rickandmorty.features.characterslist.data.remote.datasource

import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.main.data.remote.response.CharactersListResponse

interface CharactersRemoteDataSource {

    suspend fun getAllCharacters(page: Int, filter: CharacterFilter?): CharactersListResponse
}