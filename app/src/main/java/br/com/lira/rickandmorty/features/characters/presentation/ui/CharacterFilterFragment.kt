package br.com.lira.rickandmorty.features.characters.presentation.ui

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
import br.com.lira.rickandmorty.core.toolkit.addPopBackStackHandler
import br.com.lira.rickandmorty.core.toolkit.popBackStack
import br.com.lira.rickandmorty.databinding.FragmentCharacterFilterBinding
import br.com.lira.rickandmorty.features.characters.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characters.presentation.model.CharacterFilterUIModel
import br.com.lira.rickandmorty.features.characters.presentation.viewaction.CharacterFilterViewAction
import br.com.lira.rickandmorty.features.characters.presentation.viewmodel.CharacterFilterViewModel
import br.com.lira.rickandmorty.main.navigation.ImmersiveNavigationMode
import br.com.lira.rickandmorty.main.navigation.NavigationModeHandler
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_CURRENT_FILTER = "arg_current_filter"

@AndroidEntryPoint
class CharacterFilterFragment : Fragment(), NavigationModeHandler by ImmersiveNavigationMode {

    private lateinit var binding: FragmentCharacterFilterBinding
    private val viewModel: CharacterFilterViewModel by viewModels()
    private var currentFilter: CharacterFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentFilter = it.getParcelable(ARG_CURRENT_FILTER)
        }

        addPopBackStackHandler()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterFilterBinding.inflate(inflater)

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
            this@CharacterFilterFragment.popBackStack()
        }
        binding.toolbarView.searchIcon.isVisible = false
        binding.toolbarView.navigationIcon.isVisible = true
        binding.toolbarView.title.setText(R.string.character_filter_title)
    }

    private fun observeViewState() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is CharacterFilterViewAction.SendFilterResult -> sendFilterResult(action.filter)
                is CharacterFilterViewAction.UpdateUI -> updateUi(action.filter)
            }
        }
    }

    private fun updateUi(filter: CharacterFilterUIModel) {
        binding.etName.setText(filter.name)
        filter.selectedStatus?.let { binding.statusChipGroup.check(it) }
        filter.selectedGender?.let { binding.genderChipGroup.check(it) }
    }

    private fun sendFilterResult(filter: CharacterFilter?) {
        setFragmentResult(FILTER_REQUEST_KEY, bundleOf(ARG_FILTER to filter))
        popBackStack()
    }

    companion object {
        fun newInstance(currentFilter: CharacterFilter?) = CharacterFilterFragment().apply {
            arguments = bundleOf(
                ARG_CURRENT_FILTER to currentFilter
            )
        }
    }
}
