package br.lira.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import br.com.lira.rickandmorty.core.presentation.databinding.LoadStateFooterViewItemBinding

class PagingLoadStateAdapter(
    private val retry: () -> Unit = {}
) : LoadStateAdapter<PagingLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: PagingLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PagingLoadStateViewHolder(
        binding = LoadStateFooterViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        retry = retry
    )
}
