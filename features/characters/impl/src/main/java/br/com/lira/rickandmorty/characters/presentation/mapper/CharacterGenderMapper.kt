package br.com.lira.rickandmorty.characters.presentation.mapper

import br.com.lira.rickandmorty.characters.domain.model.CharacterGender
import br.com.lira.rickandmorty.characters.impl.R
import br.lira.core.presentation.ResourceProvider
import javax.inject.Inject

class CharacterGenderMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun mapFrom(gender: CharacterGender) = when (gender) {
        CharacterGender.MALE -> R.string.character_gender_male
        CharacterGender.FEMALE -> R.string.character_gender_female
        else -> R.string.character_gender_unknown
    }.let(resourceProvider::getString)
}