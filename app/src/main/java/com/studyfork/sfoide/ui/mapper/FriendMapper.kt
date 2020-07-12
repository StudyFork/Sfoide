package com.studyfork.sfoide.ui.mapper

import com.studyfork.sfoide.data.model.FriendData
import com.studyfork.sfoide.ui.model.Coordinates
import com.studyfork.sfoide.ui.model.Friend

fun FriendData.toPresentation(): Friend {
    return Friend(
        id = this.id,
        thumbnail = this.thumbnail,
        name = this.name,
        age = this.age,
        gender = this.gender,
        country = this.country,
        email = this.email,
        telephone = this.telephone,
        mobilePhone = this.mobilePhone,
        coordinates = Coordinates(
            latitude = this.coordinatesData.latitude,
            longitude = this.coordinatesData.longitude
        )
    )
}