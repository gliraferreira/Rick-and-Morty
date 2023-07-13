package br.com.lira.rickandmorty.features.characterslist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.databinding.FragmentCharacterFilterBinding
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.presentation.model.CharacterFilterUIModel
import br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel.CharacterFilterViewAction
import br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel.CharacterFilterViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_CURRENT_FILTER = "arg_current_filter"

@AndroidEntryPoint
class CharacterFilterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterFilterBinding
    private val viewModel: CharacterFilterViewModel by viewModels()
    private var currentFilter: CharacterFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentFilter = it.getParcelable(ARG_CURRENT_FILTER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterFilterBinding.inflate(inflater).apply {
            lifecycleOwner = this@CharacterFilterFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(currentFilter)
        setupToolbar()
        setupViews()
        observeViewState()
    }

    private fun setupViews() {
        binding.btnApply.setOnClickListener {
            val name = binding.etName.text
            viewModel.onApplyFilterClicked(name)
        }
        binding.statusChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            viewModel.onStatusChecked(checkedIds)
        }
        binding.genderChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            viewModel.onGenderChecked(checkedIds)
        }
    }

    private fun setupToolbar() {
        binding.toolbarView.navigationIcon.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.toolbarView.searchIcon.isVisible = false
        binding.toolbarView.navigationIcon.isVisible = true
        binding.toolbarView.title.setText(R.string.character_filter_title)
    }

    private fun observeViewState() {
        viewModel.viewState.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is CharacterFilterViewAction.SendFilterResult -> sendFilterResult(action.filter)
                is CharacterFilterViewAction.UpdateUI -> updateUi(action.filter)
            }
        }
    }

    private fun updateUi(filter: CharacterFilterUIModel) {
        binding.etName.setText(filter.name)
        filter.status?.let { binding.statusChipGroup.check(it) }
        filter.gender?.let { binding.genderChipGroup.check(it) }
    }

    private fun sendFilterResult(filter: CharacterFilter?) {
        setFragmentResult(FILTER_REQUEST_KEY, bundleOf(ARG_FILTER to filter))
        activity?.onBackPressed()
    }

    companion object {
        fun newInstance(currentFilter: CharacterFilter?) = CharacterFilterFragment().apply {
            arguments = bundleOf(
                ARG_CURRENT_FILTER to currentFilter
            )
        }
    }
}
