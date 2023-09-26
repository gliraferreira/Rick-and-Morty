package br.com.lira.rickandmorty.features.characters.data.remote.datasource

import br.com.lira.rickandmorty.features.characters.data.remote.response.CharacterResponse
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.main.data.remote.response.PageResponse

interface CharactersRemoteDataSource {

    suspend fun getAllCharacters(
        page: Int,
        filter: CharacterFilter?
    ): PageResponse<CharacterResponse>

    suspend fun getCharacter(characterId: Long?): CharacterResponse
}
