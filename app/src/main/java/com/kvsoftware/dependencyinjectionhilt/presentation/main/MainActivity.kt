package com.kvsoftware.dependencyinjectionhilt.presentation.main

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kvsoftware.dependencyinjectionhilt.databinding.ActivityMainBinding
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseActivity
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite.FavoriteFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initializeView() {
        mainViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.apply {
            adapter = mainViewPagerAdapter
            offscreenPageLimit = 2
        }
    }

    override fun initializeObserver() {
    }

    class MainViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return FavoriteFragment.newInstance()
        }
    }

}