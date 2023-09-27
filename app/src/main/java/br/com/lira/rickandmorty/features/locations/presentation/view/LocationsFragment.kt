package br.com.lira.rickandmorty.features.locations.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.databinding.FragmentLocationsBinding
import br.com.lira.rickandmorty.main.presentation.adapter.CharactersListAdapter
import br.com.lira.rickandmorty.features.characters.presentation.viewmodel.CharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint

const val FILTER_REQUEST_KEY = "filter_request_key"
const val ARG_FILTER = "arg_filter"

@AndroidEntryPoint
class LocationsFragment : Fragment() {

    private lateinit var binding: FragmentLocationsBinding
    private val viewModel: CharactersListViewModel by viewModels()

    private lateinit var charactersAdapter: CharactersListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(layoutInflater)

        return binding.root
    }
}
