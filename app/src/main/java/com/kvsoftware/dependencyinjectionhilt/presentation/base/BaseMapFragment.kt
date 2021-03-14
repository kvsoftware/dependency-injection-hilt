package com.kvsoftware.dependencyinjectionhilt.presentation.base

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

abstract class BaseMapFragment<T : ViewBinding> : BaseFragment<T>(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    protected lateinit var mGoogleMap: GoogleMap

    abstract fun getMapResourceId(): Int

    abstract fun onMapInitialized(context: Context)

    override fun initializeView(context: Context) {
        initializeMap()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        mGoogleMap.setOnMarkerClickListener(this)
        context?.let { onMapInitialized(it) }
    }

    private fun initializeMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(getMapResourceId()) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
}