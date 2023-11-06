package br.com.lira.rickandmorty.core.di

import br.com.lira.rickandmorty.core.mapper.UrlDefaultMapper
import br.com.lira.rickandmorty.core.mapper.UrlMapper
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreMapperModule {

    @Binds
    @Reusable
    fun bindsUrlMapper(
        mapper: UrlDefaultMapper
    ): UrlMapper
}