package br.com.lira.rickandmorty.features.episodes.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.databinding.FragmentEpisodesBinding
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeUIModel
import br.com.lira.rickandmorty.features.episodes.presentation.view.adapter.EpisodesListAdapter
import br.com.lira.rickandmorty.features.episodes.presentation.viewmodel.EpisodesListViewModel
import br.com.lira.rickandmorty.features.episodes.presentation.viewmodel.EpisodesListViewState
import br.com.lira.rickandmorty.main.presentation.adapter.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

const val FILTER_REQUEST_KEY = "filter_request_key"
const val ARG_FILTER = "arg_filter"

@AndroidEntryPoint
class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding
    private val viewModel: EpisodesListViewModel by viewModels()

    private lateinit var episodesAdapter: EpisodesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeViewState()
    }

    private fun setupViews() {
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        binding.toolbarView.navigationIcon.isVisible = false
        binding.toolbarView.title.setText(R.string.title_episodes)
    }

    private fun setupRecyclerView() {
        episodesAdapter = EpisodesListAdapter() {}
        binding.rvCharacters.adapter = episodesAdapter.apply {
            withLoadStateFooter(
                footer = PagingLoadStateAdapter()
            )
            addLoadStateListener { loadState ->
                viewModel.onLoadStateChanged(loadState)
            }
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            handleEpisodesList(state.episodes)
            handleCurrentScreenState(state)
        }
    }

    private fun handleEpisodesList(episodes: PagingData<EpisodeUIModel>?) {
        episodes?.let {
            episodesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun handleCurrentScreenState(state: EpisodesListViewState) = with(binding) {
        loading.root.isVisible = state.isLoading
        rvCharacters.isVisible = state.shouldDisplayContent
        errorState.root.isVisible = state.isError
    }
}
