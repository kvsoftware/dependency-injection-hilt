package com.kvsoftware.dependencyinjectionhilt.presentation.main.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

    inner class ViewHolder(val binding: AdapterFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteModel: FavoriteModel) {
            binding.textviewTest.text = favoriteModel.name
        }
    }
}