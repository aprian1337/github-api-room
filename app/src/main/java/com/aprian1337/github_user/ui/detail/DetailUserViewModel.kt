package com.aprian1337.github_user.ui.detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aprian1337.github_user.data.room.AppDatabase
import com.aprian1337.github_user.data.room.FavoriteUser
import com.aprian1337.github_user.model.User
import com.aprian1337.github_user.repository.MainRepository
import com.aprian1337.github_user.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(private val repository: MainRepository) : ViewModel() {
    val user = MutableLiveData<User>()
    val result = MutableLiveData<Int>()

    fun setUser(username: String) {
        viewModelScope.launch {
            try {
                val repository = repository.getUser(username)
                repository.enqueue(object : Callback<User> {
                    override fun onResponse(
                        call: Call<User>,
                        response: Response<User>
                    ) {
                        if (response.isSuccessful) user.postValue(response.body())
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.d("fail", t.message.toString())
                    }
                })
            }catch (t: Throwable){
                Log.d("throw", t.message.toString())
            }
        }
    }

    fun addFav(favoriteUser: FavoriteUser, context: Context){
        val favDao = AppDatabase.getDatabase(context)?.favoriteUserDAO()
        val repository = favDao?.let { RoomRepository(it) }
        viewModelScope.launch(Dispatchers.IO) {
            if (repository != null) {
                repository.addFav(favoriteUser)
            }
        }
    }

    fun deleteFav(favoriteUser: FavoriteUser, context: Context){
        val favDao = AppDatabase.getDatabase(context)?.favoriteUserDAO()
        val repository = favDao?.let { RoomRepository(it) }
        viewModelScope.launch(Dispatchers.IO) {
            if (repository != null) {
                repository.deleteFav(favoriteUser)
            }
        }
    }

    fun getFav(id: Int, context: Context) : LiveData<Int>{
        val favDao = AppDatabase.getDatabase(context)?.favoriteUserDAO()
        val repository = favDao?.let { RoomRepository(it) }
        viewModelScope.launch(Dispatchers.IO) {
            if (repository != null) {
                result.postValue(repository.getFav(id))
            }
        }
        return result
    }

    fun getUser() : LiveData<User>{
        return user
    }
}