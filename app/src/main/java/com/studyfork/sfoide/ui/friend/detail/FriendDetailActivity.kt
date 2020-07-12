package com.studyfork.sfoide.ui.friend.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.studyfork.sfoide.R
import com.studyfork.sfoide.data.model.Friend
import com.studyfork.sfoide.databinding.ActivityFriendDetailBinding

class FriendDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityFriendDetailBinding

    private var friend: Friend? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend_detail)
        binding.lifecycleOwner = this

        initMap()

        friend = intent.getParcelableExtra<Friend>(EXTRA_FRIEND)
        binding.friend = this.friend
    }

    private fun initMap() {
        val mapFragment =
            (supportFragmentManager.findFragmentById(R.id.map_friend_detail) as? SupportMapFragment)
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        friend?.coordinates?.let {
            val coordinates = LatLng(it.latitude, it.longitude)
            map?.addMarker(
                MarkerOptions()
                    .position(coordinates)
            )

            map?.moveCamera(CameraUpdateFactory.newLatLng(coordinates))
        }
    }

    companion object {

        const val EXTRA_FRIEND = "EXTRA_FRIEND"
        fun createIntent(context: Context, friend: Friend): Intent {
            return Intent(context, FriendDetailActivity::class.java).apply {
                putExtra(EXTRA_FRIEND, friend)
            }
        }
    }
}