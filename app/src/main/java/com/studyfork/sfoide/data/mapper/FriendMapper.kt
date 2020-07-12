package com.studyfork.sfoide.data.mapper

import com.studyfork.sfoide.data.model.Coordinates
import com.studyfork.sfoide.data.model.Friend
import com.studyfork.sfoide.data.remote.response.FriendsResponse

fun FriendsResponse.Result.toEntity(): Friend {
    return Friend(
        id = this.login?.uuid ?: "",
        thumbnail = this.picture?.large ?: "",
        name = this.name.toString(),
        age = this.dob?.age ?: 0,
        gender = this.gender ?: "",
        country = this.location?.country ?: "'",
        email = this.email ?: "",
        telephone = this.phone ?: "",
        mobilePhone = this.cell ?: "",
        coordinates = Coordinates(
            latitude = this.location?.coordinates?.latitude?.toDouble() ?: 0.0,
            longitude = this.location?.coordinates?.longitude?.toDouble() ?: 0.0
        )
    )
}