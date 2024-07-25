package br.com.lira.rickandmorty.main

import br.com.lira.rickandmorty.R
import br.com.lira.rickandmorty.navigation.NavigationHostProvider
import javax.inject.Inject

class NavigationHostProviderImpl @Inject constructor() : NavigationHostProvider {
    
    override fun getNavHostId(): Int {
        return R.id.app_nav_host_fragment
    }
}