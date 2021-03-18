package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.countrydetail.GetFavoriteInteractor
import com.kvsoftware.dependencyinjectionhilt.domain.interactor.countrydetail.SetFavoriteInteractor
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailActivityViewModel @Inject constructor(
    private val getFavoriteInteractor: GetFavoriteInteractor,
    private val setFavoriteInteractor: SetFavoriteInteractor
) : BaseViewModel() {

    val isFavorited: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun getFavoriteStatus(countryDataModel: CountryDataModel) {
        viewModelScope.launch {
            val favorite = getFavoriteInteractor.invoke(GetFavoriteInteractor.Params(countryDataModel.country))
            isFavorited.postValue(favorite)
        }
    }

    fun favorite(countryDataModel: CountryDataModel) {
        viewModelScope.launch {
            val favorite = setFavoriteInteractor.invoke(SetFavoriteInteractor.Params(countryDataModel.country))
            isFavorited.postValue(favorite)
        }
    }

}