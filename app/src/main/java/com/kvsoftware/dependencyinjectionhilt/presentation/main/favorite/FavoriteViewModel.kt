package com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kvsoftware.dependencyinjectionhilt.domain.helper.ErrorHelper
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.GetCountriesInteractor
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val getCountriesInteractor: GetCountriesInteractor) :
    BaseViewModel() {

    val favorites by lazy { MutableLiveData<ArrayList<FavoriteModel>>() }

    fun getFavorites(context: Context) {
        isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = getCountriesInteractor.invoke(GetCountriesInteractor.Params())
                favorites.postValue(response)
            } catch (e: Exception) {
                error.postValue(ErrorHelper.getErrorMessage(context, e))
            }
        }
        isLoading.postValue(false)
    }
}