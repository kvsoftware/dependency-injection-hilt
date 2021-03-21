package com.kvsoftware.dependencyinjectionhilt.di.module

import com.kvsoftware.dependencyinjectionhilt.data.rest.RestClient
import com.kvsoftware.dependencyinjectionhilt.data.rest.repository.CountryRepository
import com.kvsoftware.dependencyinjectionhilt.data.rest.repository.HistoricalRepository
import com.kvsoftware.dependencyinjectionhilt.data.sharepref.SharedPreferences
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.main.GetFavoriteCountriesInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideCountryRepository(restClient: RestClient): CountryRepository {
        return CountryRepository(restClient)
    }

    @Provides
    fun provideHistoricalRepository(restClient: RestClient): HistoricalRepository {
        return HistoricalRepository(restClient)
    }

}