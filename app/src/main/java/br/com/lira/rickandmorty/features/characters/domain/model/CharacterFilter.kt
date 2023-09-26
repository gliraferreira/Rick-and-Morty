package br.com.lira.rickandmorty.features.characters.domain.model

import android.os.Parcelable
import br.com.lira.rickandmorty.main.domain.model.CharacterGender
import br.com.lira.rickandmorty.main.domain.model.CharacterStatus
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterFilter(
    val name: String? = null,
    val status: CharacterStatus? = null,
    val gender: CharacterGender? = null
) : Parcelable
