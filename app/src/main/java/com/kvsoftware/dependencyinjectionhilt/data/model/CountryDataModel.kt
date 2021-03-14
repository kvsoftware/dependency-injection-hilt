package com.kvsoftware.dependencyinjectionhilt.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDataModel(
    @SerializedName("country") val country: String,
    @SerializedName("countryInfo") val countryInfo: CountryInfoDataModel,
    @SerializedName("cases") val cases: Int,
    @SerializedName("recovered") val recovered: Int,
    @SerializedName("deaths") val deaths: Int,
    @SerializedName("todayCases") val todayCases: Int,
    @SerializedName("todayRecovered") val todayRecovered: Int,
    @SerializedName("todayDeaths") val todayDeaths: Int
) : Parcelable