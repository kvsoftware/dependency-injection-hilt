package com.kvsoftware.dependencyinjectionhilt.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TimelineDataModel(
    @SerializedName("cases") val cases: Map<String, String>,
    @SerializedName("deaths") val deaths: Map<String, String>,
    @SerializedName("recovered") val recovered: Map<String, String>
) : Parcelable