package com.kvsoftware.dependencyinjectionhilt.data.rest.service

import com.kvsoftware.dependencyinjectionhilt.data.model.HistoricalDataModel
import retrofit2.http.GET

interface HistoricalService {
    @GET("historical")
    suspend fun getHistorical(): List<HistoricalDataModel>
}