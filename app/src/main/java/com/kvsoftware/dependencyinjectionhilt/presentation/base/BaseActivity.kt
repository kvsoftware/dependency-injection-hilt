package com.kvsoftware.dependencyinjectionhilt.presentation.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.kvsoftware.dependencyinjectionhilt.R

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
        initializeObserver()
        initializeViewModel()
    }

    protected fun setupToolbar(
        title: String? = null,
        showBackButton: Boolean = false,
        imageResOption1: Int? = null,
        imageResOption2: Int? = null,
        option1ClickListener: View.OnClickListener? = null,
        option2ClickListener: View.OnClickListener? = null
    ) {
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.apply {
            contentInsetStartWithNavigation = 0
            findViewById<TextView>(R.id.textview_title)?.apply {
                title?.let { text = it }
            }
            findViewById<ImageView>(R.id.imageview_back)?.apply {
                visibility = if (showBackButton) View.VISIBLE else View.GONE
                setOnClickListener { onBackPressed() }
            }
            findViewById<ImageView>(R.id.imageview_option_1)?.apply {
                imageResOption1?.let { setImageResource(it) }
                option1ClickListener?.let { setOnClickListener(it) }
            }
            findViewById<ImageView>(R.id.imageview_option_2)?.apply {
                imageResOption2?.let { setImageResource(it) }
                option2ClickListener?.let { setOnClickListener(it) }
            }
        }
    }

    fun getOptionMenu1(): ImageView? {
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        return toolbar.findViewById(R.id.imageview_option_1)
    }

    fun getOptionMenu2(): ImageView? {
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        return toolbar.findViewById(R.id.imageview_option_2)
    }

    private fun initializeDataBinding() {
        binding = getViewBinding()
        setContentView(binding.root)
    }

    private fun initializeViewModel() {
        getViewModel()?.initialize()
        getViewModel()?.initialize(this)
    }

    protected fun replaceFragment(id: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(id, fragment)
            .commit()
    }

}