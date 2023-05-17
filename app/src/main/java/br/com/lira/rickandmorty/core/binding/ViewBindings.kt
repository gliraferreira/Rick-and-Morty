package br.com.lira.rickandmorty.core.binding

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.lira.rickandmorty.R
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso

@BindingAdapter("present")
fun View.setPresent(value: Boolean?) {
    value?.let {
        visibility = if (it) View.VISIBLE else View.GONE
    }
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
        chipBackgroundColor = ColorStateList.valueOf(
            ContextCompat.getColor(context, it)
        )
    }
}

@BindingAdapter("textRes")
fun TextView.setTextRes(value: Int?) {
    value?.let {
        text = context.getText(it)
    }
}
