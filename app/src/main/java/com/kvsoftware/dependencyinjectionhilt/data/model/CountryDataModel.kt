package com.kvsoftware.dependencyinjectionhilt.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDataModel(
    @SerializedName("Country") val country: String,
    @SerializedName("Slug") val slug: String,
    @SerializedName("ISO2") val iso2: String
) : Parcelable