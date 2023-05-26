package br.com.lira.rickandmorty.features.characterslist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import br.com.lira.rickandmorty.features.characterslist.data.remote.datasource.CharacterPagingDataSource
import br.com.lira.rickandmorty.features.characterslist.data.remote.datasource.CharactersRemoteDataSource
import br.com.lira.rickandmorty.features.characterslist.domain.repository.CharactersRepository
import br.com.lira.rickandmorty.main.data.remote.mapper.CharacterResponseToModelMapper
import br.com.lira.rickandmorty.main.domain.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersDefaultRepository @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val charactersResponseMapper: CharacterResponseToModelMapper,
    private val urlMapper: UrlMapper
) : CharactersRepository {

    override suspend fun getAllCharacters() = Pager(PagingConfig(pageSize = 20)) {
        CharacterPagingDataSource(
            remoteDataSource = remoteDataSource,
            urlMapper = urlMapper,
            charactersResponseMapper = charactersResponseMapper
        )
    }.flow

    override suspend fun searchCharacters(name: String?) = Pager(PagingConfig(pageSize = 20)) {
        CharacterPagingDataSource(
            name = name,
            remoteDataSource = remoteDataSource,
            urlMapper = urlMapper,
            charactersResponseMapper = charactersResponseMapper
        )
    }.flow
}