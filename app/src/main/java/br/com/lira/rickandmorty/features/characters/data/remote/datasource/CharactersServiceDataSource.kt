package br.com.lira.rickandmorty.features.characters.data.remote.datasource

import br.com.lira.rickandmorty.features.characters.data.remote.api.CharactersApi
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import javax.inject.Inject

class CharactersServiceDataSource @Inject constructor(
    private val charactersApi: CharactersApi
) : CharactersRemoteDataSource {

    override suspend fun getAllCharacters(
        page: Int,
        filter: CharacterFilter?
    ) = charactersApi.getAllCharacters(
        page = page,
        name = filter?.name,
        status = filter?.status?.name?.lowercase(),
        gender = filter?.gender?.name?.lowercase()
    )

    override suspend fun getCharacter(characterId: Long?) =
        charactersApi.getCharacterById(characterId)

    override suspend fun getMultipleCharacters(ids: List<String>) =
        charactersApi.getMultipleCharacters(ids)
}
