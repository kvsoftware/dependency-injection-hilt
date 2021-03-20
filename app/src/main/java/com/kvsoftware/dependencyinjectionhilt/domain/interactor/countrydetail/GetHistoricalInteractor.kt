package com.kvsoftware.dependencyinjectionhilt.domain.interactor.countrydetail

import com.kvsoftware.dependencyinjectionhilt.data.rest.repository.HistoricalRepository
import com.kvsoftware.dependencyinjectionhilt.domain.helper.AppCacheHelper
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.base.BaseInteractor
import com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail.ProvinceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHistoricalInteractor @Inject constructor(private val historicalRepository: HistoricalRepository) :
    BaseInteractor<ArrayList<ProvinceModel>, GetHistoricalInteractor.Params>() {

    override suspend fun invoke(params: Params): ArrayList<ProvinceModel> {
        return withContext(Dispatchers.IO) {
            val historicals =
                AppCacheHelper.historicalDataModel ?: historicalRepository.getHistorical()
            AppCacheHelper.historicalDataModel = historicals

            val filterHistoricals =
                historicals.filter { it.country == params.country && it.province != null }
            val provinceModels = arrayListOf<ProvinceModel>()
            for (historical in filterHistoricals) {
                val provinceModel = ProvinceModel(
                    province = historical.province ?: "",
                    cases = getLatest(historical.timeline.cases),
                    recovered = getLatest(historical.timeline.recovered),
                    deaths = getLatest(historical.timeline.deaths)
                )
                provinceModels.add(provinceModel)
            }

            provinceModels
        }
    }

    private fun getLatest(data: Map<String, Int>): Int {
        return if (data.isEmpty()) {
            0
        } else {
            val entry: Map.Entry<String, Int> = data.iterator().next()
            entry.value
        }
    }

    class Params(val country: String)
}