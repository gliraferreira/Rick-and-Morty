package br.com.lira.rickandmorty.characterdetails.data.remote.datasource

import br.com.lira.rickandmorty.characterdetails.data.remote.api.CharacterDetailsApi
import br.com.lira.rickandmorty.main.data.remote.mapper.CharacterResponseToModelMapper
import br.com.lira.rickandmorty.main.domain.model.Character
import javax.inject.Inject

class CharacterDetailsServiceDataSource @Inject constructor(
    private val characterDetailsApi: CharacterDetailsApi,
    private val charactersResponseMapper: CharacterResponseToModelMapper
) : CharacterDetailsRemoteDataSource {

    override suspend fun getCharacter(characterId: Long?): Character {
        val response = characterDetailsApi.getCharacterById(characterId)

        return charactersResponseMapper.mapFrom(response)
    }
}