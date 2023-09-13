package br.com.lira.rickandmorty.core.di

import br.com.lira.rickandmorty.core.toolkit.ResourceProvider
import br.com.lira.rickandmorty.core.toolkit.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ResourceProviderModule {

    @Provides
    fun provideResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider {
        return resourceProviderImpl
    }
}
