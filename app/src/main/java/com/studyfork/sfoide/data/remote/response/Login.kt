package com.studyfork.sfoide.data.remote.response

data class Login(
    val md5: String? = null,
    val password: String? = null,
    val salt: String? = null,
    val sha1: String? = null,
    val sha256: String? = null,
    val username: String? = null,
    val uuid: String? = null
)