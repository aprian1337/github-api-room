package com.aprian1337.consumerapp.utils

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aprian1337.consumerapp.data.database.DatabaseContract
import com.aprian1337.consumerapp.data.model.FavoriteUser

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): LiveData<List<FavoriteUser>> {
        val liveData = MutableLiveData<List<FavoriteUser>>()
        val favoriteList = mutableListOf<FavoriteUser>()
        cursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns._ID))
                val login = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.LOGIN))
                val avatarUrl = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR_URL))
                favoriteList.add(FavoriteUser(id,login,avatarUrl))
            }
            liveData.postValue(favoriteList)
        }
        return liveData
    }
}