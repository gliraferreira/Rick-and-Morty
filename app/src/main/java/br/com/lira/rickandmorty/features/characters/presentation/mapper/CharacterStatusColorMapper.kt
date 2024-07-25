package br.com.lira.rickandmorty.features.characters.presentation.mapper

import br.com.lira.rickandmorty.R
import br.lira.core.presentation.ResourceProvider
import br.com.lira.rickandmorty.characters.domain.model.CharacterStatus
import javax.inject.Inject

class CharacterStatusColorMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(status: CharacterStatus) = when (status) {
        CharacterStatus.ALIVE -> R.color.character_status_alive
        CharacterStatus.DEAD -> R.color.character_status_dead
        else -> R.color.character_status_unknown
    }.let(resourceProvider::getColor)
}
