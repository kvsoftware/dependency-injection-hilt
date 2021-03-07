package com.kvsoftware.dependencyinjectionhilt.di.module

import com.kvsoftware.dependencyinjectionhilt.data.rest.RestClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideRestClient(): RestClient {
        return RestClient("https://api.covid19api.com/")
    }

}