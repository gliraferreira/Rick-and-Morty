package br.com.lira.rickandmorty.characters.presentation.mapper

import br.com.lira.rickandmorty.characters.domain.model.CharacterFilter
import javax.inject.Inject

class CharacterFilterToTextMapper @Inject constructor() {

    fun mapFrom(filter: CharacterFilter) = listOf(
        filter.name.orEmpty(),
        filter.status?.name?.lowercase().orEmpty(),
        filter.gender?.name?.lowercase().orEmpty()
    ).filter { it.isNotEmpty() }.joinToString(SEPARATOR)

    companion object {
        private const val SEPARATOR = " | "
    }
}