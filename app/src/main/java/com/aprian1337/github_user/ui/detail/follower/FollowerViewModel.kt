package com.aprian1337.github_user.ui.detail.follower

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

class FollowerViewModel : ViewModel() {
    var following = MutableLiveData<ArrayList<User>>()

    fun setFollower(username: String){
        CoroutineScope(Dispatchers.IO).launch {
            ApiClient.apiInstance
                .getFollowers(username)
                .enqueue(object : Callback<ArrayList<User>>{
                    override fun onResponse(
                        call: Call<ArrayList<User>>,
                        response: Response<ArrayList<User>>
                    ) {
                        println(response)
                        if(response.isSuccessful){
                            println("${response.body()} BIAASALAAAAAAAAAAAAAAAAAH")
                            following.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                        Log.d("err", t.message.toString())
                    }
                })
        }
    }

    fun getFollower() : LiveData<ArrayList<User>>{
        return following
    }
}