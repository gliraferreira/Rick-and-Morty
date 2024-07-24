package br.lira.core.presentation

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
