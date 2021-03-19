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
class CountryDetailFragmentViewModel @Inject constructor(val getHistoricalInteractor: GetHistoricalInteractor) :
    BaseViewModel() {

    val historicalDataModel: MutableLiveData<List<HistoricalDataModel>> by lazy { MutableLiveData<List<HistoricalDataModel>>() }

    fun getHistoricalByCountry(context: Context, country: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = getHistoricalInteractor.invoke(GetHistoricalInteractor.Params(country))
                historicalDataModel.postValue(response)
            } catch (e: Exception) {
                error.postValue(ErrorHelper.getErrorMessage(context, e))
            }
            isLoading.postValue(false)
        }
    }

}