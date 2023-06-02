package br.com.lira.rickandmorty.features.characterslist.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterError
import retrofit2.HttpException
import javax.inject.Inject

class CharactersErrorMapper @Inject constructor() {

    fun mapFrom(error: Throwable): CharacterError = when (error) {
        is HttpException -> {
            mapHttpException(error)
        }
        else -> {
            CharacterError(
                message = R.string.common_error_title,
                image = R.drawable.ic_error,
                isTryAgainVisible = true
            )
        }
    }

    private fun mapHttpException(error: HttpException) = if (error.code() == 404) {
        CharacterError(
            message = R.string.characters_search_empty_text,
            image = R.drawable.ic_empty,
            isTryAgainVisible = false
        )
    } else {
        CharacterError(
            message = R.string.common_error_title,
            image = R.drawable.ic_error,
            isTryAgainVisible = true
        )
    }
}