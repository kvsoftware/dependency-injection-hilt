package com.kvsoftware.dependencyinjectionhilt.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoritesDataModel(var countries: ArrayList<String> = arrayListOf()) : Parcelable