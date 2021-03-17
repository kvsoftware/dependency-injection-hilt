package com.kvsoftware.dependencyinjectionhilt.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected lateinit var binding: T

    abstract fun getViewModel(): BaseViewModel?

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T

    open fun initializeView(context: Context) {}

    open fun initializeObserver(context: Context) {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { it ->
            initializeView(it)
            initializeObserver(it)
        }
    }

    fun replaceFragment(id: Int, fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(id, fragment)
            .commit()
    }

    fun removeFragment(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commitNow()
    }

    fun removeFragmentById(id: Int) {
        val fragment = childFragmentManager.findFragmentById(id)
        fragment?.let { removeFragment(it) }
    }

}