package com.studyfork.sfoide.data.remote.response

data class Location(
    val city: String? = null,
    val coordinates: Coordinates? = null,
    val country: String? = null,
    val postcode: String? = null,
    val state: String? = null,
    val street: Street? = null,
    val timezone: Timezone? = null
)