package com.aprian1337.consumerapp.data.database

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {

    const val AUTHORITY = "com.aprian1337.github_user"
    const val SCHEME = "content"

    class FavoriteColumns : BaseColumns {

        companion object {
            const val TABLE_NAME = "fav_user"
            const val _ID = "id"
            const val AVATAR_URL = "avatar_url"
            const val LOGIN = "login"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }

    }
}