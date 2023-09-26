package br.com.lira.rickandmorty.features.characters.data.remote.datasource


import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characters.data.mapper.CharacterResponseToModelMapper
import br.com.lira.rickandmorty.main.domain.model.Character

class CharacterPagingDataSource constructor(
    private val filter: CharacterFilter? = null,
    private val remoteDataSource: CharactersRemoteDataSource,
    private val urlMapper: UrlMapper,
    private val charactersResponseMapper: CharacterResponseToModelMapper,
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>) = 1

    override suspend fun load(params: LoadParams<Int>) = try {
        val pageNumber = params.key ?: 1
        val data = remoteDataSource.getAllCharacters(pageNumber, filter)
        val nextPageNumber = urlMapper.mapPage(data.info.next)
        val characters = data.results.map(charactersResponseMapper::mapFrom)

        LoadResult.Page(
            data = characters,
            prevKey = null,
            nextKey = nextPageNumber
        )

    } catch (exception: Exception) {
        LoadResult.Error(exception)
    }
}