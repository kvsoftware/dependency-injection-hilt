package com.kvsoftware.dependencyinjectionhilt.presentation.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isSuccess: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val error: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    open fun initialize() {}

    open fun initialize(context: Context) {}

}