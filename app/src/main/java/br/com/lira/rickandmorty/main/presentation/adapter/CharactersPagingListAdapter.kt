package br.com.lira.rickandmorty.main.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.lira.rickandmorty.databinding.ListItemCharacterBinding
import br.com.lira.rickandmorty.main.presentation.model.CharacterUIModel

class CharactersPagingListAdapter(
    private val isNavigationEnabled: Boolean = false,
    private val onCharacterClicked: (Long) -> Unit
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
        onCharacterClicked = onCharacterClicked,
        isNavigationEnabled = isNavigationEnabled
    )

    override fun onBindViewHolder(holder: CharacterListItemViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}