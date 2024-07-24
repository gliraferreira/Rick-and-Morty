package br.com.lira.rickandmorty.features.characters.presentation.mapper

import br.com.lira.rickandmorty.R
import br.lira.core.presentation.ResourceProvider
import br.com.lira.rickandmorty.features.shared.domain.model.CharacterGender
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
