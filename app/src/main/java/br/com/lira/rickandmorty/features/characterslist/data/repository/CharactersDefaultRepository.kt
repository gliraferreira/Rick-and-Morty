package br.com.lira.rickandmorty.features.characterslist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.lira.rickandmorty.features.characterslist.data.remote.datasource.CharacterPagingDataSource
import br.com.lira.rickandmorty.features.characterslist.data.remote.datasource.CharactersRemoteDataSource
import br.com.lira.rickandmorty.features.characterslist.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersDefaultRepository @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val pagingDataSource: CharacterPagingDataSource
) : CharactersRepository {

    override suspend fun getAllCharacters() = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { pagingDataSource }
    ).flow
}