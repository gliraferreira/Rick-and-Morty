package br.com.lira.rickandmorty.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.lira.rickandmorty.characters.data.remote.datasource.CharacterPagingDataSource
import br.com.lira.rickandmorty.characters.data.remote.datasource.CharactersRemoteDataSource
import br.com.lira.rickandmorty.characters.domain.repository.CharactersRepository
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