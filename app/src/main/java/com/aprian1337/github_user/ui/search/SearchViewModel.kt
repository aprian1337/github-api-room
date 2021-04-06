package com.aprian1337.github_user.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.github_user.api.ApiClient
import com.aprian1337.github_user.model.User
import com.aprian1337.github_user.model.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    val listUser = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ApiClient.apiInstance
                    .getSearchUsers(query)
                    .enqueue(object : Callback<UserResponse> {
                        override fun onResponse(
                            call: Call<UserResponse>,
                            response: Response<UserResponse>
                        ) {
                            if (response.isSuccessful) {
                                listUser.postValue(response.body()?.items)
                            }
                        }

                        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                            Log.d("err", t.message.toString())
                        }

                    })
            }catch (t: Throwable){
                Log.d("err", t.message.toString())
            }

        }

    }
    fun getSearchUsers(): LiveData<ArrayList<User>> {
        return listUser
    }
}