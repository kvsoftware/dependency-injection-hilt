package com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import com.kvsoftware.dependencyinjectionhilt.databinding.FragmentFavoriteBinding
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseFragment
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail.CountryDetailActivity
import com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail.CountryDetailArgument
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(), FavoriteAdapter.Listener {

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
    }

    override fun initializeObserver(context: Context) {
        viewModel.isLoading.observe(this, {
            binding.progressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.error.observe(this, {
        })
        viewModel.favorites.observe(this, {
            binding.recyclerview.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = FavoriteAdapter(context, it).apply { listener = this@FavoriteFragment }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        context?.let { viewModel.getFavorites(it) }
    }

    override fun onCountryClicked(countryDataModel: CountryDataModel) {
        context?.let { CountryDetailActivity.start(it, CountryDetailArgument(countryDataModel)) }
    }

}