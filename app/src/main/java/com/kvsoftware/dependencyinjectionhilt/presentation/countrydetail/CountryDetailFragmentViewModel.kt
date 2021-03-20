package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kvsoftware.dependencyinjectionhilt.data.model.HistoricalDataModel
import com.kvsoftware.dependencyinjectionhilt.domain.helper.ErrorHelper
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.countrydetail.GetHistoricalInteractor
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CountryDetailFragmentViewModel @Inject constructor(private val getHistoricalInteractor: GetHistoricalInteractor) :
    BaseViewModel() {

    val provinceModels: MutableLiveData<ArrayList<ProvinceModel>> by lazy { MutableLiveData<ArrayList<ProvinceModel>>() }

    fun getHistoricalByCountry(context: Context, country: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = getHistoricalInteractor.invoke(GetHistoricalInteractor.Params(country))
                provinceModels.postValue(response)
            } catch (e: Exception) {
                error.postValue(ErrorHelper.getErrorMessage(context, e))
            }
            isLoading.postValue(false)
        }
    }

}