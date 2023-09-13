package br.com.lira.rickandmorty.main.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import br.com.lira.rickandmorty.databinding.LoadStateFooterViewItemBinding
import br.com.lira.rickandmorty.main.presentation.adapter.PagingLoadStateViewHolder

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
