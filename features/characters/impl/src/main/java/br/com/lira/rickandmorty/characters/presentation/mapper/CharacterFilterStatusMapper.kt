package br.com.lira.rickandmorty.characters.presentation.mapper

import androidx.annotation.IdRes
import br.com.lira.rickandmorty.characters.domain.model.CharacterStatus
import br.com.lira.rickandmorty.characters.impl.R
import javax.inject.Inject

class CharacterFilterStatusMapper @Inject constructor() {

    fun mapFrom(@IdRes chipRes: Int) = when (chipRes) {
        R.id.aliveStatusChip -> CharacterStatus.ALIVE
        R.id.deadStatusChip -> CharacterStatus.DEAD
        else -> CharacterStatus.UNKNOWN
    }
}