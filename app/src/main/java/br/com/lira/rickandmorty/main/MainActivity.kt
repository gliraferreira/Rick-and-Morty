package br.com.lira.rickandmorty.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.core.toolkit.setupFragmentHost
import br.com.lira.rickandmorty.databinding.ActivityMainBinding
import br.com.lira.rickandmorty.features.characterslist.presentation.view.CharactersFragment
import br.com.lira.rickandmorty.features.episodes.presentation.view.EpisodesFragment
import br.com.lira.rickandmorty.features.locations.presentation.view.LocationsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val charactersFragment = CharactersFragment()
    private val episodesFragment = EpisodesFragment()
    private val locationsFragment = LocationsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragmentHost(R.id.app_nav_host_fragment, charactersFragment)

        binding.navView.setOnItemSelectedListener {

            val fragment = when (it.itemId) {
                R.id.navigation_characters -> charactersFragment
                R.id.navigation_episodes -> episodesFragment
                else -> locationsFragment
            }

            setupFragmentHost(R.id.app_nav_host_fragment, fragment)

            true
        }
    }
}