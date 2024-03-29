package br.com.lira.rickandmorty.features.characters.data.remote.datasource

import br.com.lira.rickandmorty.features.characters.data.remote.api.response.CharacterResponse
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.shared.remote.response.PageResponse
import retrofit2.http.Path

interface CharactersRemoteDataSource {

    suspend fun getAllCharacters(
        page: Int,
        filter: CharacterFilter?
    ): PageResponse<CharacterResponse>

    suspend fun getCharacter(characterId: Long?): CharacterResponse

    suspend fun getMultipleCharacters(ids: List<String>): List<CharacterResponse>
}
