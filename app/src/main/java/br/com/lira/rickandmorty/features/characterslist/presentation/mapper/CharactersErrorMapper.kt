package br.com.lira.rickandmorty.features.characterslist.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.ResourceProvider
import br.com.lira.rickandmorty.main.presentation.model.GenericError
import retrofit2.HttpException
import javax.inject.Inject

class CharactersErrorMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(error: Throwable): GenericError = when (error) {
        is HttpException -> {
            mapHttpException(error)
        }
        else -> {
            GenericError(
                message = resourceProvider.getString(R.string.common_error_title),
                image = resourceProvider.getDrawable(R.drawable.ic_error),
                isTryAgainVisible = true
            )
        }
    }

    private fun mapHttpException(error: HttpException) = if (error.code() == 404) {
        GenericError(
            message = resourceProvider.getString(R.string.characters_search_empty_text),
            image = resourceProvider.getDrawable(R.drawable.ic_empty),
            isTryAgainVisible = false
        )
    } else {
        GenericError(
            message = resourceProvider.getString(R.string.common_error_title),
            image = resourceProvider.getDrawable(R.drawable.ic_error),
            isTryAgainVisible = true
        )
    }
}
