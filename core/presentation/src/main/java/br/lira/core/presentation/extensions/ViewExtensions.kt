package br.lira.core.presentation.extensions

import android.widget.ImageView
import br.lira.core.presentation.R
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?) {
    Picasso.get()
        .load(url)
        .error(R.drawable.ic_round_account_circle)
        .into(this)
}
