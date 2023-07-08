package br.com.lira.rickandmorty.features.characterslist.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus
import javax.inject.Inject

class CharacterStatusToResourceMapper @Inject constructor() {

    fun mapFrom(status: CharacterStatus) = when (status) {
        CharacterStatus.ALIVE -> R.id.aliveStatusChip
        CharacterStatus.DEAD -> R.id.deadStatusChip
        else -> R.id.unknownStatusChip
    }
}
