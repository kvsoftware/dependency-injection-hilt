package com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kvsoftware.dependencyinjectionhilt.R
import com.kvsoftware.dependencyinjectionhilt.databinding.AdapterFavoriteBinding
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseRecyclerViewAdapter

class FavoriteAdapter(
    context: Context, items: ArrayList<FavoriteModel>
) : BaseRecyclerViewAdapter<FavoriteModel, FavoriteAdapter.ViewHolder>(context, items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterFavoriteBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: AdapterFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteModel: FavoriteModel) {
            binding.textviewCountry.text = favoriteModel.countryName
            binding.textviewCases.text =
                context.getString(R.string.adapter_favorite_tv_cases, favoriteModel.cases)
            binding.textviewRecovered.text =
                context.getString(R.string.adapter_favorite_tv_recovered, favoriteModel.recovered)
            binding.textviewDeaths.text =
                context.getString(R.string.adapter_favorite_tv_deaths, favoriteModel.deaths)
        }
    }
}