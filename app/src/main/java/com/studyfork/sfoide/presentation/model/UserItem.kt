package com.studyfork.sfoide.presentation.model

data class UserItem(
    val profile: String,
    val title: String, //이름(나이) 성별 국가
    val phone: String,
    val cell: String,
    val email: String,
    val coordinates: CoordinatesItem
) {
    data class CoordinatesItem(
        val latitude: String,
        val longitude: String
    )
}