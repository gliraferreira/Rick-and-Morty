package br.com.lira.rickandmorty.characters.presentation.mapper

import br.com.lira.rickandmorty.characters.domain.model.CharacterShort
import br.com.lira.rickandmorty.characters.presentation.model.CharacterUIModel

interface CharacterShortToUIMapper {
    fun mapFrom(character: CharacterShort): CharacterUIModel
}