package com.aprian1337.github_user.ui.detail.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.github_user.api.ApiClient
import com.aprian1337.github_user.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    var following = MutableLiveData<ArrayList<User>>()

    fun setFollowing(username: String){
        CoroutineScope(Dispatchers.IO).launch {
            ApiClient.apiInstance
                .getFollowing(username)
                .enqueue(object : Callback<ArrayList<User>>{
                    override fun onResponse(
                        call: Call<ArrayList<User>>,
                        response: Response<ArrayList<User>>
                    ) {
                        if(response.isSuccessful){
                            following.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                        Log.d("err", t.message.toString())
                    }
                })
        }
    }

    fun getFollowing() : LiveData<ArrayList<User>>{
        return following
    }
}