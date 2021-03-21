package com.kvsoftware.dependencyinjectionhilt.di.module

import android.content.Context
import com.kvsoftware.dependencyinjectionhilt.data.rest.RestClient
import com.kvsoftware.dependencyinjectionhilt.data.sharepref.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for keeping below dependencies arrived as long as application.
 *
 * The dependency which has @Singleton annotation, it will be constructed only one instance across
 * the component.
 * */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideRestClient(): RestClient {
        return RestClient("https://corona.lmao.ninja/v2/")
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return SharedPreferences(context)
    }

}