package com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kvsoftware.dependencyinjectionhilt.R
import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import com.kvsoftware.dependencyinjectionhilt.databinding.AdapterFavoriteBinding
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseRecyclerViewAdapter

class FavoriteAdapter(
    context: Context, items: ArrayList<CountryDataModel>
) : BaseRecyclerViewAdapter<CountryDataModel, FavoriteAdapter.ViewHolder>(context, items) {

    interface Listener {
        fun onCountryClicked(countryDataModel: CountryDataModel)
    }

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterFavoriteBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: AdapterFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(countryDataModel: CountryDataModel) {
            binding.root.setOnClickListener {
                listener?.onCountryClicked(countryDataModel)
            }
            binding.imageviewFlag.downloadImage("", countryDataModel.countryInfo.flag)
            binding.textviewCases.text =
                context.getString(R.string.adapter_favorite_tv_cases, countryDataModel.cases)
            binding.textviewRecovered.text =
                context.getString(
                    R.string.adapter_favorite_tv_recovered,
                    countryDataModel.recovered
                )
            binding.textviewDeaths.text =
                context.getString(R.string.adapter_favorite_tv_deaths, countryDataModel.deaths)
        }

    }
}