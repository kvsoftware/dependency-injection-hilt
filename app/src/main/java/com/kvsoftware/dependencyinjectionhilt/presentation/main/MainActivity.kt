package com.kvsoftware.dependencyinjectionhilt.presentation.main

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.kvsoftware.dependencyinjectionhilt.R
import com.kvsoftware.dependencyinjectionhilt.databinding.ActivityMainBinding
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseActivity
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail.CountryDetailActivity
import com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail.CountryDetailViewModel
import com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite.FavoriteFragment
import com.kvsoftware.dependencyinjectionhilt.presentation.main.map.MapFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        const val TAB_FAVORITE_POSITION = 0
        const val TAB_MAP_POSITION = 1
    }

    private val viewModel: MainViewModel by viewModels()

    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initializeView() {
        setupToolbar(title = "Test")
        mainViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewpager.apply {
            adapter = mainViewPagerAdapter
            offscreenPageLimit = 2
            isUserInputEnabled = false
        }
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                TAB_FAVORITE_POSITION -> getString(R.string.activity_main_viewpager_title_favorite)
                TAB_MAP_POSITION -> getString(R.string.activity_main_viewpager_title_map)
                else -> ""
            }
        }.attach()
    }

    override fun initializeObserver() {
    }

    class MainViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                TAB_FAVORITE_POSITION -> FavoriteFragment.newInstance()
                TAB_MAP_POSITION -> MapFragment.newInstance()
                else -> FavoriteFragment.newInstance()
            }
        }
    }

}