package com.studyfork.sfoide.data.mapper

import com.studyfork.sfoide.data.model.RandomUser
import com.studyfork.sfoide.data.remote.response.*

object RandomUserMapper {
    fun fromResult(result: Result) =
        RandomUser(
            cell = result.cell ?: "",
            age = result.dob?.age ?: 0,
            email = result.email ?: "",
            gender = result.gender ?: "",
            id = result.id?.value ?: "",
            latitude = result.location?.coordinates?.latitude ?: "",
            longitude = result.location?.coordinates?.longitude ?: "",
            name = result.name?.first ?: "" + result.name?.last ?: "",
            nat = result.nat ?: "",
            phone = result.phone ?: "",
            picture = result.picture?.medium ?: ""
        )
}