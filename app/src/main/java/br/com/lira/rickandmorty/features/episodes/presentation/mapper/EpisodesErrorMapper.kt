package br.com.lira.rickandmorty.features.episodes.presentation.mapper

import br.com.lira.rickandmorty.R
import br.lira.core.presentation.ResourceProvider
import br.lira.core.presentation.model.GenericUIError
import javax.inject.Inject

class EpisodesErrorMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(error: Throwable): GenericUIError = GenericUIError(
        message = resourceProvider.getString(R.string.common_error_title),
        image = resourceProvider.getDrawable(R.drawable.ic_error),
        isTryAgainVisible = true
    )
}