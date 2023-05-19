package br.com.lira.rickandmorty.features.characterdetails.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.lira.rickandmorty.features.characterdetails.presentation.model.CharacterEpisodeUIModel
import br.com.lira.rickandmorty.databinding.ListItemCharacterEpisodeBinding

class CharacterEpisodeAdapter : ListAdapter<CharacterEpisodeUIModel, CharacterEpisodeViewHolder>(
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
        )
    )

    override fun onBindViewHolder(holder: CharacterEpisodeViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}