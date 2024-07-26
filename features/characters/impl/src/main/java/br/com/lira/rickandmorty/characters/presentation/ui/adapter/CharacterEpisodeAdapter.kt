package br.com.lira.rickandmorty.characters.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.lira.rickandmorty.characters.impl.databinding.ListItemCharacterEpisodeBinding
import br.com.lira.rickandmorty.characters.presentation.model.CharacterEpisodeUIModel

class CharacterEpisodeAdapter(
    private val onEpisodeClicked: (Long) -> Unit
) : ListAdapter<CharacterEpisodeUIModel, CharacterEpisodeViewHolder>(
    CharacterEpisodeItemDiffCallback
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CharacterEpisodeViewHolder(
        binding = ListItemCharacterEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onEpisodeClicked = onEpisodeClicked
    )

    override fun onBindViewHolder(holder: CharacterEpisodeViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}