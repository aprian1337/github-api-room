package com.aprian1337.github_user.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_user")
data class FavoriteUser(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    val login: String,
    val avatar_url: String
)