package com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite

data class FavoriteModel(
    val countryName: String,
    val cases: Int,
    val recovered: Int,
    val deaths: Int
)