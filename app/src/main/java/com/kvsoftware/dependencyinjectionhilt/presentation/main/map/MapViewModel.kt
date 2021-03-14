package com.kvsoftware.dependencyinjectionhilt.presentation.main.map

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import com.kvsoftware.dependencyinjectionhilt.domain.helper.ErrorHelper
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.GetCountriesInteractor
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val getCountriesInteractor: GetCountriesInteractor) :
    BaseViewModel() {

    val countries: MutableLiveData<List<CountryDataModel>> by lazy { MutableLiveData<List<CountryDataModel>>() }

    fun getCountries(context: Context) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = getCountriesInteractor.invoke(GetCountriesInteractor.Params())
                countries.postValue(response)
            } catch (e: Exception) {
                error.postValue(ErrorHelper.getErrorMessage(context, e))
            }
            isLoading.postValue(false)
        }

    }
}