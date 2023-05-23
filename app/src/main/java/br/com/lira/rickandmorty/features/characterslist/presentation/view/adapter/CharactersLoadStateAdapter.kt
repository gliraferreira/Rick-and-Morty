package br.com.lira.rickandmorty.features.characterslist.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import br.com.lira.rickandmorty.databinding.CharactersLoadStateFooterViewItemBinding

class CharactersLoadStateAdapter(
    private val retry: () -> Unit = {}
) : LoadStateAdapter<CharactersLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: CharactersLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = CharactersLoadStateViewHolder(
        binding = CharactersLoadStateFooterViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        retry = retry
    )
}
