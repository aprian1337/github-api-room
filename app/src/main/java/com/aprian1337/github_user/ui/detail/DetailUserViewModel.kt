package com.aprian1337.github_user.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aprian1337.github_user.model.User
import com.aprian1337.github_user.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel constructor(private val repository: MainRepository) : ViewModel() {
    val user = MutableLiveData<User>()

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

    fun getUser(): LiveData<User> {
        return user
    }
}