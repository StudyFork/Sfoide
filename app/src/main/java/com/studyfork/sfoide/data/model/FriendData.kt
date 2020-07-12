package com.studyfork.sfoide.data.model

data class FriendData(
    val id: String,
    val thumbnail: String,
    val name: String,
    val age: Int,
    val gender: String,
    val country: String,
    val email: String,
    val telephone: String,
    val mobilePhone: String,
    val coordinatesData: CoordinatesData
)

class CoordinatesData(
    val latitude: Double,
    val longitude: Double
)