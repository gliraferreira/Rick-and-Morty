package br.com.lira.rickandmorty.features.episodes.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.SlideFromRightAnimation
import br.com.lira.rickandmorty.core.toolkit.navigateToFragment
import br.com.lira.rickandmorty.databinding.FragmentEpisodesBinding
import br.com.lira.rickandmorty.features.episodes.presentation.model.EpisodeUIModel
import br.com.lira.rickandmorty.features.episodes.presentation.ui.adapter.EpisodesListAdapter
import br.com.lira.rickandmorty.features.episodes.presentation.viewmodel.EpisodesListViewAction
import br.com.lira.rickandmorty.features.episodes.presentation.viewmodel.EpisodesListViewModel
import br.com.lira.rickandmorty.features.episodes.presentation.viewmodel.EpisodesListViewState
import br.com.lira.rickandmorty.features.shared.presentation.adapter.PagingLoadStateAdapter
import br.com.lira.rickandmorty.main.navigation.DefaultNavigationMode
import br.com.lira.rickandmorty.main.navigation.NavigationModeHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : Fragment(), NavigationModeHandler by DefaultNavigationMode {

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
        observeViewActions()
    }

    private fun setupViews() {
        setupToolbar()
        setupRecyclerView()
        setupErrorView()
    }

    private fun setupToolbar() {
        binding.toolbarView.navigationIcon.isVisible = false
        binding.toolbarView.searchIcon.isVisible = false
        binding.toolbarView.title.setText(R.string.title_episodes)
    }

    private fun setupRecyclerView() {
        episodesAdapter = EpisodesListAdapter(viewModel::onEpisodeClicked)
        binding.episodesList.adapter = episodesAdapter.apply {
            withLoadStateFooter(
                footer = PagingLoadStateAdapter()
            )
            addLoadStateListener { loadState ->
                viewModel.onLoadStateChanged(loadState)
            }
        }
    }

    private fun setupErrorView() {
        binding.errorState.btnTryAgain.setOnClickListener {
            viewModel.onTryAgainClicked()
        }
    }

    private fun handleError(state: EpisodesListViewState) = with(binding.errorState) {
        state.error?.let {
            description.text = it.message
            btnTryAgain.isVisible = it.isTryAgainVisible
            errorImageView.setImageDrawable(it.image)
        }
    }

    private fun observeViewActions() {
        viewModel.action.observe(viewLifecycleOwner) { viewAction ->
            when (viewAction) {
                is EpisodesListViewAction.OpenEpisodeDetails -> openEpisodeDetailsScreen(
                    viewAction.episodeId
                )
            }
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            handleEpisodesList(state.episodes)
            handleCurrentScreenState(state)
            handleError(state)
        }
    }

    private fun handleEpisodesList(episodes: PagingData<EpisodeUIModel>?) {
        episodes?.let {
            episodesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun openEpisodeDetailsScreen(episodeId: Long) {
        navigateToFragment(
            hostRes = R.id.app_nav_host_fragment,
            destination = EpisodeDetailsFragment.newInstance(episodeId),
            fragmentAnimation = SlideFromRightAnimation
        )
    }

    private fun handleCurrentScreenState(state: EpisodesListViewState) = with(binding) {
        loading.root.isVisible = state.isLoading
        episodesList.isVisible = state.shouldDisplayContent
        errorState.root.isVisible = state.isError
    }
}
