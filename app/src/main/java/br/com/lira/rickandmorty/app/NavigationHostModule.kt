package br.com.lira.rickandmorty.app

import br.com.lira.rickandmorty.navigation.NavigationHostProvider
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationHostModule {

    @Binds
    @Reusable
    fun bindsNavigationHostProvider(
        provider: NavigationHostProviderImpl
    ): NavigationHostProvider
}