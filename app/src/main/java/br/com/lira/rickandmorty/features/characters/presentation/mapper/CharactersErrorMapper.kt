package br.com.lira.rickandmorty.features.characters.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.ResourceProvider
import br.com.lira.rickandmorty.main.presentation.model.GenericUIError
import retrofit2.HttpException
import javax.inject.Inject

class CharactersErrorMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(error: Throwable): GenericUIError = when (error) {
        is HttpException -> {
            mapHttpException(error)
        }
        else -> {
            GenericUIError(
                message = resourceProvider.getString(R.string.common_error_title),
                image = resourceProvider.getDrawable(R.drawable.ic_error),
                isTryAgainVisible = true
            )
        }
    }

    private fun mapHttpException(error: HttpException) = if (error.code() == 404) {
        GenericUIError(
            message = resourceProvider.getString(R.string.characters_search_empty_text),
            image = resourceProvider.getDrawable(R.drawable.ic_empty),
            isTryAgainVisible = false
        )
    } else {
        GenericUIError(
            message = resourceProvider.getString(R.string.common_error_title),
            image = resourceProvider.getDrawable(R.drawable.ic_error),
            isTryAgainVisible = true
        )
    }
}
