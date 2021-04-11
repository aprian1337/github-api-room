package com.aprian1337.github_user.repository

import androidx.lifecycle.LiveData
import com.aprian1337.github_user.data.room.FavoriteUser
import com.aprian1337.github_user.data.room.FavoriteUserDAO

class RoomRepository(private val favUserDao : FavoriteUserDAO) {
    val loadAllFav : LiveData<List<FavoriteUser>> = favUserDao.loadAllFav()

    suspend fun addFav(username: String, avatar_url: String){
        favUserDao.addFav(username, avatar_url)
    }

}