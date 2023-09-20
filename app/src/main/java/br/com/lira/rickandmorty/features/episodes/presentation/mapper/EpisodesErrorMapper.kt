package br.com.lira.rickandmorty.features.episodes.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.ResourceProvider
import br.com.lira.rickandmorty.main.presentation.model.GenericError
import javax.inject.Inject

class EpisodesErrorMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(error: Throwable): GenericError = GenericError(
        message = resourceProvider.getString(R.string.common_error_title),
        image = resourceProvider.getDrawable(R.drawable.ic_error),
        isTryAgainVisible = true
    )
}