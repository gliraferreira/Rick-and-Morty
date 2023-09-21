package br.com.lira.rickandmorty.features.characterslist.presentation.mapper

import androidx.annotation.IdRes
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.main.domain.model.CharacterGender
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus
import javax.inject.Inject

class CharacterGenderMapper @Inject constructor() {

    fun mapFrom(@IdRes chipRes: Int) = when (chipRes) {
        R.id.genderFemaleChip -> CharacterGender.FEMALE
        R.id.genderMaleChip -> CharacterGender.MALE
        R.id.genderGenderlessChip -> CharacterGender.GENDERLESS
        else -> CharacterGender.UNKNOWN
    }
}
