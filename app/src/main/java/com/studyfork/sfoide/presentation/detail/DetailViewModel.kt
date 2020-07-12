package com.studyfork.sfoide.presentation.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.studyfork.sfoide.base.BaseViewModel
import com.studyfork.sfoide.presentation.model.UserItem

class DetailViewModel(
    val userItem: UserItem
) : BaseViewModel(), OnMapReadyCallback {

    fun goToCall(context: Context) {
        context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + userItem.cell)))
    }

    fun goToPhone(context: Context) {
        context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + userItem.phone)))
    }

    fun goToEmail(context: Context) {
        val email = Intent(Intent.ACTION_SEND);
        email.type = "plain/Text";
        context.startActivity(email)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        userItem.coordinates.let {
            val coordinates = LatLng(it.latitude, it.longitude)

            val makerOptions = MarkerOptions()
            makerOptions.position(coordinates)

            googleMap.addMarker(makerOptions)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 4f))
        }
    }
}