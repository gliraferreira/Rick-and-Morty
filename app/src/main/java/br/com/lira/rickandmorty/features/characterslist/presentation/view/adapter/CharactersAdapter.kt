package br.com.lira.rickandmorty.features.characterslist.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterUIModel
import br.com.lira.rickandmorty.features.characterslist.presentation.view.CharactersListener
import br.com.lira.rickandmorty.databinding.ListItemCharacterBinding

class CharactersAdapter(
    private val listener: CharactersListener
) : PagingDataAdapter<CharacterUIModel, CharacterListItemViewHolder>(CharacterItemDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CharacterListItemViewHolder(
        binding = ListItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        listener = listener
    )

    override fun onBindViewHolder(holder: CharacterListItemViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}