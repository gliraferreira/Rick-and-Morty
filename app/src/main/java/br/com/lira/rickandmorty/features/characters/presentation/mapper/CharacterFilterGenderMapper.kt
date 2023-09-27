package br.com.lira.rickandmorty.features.characters.presentation.mapper

import androidx.annotation.IdRes
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.main.domain.model.CharacterGender
import javax.inject.Inject

class CharacterFilterGenderMapper @Inject constructor() {

    fun mapFrom(@IdRes chipRes: Int) = when (chipRes) {
        R.id.genderFemaleChip -> CharacterGender.FEMALE
        R.id.genderMaleChip -> CharacterGender.MALE
        R.id.genderGenderlessChip -> CharacterGender.GENDERLESS
        else -> CharacterGender.UNKNOWN
    }
}
