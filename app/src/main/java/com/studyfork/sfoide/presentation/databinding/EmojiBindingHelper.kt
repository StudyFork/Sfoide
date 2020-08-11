package com.studyfork.sfoide.presentation.databinding

import com.studyfork.sfoide.R

object EmojiBindingHelper {

    fun getCountryEmoji(country: String): Int {
        return when (country) {
            "AU" -> R.string.au_emoji
            "BR" -> R.string.br_emoji
            "CA" -> R.string.ca_emoji
            "CH" -> R.string.ch_emoji
            "DE" -> R.string.de_emoji
            "DK" -> R.string.dk_emoji
            "ES" -> R.string.es_emoji
            "FI" -> R.string.fi_emoji
            "FR" -> R.string.fr_emoji
            "GB" -> R.string.gb_emoji
            "IE" -> R.string.ie_emoji
            "IR" -> R.string.ir_emoji
            "NO" -> R.string.no_emoji
            "NL" -> R.string.nl_emoji
            "NZ" -> R.string.nz_emoji
            "TR" -> R.string.tr_emoji
            "US" -> R.string.us_emoji
            else -> R.string.us_emoji
        }
    }

    fun getGenderEmoji(gender: String) =
        when (gender) {
            "male" -> R.string.male_emoji
            else -> R.string.female_emoji
        }
}