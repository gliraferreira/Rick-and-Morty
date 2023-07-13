package br.com.lira.rickandmorty.core.extension

import android.widget.ImageView
import br.com.lira.rickandmorty.R
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?) {
    Picasso.get()
        .load(url)
        .error(R.drawable.ic_round_account_circle)
        .into(this)
}
