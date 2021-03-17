package com.kvsoftware.dependencyinjectionhilt.data.sharepref

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.kvsoftware.dependencyinjectionhilt.data.model.FavoritesDataModel

class SharedPreferences(context: Context) {

    companion object {
        private const val PREF_FAVORITES = "pref_favorites"
    }

    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    var favoritesDataModel: FavoritesDataModel? = null
        get() {
            return Gson().fromJson(
                preferences.getString(PREF_FAVORITES, null),
                FavoritesDataModel::class.java
            )
        }
        set(value) {
            field = value
            preferences.edit().putString(PREF_FAVORITES, Gson().toJson(value)).apply()
        }

    fun clear() {
        favoritesDataModel = null
    }
}