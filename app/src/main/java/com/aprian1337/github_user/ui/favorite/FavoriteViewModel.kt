package com.aprian1337.github_user.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.github_user.data.room.AppDatabase
import com.aprian1337.github_user.data.room.FavoriteUser
import com.aprian1337.github_user.repository.RoomRepository

class FavoriteViewModel() : ViewModel(){

    private lateinit var readAllData : LiveData<List<FavoriteUser>>

    fun setFavDatas(context: Context){
        val favDao = AppDatabase.getDatabase(context)?.favoriteUserDAO()
        val repository = favDao?.let { RoomRepository(it) }
        if (repository != null) {
            readAllData = repository.loadAllFav
        }
    }

    fun getFavDatas() : LiveData<List<FavoriteUser>> = readAllData
}