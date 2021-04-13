package com.aprian1337.github_user.repository

import androidx.lifecycle.LiveData
import com.aprian1337.github_user.data.room.FavoriteUser
import com.aprian1337.github_user.data.room.FavoriteUserDAO

class RoomRepository(private val favUserDao : FavoriteUserDAO) {
    val loadAllFav : LiveData<List<FavoriteUser>> = favUserDao.getAllFav()

    suspend fun addFav(favoriteUser :FavoriteUser){
        favUserDao.addFav(favoriteUser)
    }

    suspend fun getFav(id: Int) : Int = favUserDao.getFavUser(id)

    suspend fun deleteFav(favoriteUser: FavoriteUser){
        favUserDao.deleteFav(favoriteUser)
    }

}