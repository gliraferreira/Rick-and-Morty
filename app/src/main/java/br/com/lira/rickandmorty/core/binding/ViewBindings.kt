package br.com.lira.rickandmorty.core.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.lira.rickandmorty.R
import com.squareup.picasso.Callback
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
        .into(this, object : Callback {
            override fun onSuccess() { }

            override fun onError(e: Exception?) { }
        })
}
