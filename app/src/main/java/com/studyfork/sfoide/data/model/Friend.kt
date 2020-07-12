package com.studyfork.sfoide.data.model

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
)

class Coordinates(
    val latitude: String,
    val longitude: String
)