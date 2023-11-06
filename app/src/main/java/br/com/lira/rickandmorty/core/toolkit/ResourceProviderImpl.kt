package br.com.lira.rickandmorty.core.toolkit

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    override fun getDrawable(drawableId: Int): Drawable? {
        return AppCompatResources.getDrawable(context, drawableId)
    }

    override fun getColor(colorId: Int): Int {
        return ContextCompat.getColor(context, colorId)
    }
}
