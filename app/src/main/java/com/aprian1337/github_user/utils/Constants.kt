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
        const val TABLE_FAVORITE = "fav_user"
        const val HOUR_TIME = 9
        const val MINUTE_TIME = 0
        const val SECOND_TIME = 0
        const val ID_REPEATING = 101
    }
}