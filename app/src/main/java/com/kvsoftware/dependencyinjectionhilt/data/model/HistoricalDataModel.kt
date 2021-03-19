package com.kvsoftware.dependencyinjectionhilt.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoricalDataModel(
    @SerializedName("country") val country: String,
    @SerializedName("province") val province: String?,
    @SerializedName("timeline") val timeline: TimelineDataModel
) : Parcelable