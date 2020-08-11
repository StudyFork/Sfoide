package com.studyfork.sfoide.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RandomUser(
    val cell: String,
    val age: Int,
    val email: String,
    val gender: String,
    val id: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val nat: String,
    val phone: String,
    val picture: String
) : Parcelable