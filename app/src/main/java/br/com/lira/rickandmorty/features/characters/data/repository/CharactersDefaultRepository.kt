package br.com.lira.rickandmorty.features.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import br.com.lira.rickandmorty.features.characters.data.remote.datasource.CharacterPagingDataSource
import br.com.lira.rickandmorty.features.characters.data.remote.datasource.CharactersRemoteDataSource
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characters.domain.repository.CharactersRepository
import br.com.lira.rickandmorty.features.characters.data.mapper.CharacterResponseToModelMapper
import br.com.lira.rickandmorty.main.domain.model.Character
import javax.inject.Inject

class CharactersDefaultRepository @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val charactersResponseMapper: CharacterResponseToModelMapper,
    private val urlMapper: UrlMapper,
) : CharactersRepository {

    override suspend fun getAllCharacters(
        filter: CharacterFilter?
    ) = Pager(PagingConfig(pageSize = 20)) {
        CharacterPagingDataSource(
            filter = filter,
            remoteDataSource = remoteDataSource,
            urlMapper = urlMapper,
            charactersResponseMapper = charactersResponseMapper
        )
    }.flow

    override suspend fun getCharacter(characterId: Long?): Character {
        val response = remoteDataSource.getCharacter(characterId)

        return charactersResponseMapper.mapFrom(response)
    }
}
