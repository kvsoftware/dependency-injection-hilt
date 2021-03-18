package com.kvsoftware.dependencyinjectionhilt.domain.interactor.countrydetail

import com.kvsoftware.dependencyinjectionhilt.data.sharepref.SharedPreferences
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.base.BaseInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoriteInteractor @Inject constructor(private val sharedPreferences: SharedPreferences) :
    BaseInteractor<Boolean, GetFavoriteInteractor.Params>() {

    override suspend fun invoke(params: Params): Boolean {
        return withContext(Dispatchers.IO) {
            val country = sharedPreferences.favoritesDataModel?.countries?.find { it == params.country }
            country != null
        }
    }

    class Params(val country: String)
}