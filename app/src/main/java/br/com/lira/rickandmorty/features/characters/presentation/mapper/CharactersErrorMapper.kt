package br.com.lira.rickandmorty.features.characters.presentation.mapper

import br.com.lira.rickandmorty.R
import br.lira.core.presentation.ResourceProvider
import br.com.lira.rickandmorty.features.shared.presentation.model.GenericUIError
import retrofit2.HttpException
import javax.inject.Inject

private const val HTTP_NOT_FOUND = 404

class CharactersErrorMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(error: Throwable): GenericUIError = when (error) {
        is HttpException -> mapHttpException(error)
        else -> mapGenericError()
    }

    private fun mapHttpException(error: HttpException) = when (error.code()) {
        HTTP_NOT_FOUND -> mapNotFoundError()
        else -> mapGenericError()
    }

    private fun mapNotFoundError() = GenericUIError(
        message = resourceProvider.getString(R.string.characters_search_empty_text),
        image = resourceProvider.getDrawable(R.drawable.ic_empty),
        isTryAgainVisible = false
    )

    private fun mapGenericError() = GenericUIError(
        message = resourceProvider.getString(R.string.common_error_title),
        image = resourceProvider.getDrawable(R.drawable.ic_error),
        isTryAgainVisible = true
    )
}
