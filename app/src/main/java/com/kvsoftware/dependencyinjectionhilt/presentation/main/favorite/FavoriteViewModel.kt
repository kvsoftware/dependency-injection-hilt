package com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import com.kvsoftware.dependencyinjectionhilt.domain.helper.ErrorHelper
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.main.GetFavoriteCountriesInteractor
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val getFavoriteCountriesInteractor: GetFavoriteCountriesInteractor) :
    BaseViewModel() {

    val favorites by lazy { MutableLiveData<ArrayList<CountryDataModel>>() }

    fun getFavorites(context: Context) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response =
                    getFavoriteCountriesInteractor.invoke(GetFavoriteCountriesInteractor.Params())
                favorites.postValue(response)
            } catch (e: Exception) {
                error.postValue(ErrorHelper.getErrorMessage(context, e))
            }
            isLoading.postValue(false)
        }
    }
}