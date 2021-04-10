package com.aprian1337.github_user.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aprian1337.github_user.model.User
import com.aprian1337.github_user.model.UserResponse
import com.aprian1337.github_user.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel constructor(private val repository: MainRepository) : ViewModel() {
    val users = MutableLiveData<List<User>>()

    fun setSearchUsers(query: String) {
        val response = repository.getSearchUser(query)
        viewModelScope.launch {
            try {
                response.enqueue(object : Callback<UserResponse> {
                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        if (response.isSuccessful) users.postValue(response.body()?.items)
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Log.d("fail", t.message.toString())
                    }
                })
            } catch (t: Throwable) {
                Log.d("err", t.message.toString())
            }
        }
    }

    fun getSearchUser(): LiveData<List<User>> {
        return users
    }
}