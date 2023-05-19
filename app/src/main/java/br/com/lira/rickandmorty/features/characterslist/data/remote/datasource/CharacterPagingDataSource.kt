package br.com.lira.rickandmorty.features.characterslist.data.remote.datasource


import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.lira.rickandmorty.features.characterslist.data.remote.api.CharactersApi
import br.com.lira.rickandmorty.main.data.remote.mapper.CharacterResponseToModelMapper
import br.com.lira.rickandmorty.main.domain.model.Character
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import javax.inject.Inject

class CharacterPagingDataSource @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersResponseMapper: CharacterResponseToModelMapper,
    private val urlMapper: UrlMapper
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>) = 1

    override suspend fun load(params: LoadParams<Int>) = try {
        val pageNumber = params.key ?: 1
        val response = charactersApi.getAllCharacters(pageNumber)
        val data = response.results.map(charactersResponseMapper::mapFrom)
        val nextPageNumber = urlMapper.mapPage(response.info.next)

        LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = nextPageNumber
        )

    } catch (exception: Exception) {
        LoadResult.Error(exception)
    }
}