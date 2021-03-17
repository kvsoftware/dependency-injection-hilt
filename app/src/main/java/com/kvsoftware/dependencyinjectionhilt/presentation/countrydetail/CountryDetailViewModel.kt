package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import androidx.lifecycle.MutableLiveData
import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import com.kvsoftware.dependencyinjectionhilt.data.model.FavoritesDataModel
import com.kvsoftware.dependencyinjectionhilt.data.sharepref.SharedPreferences
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    BaseViewModel() {

    val isFavorited: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun initialize(countryDataModel: CountryDataModel) {
        val country =
            sharedPreferences.favoritesDataModel?.countries?.find { it == countryDataModel.country }
        isFavorited.postValue(country != null)
    }

    fun favorite(countryDataModel: CountryDataModel) {
        val favoritesDataModel =
            sharedPreferences.favoritesDataModel ?: FavoritesDataModel(arrayListOf())
        val id = favoritesDataModel.countries.find { it == countryDataModel.country }

        if (id == null) {
            favoritesDataModel.countries.add(countryDataModel.country)
            isFavorited.postValue(true)
        } else {
            favoritesDataModel.countries.remove(id)
            isFavorited.postValue(false)
        }

        sharedPreferences.favoritesDataModel = favoritesDataModel
    }

}
