package br.com.lira.rickandmorty.core.binding

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.lira.rickandmorty.R
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso

@BindingAdapter("present")
fun View.setPresent(value: Boolean?) {
    visibility = if (value == true) View.VISIBLE else View.GONE
}

@BindingAdapter("img")
fun ImageView.loadImage(url: String?) {
    Picasso.get()
        .load(url)
        .error(R.drawable.ic_round_account_circle)
        .into(this)
}

@BindingAdapter("bgColor")
fun Chip.setBgColor(value: Int?) {
    value?.let {
        Log.i("RickApp", "bgColor: ${value.toString()}")
        setChipBackgroundColorResource(it)
    }
}

@BindingAdapter("originalBgColor", "highlightedBgColor", "isHighlighted")
fun Chip.setHighlightBgColor(
    originalBgColor: ColorDrawable?,
    highlightedBgColor: ColorDrawable?,
    isHighlighted: Boolean?
) {
   runCatching {
       val bgColor = if (isHighlighted == true) {
           highlightedBgColor
       } else {
           originalBgColor
       }
       bgColor?.let {
           Log.i("RickApp", "highlightBgColor: ${bgColor.toString()}")
           chipBackgroundColor = ColorStateList.valueOf(bgColor.color)
       }
   }
}

@BindingAdapter("textRes")
fun TextView.setTextRes(value: Int?) {
    value?.let {
        runCatching {
            text = context.getText(it)
        }
    }
}

@BindingAdapter("srcRes")
fun ImageView.setSrcRes(value: Int?) {
    value?.let {
        runCatching {
            setImageResource(it)
        }
    }
}
