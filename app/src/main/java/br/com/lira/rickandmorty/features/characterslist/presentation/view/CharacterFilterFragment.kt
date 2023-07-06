package br.com.lira.rickandmorty.features.characterslist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lira.rickandmorty.databinding.FragmentCharacterFilterBinding
import br.com.lira.rickandmorty.features.characterslist.domain.model.CharacterFilter
import br.com.lira.rickandmorty.features.characterslist.presentation.viewmodel.CharacterFilterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFilterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterFilterBinding
    private val viewModel: CharacterFilterViewModel by viewModels()

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

    companion object {
        fun newInstance(currentFilter: CharacterFilter?) = CharacterFilterFragment()
    }
}