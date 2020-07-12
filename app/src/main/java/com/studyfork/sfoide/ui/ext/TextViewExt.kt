package com.studyfork.sfoide.ui.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.studyfork.sfoide.R
import com.studyfork.sfoide.ui.model.Friend

private const val MALE = "male"

@BindingAdapter("app:nameAndAge")
fun TextView.setNameAndAge(friend: Friend) {
    text = "${friend.name}(${friend.age})"
}

@BindingAdapter("app:gender")
fun TextView.setGender(gender: String) {
    text = if (gender == MALE) {
        context.resources.getString(R.string.male)
    } else {
        context.resources.getString(R.string.female)
    }
}

@BindingAdapter("app:country")
fun TextView.setCountry(country: String) {
    text = when (country) {
        "AU" -> context.resources.getString(R.string.au)
        "BR" -> context.resources.getString(R.string.br)
        "CA" -> context.resources.getString(R.string.ca)
        "CH" -> context.resources.getString(R.string.ch)
        "DE" -> context.resources.getString(R.string.de)
        "DK" -> context.resources.getString(R.string.dk)
        "ES" -> context.resources.getString(R.string.es)
        "FI" -> context.resources.getString(R.string.fi)
        "FR" -> context.resources.getString(R.string.fr)
        "GB" -> context.resources.getString(R.string.gb)
        "IE" -> context.resources.getString(R.string.ie)
        "IR" -> context.resources.getString(R.string.ir)
        "NO" -> context.resources.getString(R.string.no)
        "NL" -> context.resources.getString(R.string.nl)
        "NZ" -> context.resources.getString(R.string.nz)
        "TR" -> context.resources.getString(R.string.tr)
        "US" -> context.resources.getString(R.string.us)
        else -> ""
    }
}

@BindingAdapter("app:email")
fun TextView.setEmail(email: String) {
    text = "${context.resources.getString(R.string.email)} $email"
}

@BindingAdapter("app:mobile")
fun TextView.setMobile(mobile: String) {
    text = "${context.resources.getString(R.string.mobile_phone)} $mobile"
}

@BindingAdapter("app:telephone")
fun TextView.setTelephone(telephone: String) {
    text = "${context.resources.getString(R.string.telephone)} $telephone"
}