package com.kvsoftware.dependencyinjectionhilt.domain.interactor.main

import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import com.kvsoftware.dependencyinjectionhilt.data.rest.repository.CountryRepository
import com.kvsoftware.dependencyinjectionhilt.data.sharepref.SharedPreferences
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.base.BaseInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoriteCountriesInteractor @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val countryRepository: CountryRepository
) :
    BaseInteractor<ArrayList<CountryDataModel>, GetFavoriteCountriesInteractor.Params>() {

    override suspend fun invoke(params: Params): ArrayList<CountryDataModel> {
        return withContext(Dispatchers.IO) {
            val countryDataModels = arrayListOf<CountryDataModel>()
            val countryIds = sharedPreferences.favoritesDataModel?.countries ?: arrayListOf()
            for (countryId in countryIds) {
                countryDataModels.add(countryRepository.getCountry(countryId))
            }
            countryDataModels
        }
    }

    class Params
}