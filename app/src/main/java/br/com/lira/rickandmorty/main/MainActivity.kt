package br.com.lira.rickandmorty.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.databinding.ActivityMainBinding
import br.com.lira.rickandmorty.main.navigation.NavigationModeHandler
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.app_nav_host_fragment)
        registerNavigationModeCallback(navView)
        navView.setupWithNavController(navController)
    }

    private fun registerNavigationModeCallback(navView: BottomNavigationView) {
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                fragment: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                val navigationMode = fragment as? NavigationModeHandler
                navView.isVisible = navigationMode?.isImmersive()?.not() ?: true
            }
        }, true)
    }
}