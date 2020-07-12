package com.studyfork.sfoide.data.model


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("info")
    val info: Info
) {
    data class Info(
        @SerializedName("seed")
        val seed: String,
        @SerializedName("results")
        val results: Int,
        @SerializedName("page")
        val page: Int,
        @SerializedName("version")
        val version: String
    )
}