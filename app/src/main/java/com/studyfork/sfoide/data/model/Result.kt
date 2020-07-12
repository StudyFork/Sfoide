package com.studyfork.sfoide.data.model

import com.google.gson.annotations.SerializedName
import com.studyfork.sfoide.presentation.model.UserItem

data class Result(
    @SerializedName("gender")
    val gender: String,
    @SerializedName("name")
    val name: Name,
    @SerializedName("location")
    val location: Location,
    @SerializedName("email")
    val email: String,
    @SerializedName("login")
    val login: Login,
    @SerializedName("dob")
    val dob: Dob,
    @SerializedName("registered")
    val registered: Registered,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("cell")
    val cell: String,
    @SerializedName("id")
    val id: Id,
    @SerializedName("picture")
    val picture: Picture,
    @SerializedName("nat")
    val nat: String
) {
    data class Name(
        @SerializedName("title")
        val title: String,
        @SerializedName("first")
        val first: String,
        @SerializedName("last")
        val last: String
    )

    data class Location(
        @SerializedName("street")
        val street: Street,
        @SerializedName("city")
        val city: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("postcode")
        val postcode: String,
        @SerializedName("coordinates")
        val coordinates: Coordinates,
        @SerializedName("timezone")
        val timezone: Timezone
    ) {
        data class Street(
            @SerializedName("number")
            val number: Int,
            @SerializedName("name")
            val name: String
        )

        data class Coordinates(
            @SerializedName("latitude")
            val latitude: String,
            @SerializedName("longitude")
            val longitude: String
        )

        data class Timezone(
            @SerializedName("offset")
            val offset: String,
            @SerializedName("description")
            val description: String
        )
    }

    data class Login(
        @SerializedName("uuid")
        val uuid: String,
        @SerializedName("username")
        val username: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("salt")
        val salt: String,
        @SerializedName("md5")
        val md5: String,
        @SerializedName("sha1")
        val sha1: String,
        @SerializedName("sha256")
        val sha256: String
    )

    data class Dob(
        @SerializedName("date")
        val date: String,
        @SerializedName("age")
        val age: Int
    )

    data class Registered(
        @SerializedName("date")
        val date: String,
        @SerializedName("age")
        val age: Int
    )

    data class Id(
        @SerializedName("name")
        val name: String,
        @SerializedName("value")
        val value: Any
    )

    data class Picture(
        @SerializedName("large")
        val large: String,
        @SerializedName("medium")
        val medium: String,
        @SerializedName("thumbnail")
        val thumbnail: String
    )
}

fun List<Result>.mapToPresentation(): List<UserItem> {
    return map {
        UserItem(
            profile = it.picture.thumbnail,
            //이름(나이) 성별 국가
            title = "${it.name}(${it.dob.age}) ${it.gender} ${it.location.country}",
            phone = it.phone,
            cell = it.cell,
            email = it.email,
            coordinates = UserItem.CoordinatesItem(
                latitude = it.location.coordinates.latitude,
                longitude = it.location.coordinates.longitude
            )
        )
    }
}
