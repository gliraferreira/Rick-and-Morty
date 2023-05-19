package br.com.lira.rickandmorty.features.characterslist.data.remote.datasource

import br.com.lira.rickandmorty.features.characterslist.data.remote.api.CharactersApi
import br.com.lira.rickandmorty.main.data.remote.mapper.CharacterResponseToModelMapper
import br.com.lira.rickandmorty.main.domain.model.Character
import javax.inject.Inject

class CharactersServiceDataSource @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersResponseMapper: CharacterResponseToModelMapper
) : CharactersRemoteDataSource {

    override suspend fun getAllCharacters(): List<Character> {
        val response = charactersApi.getAllCharacters(1)
        return response.results.map(charactersResponseMapper::mapFrom)
    }
}