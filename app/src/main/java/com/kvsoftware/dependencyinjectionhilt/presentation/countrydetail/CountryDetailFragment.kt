package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.kvsoftware.dependencyinjectionhilt.R
import com.kvsoftware.dependencyinjectionhilt.databinding.FragmentCountryDetailBinding
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseFragment
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailFragment : BaseFragment<FragmentCountryDetailBinding>() {

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
            binding.imageviewFlag.downloadImage("", it.countryDataModel.countryInfo.flag)
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
    }

    private fun getArgument(): CountryDetailArgument? =
        arguments?.getParcelable(ARGUMENT) as CountryDetailArgument?

}