package br.com.lira.rickandmorty.features.characterslist.data.remote.datasource

import br.com.lira.rickandmorty.features.characterslist.data.remote.api.CharactersApi
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.main.data.remote.mapper.CharacterResponseToModelMapper
import br.com.lira.rickandmorty.main.domain.model.Character
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
}
