package com.kvsoftware.dependencyinjectionhilt.data.rest.repository

import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import com.kvsoftware.dependencyinjectionhilt.data.rest.RestClient
import com.kvsoftware.dependencyinjectionhilt.data.rest.service.CountryService

class CountryRepository(private val restClient: RestClient) {

    suspend fun getCountries(sort: String? = null): List<CountryDataModel> =
        restClient.createService(CountryService::class.java).getCountries(sort)

}