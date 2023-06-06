package br.com.lira.rickandmorty.features.characterslist.domain.model

import br.com.lira.rickandmorty.main.domain.model.CharacterStatus

data class CharacterFilter(
    val name: String? = null,
    val status: CharacterStatus? = null
)
