package br.com.lira.rickandmorty.characters.presentation.model

import android.os.Parcelable
import androidx.annotation.IdRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterFilterUIModel(
    val name: String,
    @IdRes val selectedStatus: Int?,
    @IdRes val selectedGender: Int?
) : Parcelable
