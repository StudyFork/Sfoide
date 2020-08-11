package com.studyfork.sfoide.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.SupportMapFragment
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.studyfork.sfoide.R
import com.studyfork.sfoide.data.model.RandomUser
import com.studyfork.sfoide.databinding.FragmentDetailBinding
import com.studyfork.sfoide.presentation.base.BaseFragment
import com.studyfork.sfoide.presentation.extensions.replaceFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail),
    OnMapReadyCallback {
    private val viewModel by viewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        arguments?.getParcelable<RandomUser>(ARG_USER)?.let { user ->
            viewModel.setUser(user)
        }

        val mapFragment = childFragmentManager.replaceFragment(
            R.id.map,
            false,
            tag = TAG_GOOGLE_MAP
        ) as? SupportMapFragment

        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        viewModel.user.observe(this) { user ->
            val userLocation = LatLng(user.latitude.toDouble(), user.longitude.toDouble())

            val marker = MarkerOptions()
                .position(userLocation)
                .title(getString(R.string.hi_emoji))

            map.apply {
                moveCamera(CameraUpdateFactory.newLatLng(userLocation))
                moveCamera(CameraUpdateFactory.zoomTo(DEFAULT_ZOOM))
                addMarker(marker)
            }
        }
    }

    companion object {
        const val ARG_USER = "arg_user"
        private const val DEFAULT_ZOOM = 15f
        private const val TAG_GOOGLE_MAP = "google_map"
    }

}