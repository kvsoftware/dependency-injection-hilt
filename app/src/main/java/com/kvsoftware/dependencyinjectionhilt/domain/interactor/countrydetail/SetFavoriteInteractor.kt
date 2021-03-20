package com.kvsoftware.dependencyinjectionhilt.domain.interactor.countrydetail

import com.kvsoftware.dependencyinjectionhilt.data.model.FavoritesDataModel
import com.kvsoftware.dependencyinjectionhilt.data.sharepref.SharedPreferences
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.base.BaseInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetFavoriteInteractor @Inject constructor(private val sharedPreferences: SharedPreferences) :
    BaseInteractor<Boolean, SetFavoriteInteractor.Params>() {

    override suspend fun invoke(params: Params): Boolean {
        return withContext(Dispatchers.IO) {
            val favoritesDataModel = sharedPreferences.favoritesDataModel ?: FavoritesDataModel(arrayListOf())
            val id = favoritesDataModel.countries.find { it == params.country }
            if (id == null) {
                favoritesDataModel.countries.add(params.country)
                sharedPreferences.favoritesDataModel = favoritesDataModel
                true
            } else {
                favoritesDataModel.countries.remove(id)
                sharedPreferences.favoritesDataModel = favoritesDataModel
                false
            }
        }
    }

    class Params(val country: String)
}