package com.aprian1337.github_user.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class User(
    var login: String? = "",
    var avatar_url: String? = "",
    var html_url: String?="",
    var name: String? = "",
    var company: String? = "",
    var location: String? = "",
    var email: String? = "",
    var bio: String? = "",
    var blog: String? = "",
    var followers: Int? = 0,
    var following: Int? = 0,
    var public_repos: Int? = 0
)