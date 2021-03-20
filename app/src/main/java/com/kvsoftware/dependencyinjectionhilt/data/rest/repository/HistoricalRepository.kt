package com.kvsoftware.dependencyinjectionhilt.data.rest.repository

import com.kvsoftware.dependencyinjectionhilt.data.model.HistoricalDataModel
import com.kvsoftware.dependencyinjectionhilt.data.rest.RestClient
import com.kvsoftware.dependencyinjectionhilt.data.rest.service.HistoricalService

class HistoricalRepository(private val restClient: RestClient) {

    suspend fun getHistorical(): List<HistoricalDataModel> =
        restClient.createService(HistoricalService::class.java).getHistorical()

}