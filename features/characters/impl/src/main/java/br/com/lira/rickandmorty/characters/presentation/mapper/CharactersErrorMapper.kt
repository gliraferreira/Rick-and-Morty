package br.com.lira.rickandmorty.characters.presentation.mapper

import br.com.lira.rickandmorty.characters.impl.R
import br.lira.core.presentation.ResourceProvider
import br.lira.core.presentation.model.GenericUIError
import retrofit2.HttpException
import javax.inject.Inject
import br.com.lira.rickandmorty.core.presentation.R as coreR

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
        image = resourceProvider.getDrawable(coreR.drawable.ic_empty),
        isTryAgainVisible = false
    )

    private fun mapGenericError() = GenericUIError(
        message = resourceProvider.getString(coreR.string.common_error_title),
        image = resourceProvider.getDrawable(coreR.drawable.ic_error),
        isTryAgainVisible = true
    )

    companion object {
        private const val HTTP_NOT_FOUND = 404
    }
}