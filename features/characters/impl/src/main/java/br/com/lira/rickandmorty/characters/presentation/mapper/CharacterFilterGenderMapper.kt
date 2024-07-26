package br.com.lira.rickandmorty.characters.presentation.mapper

import androidx.annotation.IdRes
import br.com.lira.rickandmorty.characters.domain.model.CharacterGender
import br.com.lira.rickandmorty.characters.impl.R
import javax.inject.Inject

class CharacterFilterGenderMapper @Inject constructor() {

    fun mapFrom(@IdRes chipRes: Int) = when (chipRes) {
        R.id.genderFemaleChip -> CharacterGender.FEMALE
        R.id.genderMaleChip -> CharacterGender.MALE
        R.id.genderGenderlessChip -> CharacterGender.GENDERLESS
        else -> CharacterGender.UNKNOWN
    }
}