package br.com.lira.rickandmorty.features.characterslist.data.remote.datasource


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import br.com.lira.rickandmorty.main.data.remote.mapper.CharacterResponseToModelMapper
import br.com.lira.rickandmorty.main.domain.model.Character

class CharacterPagingDataSource constructor(
    private val name: String? = null,
    private val remoteDataSource: CharactersRemoteDataSource,
    private val urlMapper: UrlMapper,
    private val charactersResponseMapper: CharacterResponseToModelMapper,
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>) = 1

    override suspend fun load(params: LoadParams<Int>) = try {
        val pageNumber = params.key ?: 1
        val data = remoteDataSource.getAllCharacters(pageNumber, name)
        val nextPageNumber = urlMapper.mapPage(data.info.next)
        val characters = data.results.map(charactersResponseMapper::mapFrom)

        LoadResult.Page(
            data = characters,
            prevKey = null,
            nextKey = nextPageNumber
        )

    } catch (exception: Exception) {
        Log.e("AppError", exception.message.orEmpty(), exception)
        LoadResult.Error(exception)
    }
}