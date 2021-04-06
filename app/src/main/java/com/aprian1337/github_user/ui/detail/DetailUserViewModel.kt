package com.aprian1337.github_user.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aprian1337.github_user.api.ApiClient
import com.aprian1337.github_user.model.DetailResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    val user = MutableLiveData<DetailResponse>()

    fun setDetail(username: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ApiClient.apiInstance
                    .getDetail(username)
                    .enqueue(object: Callback<DetailResponse>{
                        override fun onResponse(
                            call: Call<DetailResponse>,
                            response: Response<DetailResponse>
                        ) {
                            if(response.isSuccessful){
                                println("AAAAAAAAAAZZZZ")
                                println(response.body().toString())
                                user.postValue(response.body())
                            }
                        }

                        override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                            Log.d("err", t.message.toString())
                        }

                    })
            }catch (t: Throwable){
                Log.d("err", t.message.toString())
            }

        }
    }

    fun getDetail(): LiveData<DetailResponse>{
        return user
    }
}