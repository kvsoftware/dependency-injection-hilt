package com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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
            binding.textviewCasesValue.text = it.countryDataModel.cases.toString()
            binding.textviewTodayCasesValue.text = it.countryDataModel.todayCases.toString()
            binding.textviewRecoveredValue.text = it.countryDataModel.recovered.toString()
            binding.textviewTodayRecoveredValue.text = it.countryDataModel.todayRecovered.toString()
            binding.textviewDeathsValue.text = it.countryDataModel.deaths.toString()
            binding.textviewTodayDeathsValue.text = it.countryDataModel.todayDeaths.toString()

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