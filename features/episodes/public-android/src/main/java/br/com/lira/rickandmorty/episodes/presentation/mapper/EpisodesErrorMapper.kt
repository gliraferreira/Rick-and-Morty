package br.com.lira.rickandmorty.episodes.presentation.mapper

import br.lira.core.presentation.model.GenericUIError

interface EpisodesErrorMapper {
    fun mapFrom(error: Throwable): GenericUIError
}