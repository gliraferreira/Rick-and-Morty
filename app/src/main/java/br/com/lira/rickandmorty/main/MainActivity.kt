package br.com.lira.rickandmorty.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.databinding.ActivityMainBinding
import br.com.lira.rickandmorty.features.characters.presentation.ui.CharactersFragment
import br.com.lira.rickandmorty.features.episodes.presentation.ui.EpisodesFragment
import br.com.lira.rickandmorty.features.locations.presentation.view.LocationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.app_nav_host_fragment)
        navView.setupWithNavController(navController)
    }
}