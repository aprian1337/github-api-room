package com.aprian1337.github_user.data.api

import com.aprian1337.github_user.model.User
import com.aprian1337.github_user.model.UserResponse
import com.aprian1337.github_user.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.ENDPOINT_SEARCH)
    @Headers("Authorization: token ${Constants.AUTH_TOKEN}")
    fun getSearchUsers(
        @Query("q") query : String,
        @Query("per_page") per_page : Int = 50
    ) : Call<UserResponse>

    @GET(Constants.ENDPOINT_DETAIL)
    @Headers("Authorization: token ${Constants.AUTH_TOKEN}")
    fun getUser(
        @Path("username") username: String
    ) : Call<User>


    @GET(Constants.ENDPOINT_DETAIL_FOLLOWING)
    @Headers("Authorization: token ${Constants.AUTH_TOKEN}")
    fun getFollowing(
        @Path("username") username: String
    ) : Call<List<User>>

    @GET(Constants.ENDPOINT_DETAIL_FOLLOWERS)
    @Headers("Authorization: token ${Constants.AUTH_TOKEN}")
    fun getFollowers(
        @Path("username") username: String
    ) : Call<List<User>>
}