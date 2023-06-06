package br.com.lira.rickandmorty.features.characterslist.presentation.mapper

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus
import javax.inject.Inject

class CharacterStatusMapper @Inject constructor() {

    fun mapFrom(chipRes: Int) = when (chipRes) {
        R.id.aliveStatusChip -> CharacterStatus.ALIVE
        R.id.deadStatusChip -> CharacterStatus.DEAD
        else -> CharacterStatus.UNKNOWN
    }
}
