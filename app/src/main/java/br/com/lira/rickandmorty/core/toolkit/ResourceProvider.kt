package br.com.lira.rickandmorty.core.toolkit

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resId: Int, vararg formatArgs: Any = emptyArray()): String

    fun getDrawable(@DrawableRes drawableId: Int): Drawable?

    fun getColor(@ColorRes colorId: Int): Int
}
