package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import android.os.Parcelable
import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDetailArgument(val countryDataModel: CountryDataModel) : Parcelable