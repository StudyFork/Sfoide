package com.studyfork.sfoide.data.mapper

import com.studyfork.sfoide.data.model.RandomUser
import com.studyfork.sfoide.data.remote.response.*

object RandomUserMapper {
    fun fromResult(result: Result) =
        RandomUser(
            cell = result.cell ?: "",
            dob = result.dob ?: Dob(),
            email = result.email ?: "",
            gender = result.gender ?: "",
            id = result.id ?: Id(),
            location = result.location ?: Location(),
            login = result.login ?: Login(),
            name = result.name ?: Name(),
            nat = result.nat ?: "",
            phone = result.phone ?: "",
            picture = result.picture ?: Picture(),
            registered = result.registered ?: Registered()
        )
}