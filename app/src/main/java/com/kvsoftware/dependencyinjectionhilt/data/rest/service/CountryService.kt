package com.kvsoftware.dependencyinjectionhilt.data.rest.service

import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import retrofit2.http.GET

interface CountryService {
    @GET("countries")
    suspend fun getCountries(): List<CountryDataModel>
}