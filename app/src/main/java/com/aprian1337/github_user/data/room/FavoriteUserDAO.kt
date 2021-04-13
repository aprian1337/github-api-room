package com.aprian1337.github_user.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteUserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFav(favoriteUser : FavoriteUser)

    @Query("SELECT * FROM fav_user")
    fun loadAllFav(): LiveData<List<FavoriteUser>>

    @Delete
    suspend fun deleteFav(favoriteUser: FavoriteUser)

    @Query("SELECT count(*) FROM fav_user WHERE id = :id")
    suspend fun getFavUser(id: Int): Int

}