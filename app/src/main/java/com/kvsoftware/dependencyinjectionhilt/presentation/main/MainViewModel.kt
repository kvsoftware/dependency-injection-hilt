package com.kvsoftware.dependencyinjectionhilt.presentation.main

import android.util.Log
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    override fun initialize() {
        Log.i("Kv", "test")
    }
}