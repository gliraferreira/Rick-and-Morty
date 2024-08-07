package br.lira.core.presentation.mapper

import br.com.lira.rickandmorty.core.presentation.R
import br.lira.core.presentation.ResourceProvider
import br.lira.core.presentation.model.GenericUIError
import javax.inject.Inject

class GenericUIErrorMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(error: Throwable): GenericUIError = GenericUIError(
        message = resourceProvider.getString(R.string.common_error_title),
        image = resourceProvider.getDrawable(R.drawable.ic_error),
        isTryAgainVisible = true
    )
}