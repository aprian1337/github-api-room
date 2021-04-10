package com.aprian1337.github_user.utils

import com.aprian1337.github_user.BuildConfig

class Constants {
    companion object{
        const val BASE_URL = "https://api.github.com"
        const val ENDPOINT_SEARCH = "/search/users?"
        const val ENDPOINT_DETAIL = "/users/{username}"
        const val ENDPOINT_DETAIL_FOLLOWERS = "/users/{username}/followers"
        const val ENDPOINT_DETAIL_FOLLOWING = "/users/{username}/following"
        const val AUTH_TOKEN = BuildConfig.AUTH_TOKEN
    }
}