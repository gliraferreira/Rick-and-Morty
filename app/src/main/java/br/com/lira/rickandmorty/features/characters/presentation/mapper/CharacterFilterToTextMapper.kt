package br.com.lira.rickandmorty.features.characters.presentation.mapper

import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import javax.inject.Inject

private const val SEPARATOR = " | "

class CharacterFilterToTextMapper @Inject constructor() {

    fun mapFrom(filter: CharacterFilter) = listOf(
        filter.name.orEmpty(),
        filter.status?.name?.lowercase().orEmpty(),
        filter.gender?.name?.lowercase().orEmpty()
    ).filter { it.isNotEmpty() }.joinToString(SEPARATOR)
}
