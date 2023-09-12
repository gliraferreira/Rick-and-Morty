package br.com.lira.rickandmorty.features.characterslist.data.remote.datasource

import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.main.data.remote.response.PageResponse
import br.com.lira.rickandmorty.main.data.remote.response.CharacterResponse

interface CharactersRemoteDataSource {

    suspend fun getAllCharacters(page: Int, filter: CharacterFilter?): PageResponse<CharacterResponse>
}