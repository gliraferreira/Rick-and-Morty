package br.com.lira.rickandmorty.characters.data.remote.datasource

import br.com.lira.rickandmorty.characters.data.remote.CharacterResponseToModelMapper
import br.com.lira.rickandmorty.characters.data.remote.api.CharactersApi
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.characters.domain.model.CharacterError
import br.com.lira.rickandmorty.core.Result
import javax.inject.Inject

class CharactersServiceDataSource @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersResponseMapper: CharacterResponseToModelMapper
) : CharactersRemoteDataSource {

    override suspend fun getAllCharacters(): Result<Character, CharacterError> {
        TODO("Not yet implemented")
    }
}