package br.com.lira.rickandmorty.navigation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationModeRegister {

    fun registerCallback(activity: AppCompatActivity, navView: BottomNavigationView) {
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object :
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