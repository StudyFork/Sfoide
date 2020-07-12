package com.studyfork.sfoide.data.mapper

import com.studyfork.sfoide.data.model.CoordinatesData
import com.studyfork.sfoide.data.model.FriendData
import com.studyfork.sfoide.data.remote.response.FriendsResponse

fun FriendsResponse.Result.toEntity(): FriendData {
    return FriendData(
        id = this.login?.uuid ?: "",
        thumbnail = this.picture?.large ?: "",
        name = this.name.toString(),
        age = this.dob?.age ?: 0,
        gender = this.gender ?: "",
        country = this.nat ?: "",
        email = this.email ?: "",
        telephone = this.phone ?: "",
        mobilePhone = this.cell ?: "",
        coordinatesData = CoordinatesData(
            latitude = this.location?.coordinates?.latitude?.toDouble() ?: 0.0,
            longitude = this.location?.coordinates?.longitude?.toDouble() ?: 0.0
        )
    )
}