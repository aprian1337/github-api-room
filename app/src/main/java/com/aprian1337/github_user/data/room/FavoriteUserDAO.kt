package com.aprian1337.github_user.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteUserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFav(username: String, avatar_url: String)

    @Query("SELECT * FROM fav_user")
    fun loadAllFav(): LiveData<List<FavoriteUser>>
//    @Query("SELECT * FROM user")
//    fun getAll(): List<User>
//
//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg username: FavoriteUser)

//    @Delete
//    fun delete(user: User)
}