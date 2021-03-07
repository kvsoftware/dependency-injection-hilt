package com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kvsoftware.dependencyinjectionhilt.databinding.FragmentFavoriteBinding
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseFragment
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val viewModel: FavoriteViewModel by viewModels()

    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)

    override fun initializeView(context: Context) {
        viewModel.getFavorites(context)
    }

    override fun initializeObserver(context: Context) {
        viewModel.isLoading.observe(this, {
            binding.progressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.error.observe(this, {
            Log.i("Kv", "Error : " + it)
        })
        viewModel.favorites.observe(this, {
            binding.recyclerview.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = FavoriteAdapter(context, it)
            }
        })
    }

}