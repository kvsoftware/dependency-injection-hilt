package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProvinceModel(
    val province: String,
    val cases: Int,
    val recovered: Int,
    val deaths: Int
) : Parcelable