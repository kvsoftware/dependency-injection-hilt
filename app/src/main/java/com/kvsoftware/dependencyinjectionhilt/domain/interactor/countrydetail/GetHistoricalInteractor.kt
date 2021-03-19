package com.kvsoftware.dependencyinjectionhilt.domain.interactor.countrydetail

import com.kvsoftware.dependencyinjectionhilt.data.model.HistoricalDataModel
import com.kvsoftware.dependencyinjectionhilt.data.rest.repository.HistoricalRepository
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.base.BaseInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHistoricalInteractor @Inject constructor(private val historicalRepository: HistoricalRepository) :
    BaseInteractor<List<HistoricalDataModel>, GetHistoricalInteractor.Params>() {

    override suspend fun invoke(params: Params): List<HistoricalDataModel> {
        return withContext(Dispatchers.IO) {
            val response = historicalRepository.getHistorical()
            val historicals = response.filter { it.country == params.country }
            historicals
        }
    }

    class Params(val country: String)
}