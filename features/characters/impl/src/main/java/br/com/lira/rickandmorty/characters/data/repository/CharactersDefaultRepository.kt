package br.com.lira.rickandmorty.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.lira.rickandmorty.core.data.mapper.UrlMapper
import br.com.lira.rickandmorty.characters.data.remote.datasource.CharacterPagingDataSource
import br.com.lira.rickandmorty.characters.data.remote.datasource.CharactersRemoteDataSource
import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.characters.domain.repository.CharactersRepository
import br.com.lira.rickandmorty.characters.data.remote.mapper.CharacterResponseToModelMapper
import br.com.lira.rickandmorty.characters.data.remote.mapper.CharacterResponseToShortModelMapper
import br.com.lira.rickandmorty.characters.domain.model.Character
import javax.inject.Inject

class CharactersDefaultRepository @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val charactersResponseMapper: CharacterResponseToModelMapper,
    private val urlMapper: UrlMapper,
    private val characterShortMapper: CharacterResponseToShortModelMapper
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

    override suspend fun getMultipleCharacters(
        ids: List<String>
    ) = remoteDataSource.getMultipleCharacters(ids).map(characterShortMapper::mapFrom)
}
