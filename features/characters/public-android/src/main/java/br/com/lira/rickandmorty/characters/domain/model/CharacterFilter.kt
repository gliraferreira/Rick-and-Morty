package br.com.lira.rickandmorty.characters.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterFilter(
    val name: String? = null,
    val status: CharacterStatus? = null,
    val gender: CharacterGender? = null
) : Parcelable
