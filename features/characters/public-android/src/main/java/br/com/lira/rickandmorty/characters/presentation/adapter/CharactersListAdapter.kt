package br.com.lira.rickandmorty.characters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.lira.rickandmorty.characters.databinding.ListItemCharacterBinding
import br.com.lira.rickandmorty.characters.presentation.model.CharacterUIModel

class CharactersListAdapter(
    private val isNavigationEnabled: Boolean = false,
    private val onCharacterClicked: (Long) -> Unit
) : ListAdapter<CharacterUIModel, CharacterListItemViewHolder>(CharacterItemDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CharacterListItemViewHolder(
        binding = ListItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onCharacterClicked = onCharacterClicked,
        isNavigationEnabled = isNavigationEnabled
    )

    override fun onBindViewHolder(holder: CharacterListItemViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}