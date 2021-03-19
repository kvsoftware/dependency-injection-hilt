package com.kvsoftware.dependencyinjectionhilt.presentation.main.map

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ui.IconGenerator
import com.kvsoftware.dependencyinjectionhilt.R
import com.kvsoftware.dependencyinjectionhilt.data.model.CountryDataModel
import com.kvsoftware.dependencyinjectionhilt.databinding.FragmentMapBinding
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseMapFragment
import com.kvsoftware.dependencyinjectionhilt.presentation.base.BaseViewModel
import com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail.CountryDetailActivity
import com.kvsoftware.dependencyinjectionhilt.presentation.countrydetail.CountryDetailArgument
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseMapFragment<FragmentMapBinding>() {

    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

    private val viewModel: MapViewModel by viewModels()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMapBinding = FragmentMapBinding.inflate(inflater, container, false)

    override fun getMapResourceId(): Int = R.id.map

    override fun initializeObserver(context: Context) {
        viewModel.isLoading.observe(this, {
            binding.progressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.error.observe(this, {
        })
        viewModel.countries.observe(this, {
            for (country in it) {
                val latLng = LatLng(country.countryInfo.lat, country.countryInfo.lng)
                val marker = mGoogleMap
                    .addMarker(
                        MarkerOptions()
                            .position(latLng)
                            .icon(BitmapDescriptorFactory.fromBitmap(getIcon(country.cases)))
                    )
                marker.tag = country
            }
        })
    }

    override fun onMapInitialized(context: Context) {
        viewModel.getCountries(context)
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        p0?.let { marker ->
            context?.let { context ->
                showCardViewCountry(context, marker.tag as CountryDataModel)
            }
        }
        return false
    }

    override fun onMapClick(p0: LatLng?) {
        binding.cardviewCountry.visibility = View.GONE
    }

    private fun getIcon(cases: Int): Bitmap {
        val markerView: View = layoutInflater.inflate(R.layout.layout_marker, null)
        markerView.findViewById<TextView>(R.id.textview_cases)?.text = cases.toString()
        val iconGen = IconGenerator(context).apply {
            setContentView(markerView)
        }
        return iconGen.makeIcon()
    }

    private fun showCardViewCountry(context: Context, countryDataModel: CountryDataModel) {
        binding.cardviewCountry.visibility = View.VISIBLE
        binding.cardviewCountry.setOnClickListener {
            CountryDetailActivity.start(
                context,
                CountryDetailArgument(countryDataModel)
            )
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