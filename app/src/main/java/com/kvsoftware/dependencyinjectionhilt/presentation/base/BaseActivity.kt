package com.kvsoftware.dependencyinjectionhilt.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    abstract fun getViewModel(): BaseViewModel?

    abstract fun getViewBinding(): T

    open fun initializeView() {}

    open fun initializeObserver() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDataBinding()
        initializeView()
        initializeViewModel()
        initializeObserver()
    }

    private fun initializeDataBinding() {
        binding = getViewBinding()
        setContentView(binding.root)
    }

    private fun initializeViewModel() {
        getViewModel()?.initialize()
        getViewModel()?.initialize(this)
    }

}