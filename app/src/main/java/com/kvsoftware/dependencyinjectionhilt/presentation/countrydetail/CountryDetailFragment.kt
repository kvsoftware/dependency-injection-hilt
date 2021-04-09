package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kvsoftware.dependencyinjectionhilt.R
import com.kvsoftware.dependencyinjectionhilt.databinding.FragmentCountryDetailBinding
import com.kvsoftware.dependencyinjectionhilt.domain.helper.DateHelper
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseFragment
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CountryDetailFragment : BaseFragment<FragmentCountryDetailBinding>(),
    ProvinceAdapter.Listener {

    companion object {
        private const val ARGUMENT = "argument"

        fun newInstance(argument: CountryDetailArgument): CountryDetailFragment {
            val fragment = CountryDetailFragment()
            val args = Bundle()
            args.putParcelable(ARGUMENT, argument)
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: CountryDetailFragmentViewModel by activityViewModels()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCountryDetailBinding =
        FragmentCountryDetailBinding.inflate(inflater, container, false)

    override fun initializeView(context: Context) {
        getArgument()?.let {
            val update = it.countryDataModel.updated
            binding.textviewUpdated.text =
                getString(
                    R.string.fragment_country_detail_tv_updated,
                    if (update != null) DateHelper.toDateString1(update) else "-"
                )
            binding.textviewCasesValue.text =
                getString(R.string.global_number_format, it.countryDataModel.cases)
            binding.textviewTodayCasesValue.text = getString(
                R.string.fragment_country_detail_tv_today,
                it.countryDataModel.todayCases
            )
            binding.textviewRecoveredValue.text =
                getString(R.string.global_number_format, it.countryDataModel.recovered)
            binding.textviewTodayRecoveredValue.text = getString(
                R.string.fragment_country_detail_tv_today,
                it.countryDataModel.todayRecovered
            )
            binding.textviewDeathsValue.text =
                getString(R.string.global_number_format, it.countryDataModel.deaths)
            binding.textviewTodayDeathsValue.text = getString(
                R.string.fragment_country_detail_tv_today,
                it.countryDataModel.todayDeaths
            )

            viewModel.getHistoricalByCountry(context, it.countryDataModel.country)
        }
    }

    override fun initializeObserver(context: Context) {
        viewModel.isLoading.observe(this, {
            binding.progressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.error.observe(this, {
            Log.i("Kv", "error : " + it)
        })
        viewModel.provinceModels.observe(this, {
            binding.textviewProvincesNotFound.visibility =
                if (it.isEmpty()) View.VISIBLE else View.GONE
            binding.recyclerviewProvinces.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter =
                    ProvinceAdapter(context, it).apply { listener = this@CountryDetailFragment }
                isNestedScrollingEnabled = false
            }
        })
    }

    override fun onProvinceClicked(provinceModel: ProvinceModel) {

    }

    private fun getArgument(): CountryDetailArgument? =
        arguments?.getParcelable(ARGUMENT) as CountryDetailArgument?

}