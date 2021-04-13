package com.aprian1337.github_user.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aprian1337.github_user.utils.Constants

@Dao
interface FavoriteUserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFav(favoriteUser : FavoriteUser)

    @Query("SELECT * FROM ${Constants.TABLE_FAVORITE}")
    fun getAllFav(): LiveData<List<FavoriteUser>>

    @Delete
    suspend fun deleteFav(favoriteUser: FavoriteUser)

    @Query("SELECT count(*) FROM ${Constants.TABLE_FAVORITE} WHERE id = :id")
    suspend fun getFavUser(id: Int): Int

}