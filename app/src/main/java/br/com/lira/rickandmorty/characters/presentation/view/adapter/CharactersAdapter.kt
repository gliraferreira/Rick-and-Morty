package br.com.lira.rickandmorty.characters.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.lira.rickandmorty.characters.domain.model.Character
import br.com.lira.rickandmorty.databinding.ListItemCharacterBinding

class CharactersAdapter :
    ListAdapter<Character, CharacterListItemViewHolder>(CharacterItemDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CharacterListItemViewHolder(
        ListItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CharacterListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}