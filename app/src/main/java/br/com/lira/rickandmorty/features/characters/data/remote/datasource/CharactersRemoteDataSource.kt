package br.com.lira.rickandmorty.features.characters.data.remote.datasource

import br.com.lira.rickandmorty.features.characters.data.remote.api.response.CharacterResponse
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.core.data.response.PageResponse

interface CharactersRemoteDataSource {

    suspend fun getAllCharacters(
        page: Int,
        filter: CharacterFilter?
    ): PageResponse<CharacterResponse>

    suspend fun getCharacter(characterId: Long?): CharacterResponse

    suspend fun getMultipleCharacters(ids: List<String>): List<CharacterResponse>
}
