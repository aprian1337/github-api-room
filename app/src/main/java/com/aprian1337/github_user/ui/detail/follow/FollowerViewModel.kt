package com.aprian1337.github_user.ui.detail.follow

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

class FollowerViewModel(private val repository: MainRepository) : ViewModel() {
    val follows = MutableLiveData<List<User>>()

    fun setFollowers(username: String) {
        val repository = repository.getFollowers(username)
        viewModelScope.launch {
            repository.enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    if (response.isSuccessful) follows.postValue(response.body())
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }
            })
        }
    }

    fun getFollowers(): LiveData<List<User>> {
        return follows
    }
}