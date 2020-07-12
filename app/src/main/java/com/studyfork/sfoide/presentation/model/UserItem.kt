package com.studyfork.sfoide.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserItem(
    val profileMedium: String,
    val profileLarge: String,
    val title: String, //이름(나이) 성별 국가
    val phone: String,
    val cell: String,
    val email: String,
    val coordinates: CoordinatesItem
) : Parcelable {

    @Parcelize
    data class CoordinatesItem(
        val latitude: String,
        val longitude: String
    ) : Parcelable
}