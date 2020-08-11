package com.studyfork.sfoide.data.remote.response

data class Result(
    val cell: String? = null,
    val dob: Dob? = null,
    val email: String? = null,
    val gender: String? = null,
    val id: Id? = null,
    val location: Location? = null,
    val login: Login? = null,
    val name: Name? = null,
    val nat: String? = null,
    val phone: String? = null,
    val picture: Picture? = null,
    val registered: Registered? = null
)