package com.studyfork.sfoide.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Friend(
    val id: String,
    val thumbnail: String,
    val name: String,
    val age: Int,
    val gender: String,
    val country: String,
    val email: String,
    val telephone: String,
    val mobilePhone: String,
    val coordinates: Coordinates
) : Parcelable

@Parcelize
class Coordinates(
    val latitude: Double,
    val longitude: Double
) : Parcelable