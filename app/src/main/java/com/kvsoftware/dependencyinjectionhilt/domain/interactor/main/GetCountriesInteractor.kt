package com.kvsoftware.dependencyinjectionhilt.domain.interactor.main

import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import com.kvsoftware.dependencyinjectionhilt.data.rest.repository.CountryRepository
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.base.BaseInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCountriesInteractor @Inject constructor(private val countryRepository: CountryRepository) :
    BaseInteractor<List<CountryDataModel>, GetCountriesInteractor.Params>() {

    override suspend fun invoke(params: Params): List<CountryDataModel> {
        return withContext(Dispatchers.IO) {
            countryRepository.getCountries()
        }
    }

    class Params
}