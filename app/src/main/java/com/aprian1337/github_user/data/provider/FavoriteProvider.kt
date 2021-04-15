package com.aprian1337.github_user.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.aprian1337.github_user.data.room.AppDatabase
import com.aprian1337.github_user.data.room.FavoriteUserDAO
import com.aprian1337.github_user.utils.Constants

class FavoriteProvider : ContentProvider() {

    companion object {
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private val FAV_ID = 1
        init {
            sUriMatcher.addURI(Constants.AUTHORITY, Constants.TABLE_FAVORITE, FAV_ID)
        }
    }

    private lateinit var favoriteUserDAO: FavoriteUserDAO

    override fun onCreate(): Boolean {
        favoriteUserDAO = context?.let { AppDatabase.getDatabase(it)?.favoriteUserDAO() }!!
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return when (sUriMatcher.match(uri)){
            FAV_ID -> {
                context?.contentResolver?.notifyChange(uri, null)
                favoriteUserDAO.getAllFavConsumer().setNotificationUri(context?.contentResolver, uri)
                favoriteUserDAO.getAllFavConsumer()
            }
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }
}