package br.lira.core.presentation.model

import android.graphics.drawable.Drawable

data class GenericUIError(
    val message: String,
    val image: Drawable?,
    val isTryAgainVisible: Boolean
)
