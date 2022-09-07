package br.com.lira.rickandmorty.characters.presentation.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.databinding.ListItemCharacterBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class CharacterListItemViewHolder(
    private val binding: ListItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        binding.character = character
        Picasso.get()
            .load(character.image)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.picture, object : Callback {
                override fun onSuccess() { }

                override fun onError(e: Exception?) { }
            })
    }
}