package br.com.lira.rickandmorty.features.characterslist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.databinding.FragmentCharacterFilterBinding
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
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
        setupViews()
        observeViewState()
    }

    private fun setupViews() {
        binding.toolbarView.navigationIcon.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.btnApply.setOnClickListener {
            val name = binding.etName.text
            viewModel.onApplyFilterClicked(name)
        }
        binding.statusChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            viewModel.onStatusChecked(checkedIds)
        }
    }

    private fun observeViewState() {
        viewModel.viewState.filter.observe(viewLifecycleOwner) { filter ->
            binding.etName.setText(filter.name)
        }
        viewModel.viewState.action.observe(viewLifecycleOwner) { action ->
            when (action) {
               is CharacterFilterViewAction.SendFilterResult -> sendFilterResult(action.filter)
            }
        }
        viewModel.viewState.status.observe(viewLifecycleOwner) { status ->
            binding.statusChipGroup.check(status)
        }
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