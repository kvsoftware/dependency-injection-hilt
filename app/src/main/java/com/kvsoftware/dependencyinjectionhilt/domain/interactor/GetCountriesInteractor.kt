package com.kvsoftware.dependencyinjectionhilt.domain.interactor

import com.kvsoftware.dependencyinjectionhilt.data.rest.repository.CountryRepository
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.base.BaseInteractor
import com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite.FavoriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class GetCountriesInteractor @Inject constructor(private val countryRepository: CountryRepository) :
    BaseInteractor<ArrayList<FavoriteModel>, GetCountriesInteractor.Params>() {

    override suspend fun invoke(params: Params): ArrayList<FavoriteModel> {
        return withContext(Dispatchers.IO) {

            val countries = countryRepository.getDriverData()

            ArrayList<FavoriteModel>().apply {
                add(FavoriteModel("Test1"))
                add(FavoriteModel("Test2"))
            }
        }
    }

    class Params
}