package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kvsoftware.dependencyinjectionhilt.R
import com.kvsoftware.dependencyinjectionhilt.databinding.AdapterProvinceBinding
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseRecyclerViewAdapter

class ProvinceAdapter constructor(context: Context, items: ArrayList<ProvinceModel>) :
    BaseRecyclerViewAdapter<ProvinceModel, ProvinceAdapter.ViewHolder>(context, items) {

    interface Listener {
        fun onProvinceClicked(provinceModel: ProvinceModel)
    }

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterProvinceBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: AdapterProvinceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(provinceModel: ProvinceModel) {
            binding.root.setOnClickListener {
                listener?.onProvinceClicked(provinceModel)
            }
            binding.textviewProvince.text = provinceModel.province
            binding.textviewCases.text =
                context.getString(R.string.adapter_province_tv_cases, provinceModel.cases)
            binding.textviewRecovered.text =
                context.getString(
                    R.string.adapter_province_tv_recovered,
                    provinceModel.recovered
                )
            binding.textviewDeaths.text =
                context.getString(R.string.adapter_province_tv_deaths, provinceModel.deaths)
        }

    }
}