package com.aprian1337.github_user.api

import com.aprian1337.github_user.model.DetailResponse
import com.aprian1337.github_user.model.User
import com.aprian1337.github_user.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET
    @Headers("Authorization: token ${Constants.AUTH_TOKEN}")
    fun getUsers(
    ) : Call<UserResponse>

    @GET(Constants.ENDPOINT_SEARCH)
    @Headers("Authorization: token ${Constants.AUTH_TOKEN}")
    fun getSearchUsers(
        @Query("q") query : String
    ) : Call<UserResponse>

    @GET(Constants.ENDPOINT_DETAIL)
    @Headers("Authorization: token ${Constants.AUTH_TOKEN}")
    fun getDetail(
        @Path("username") username: String
    ) : Call<DetailResponse>

    @GET(Constants.ENDPOINT_DETAIL_FOLLOWING)
    @Headers("Authorization: token ${Constants.AUTH_TOKEN}")
    fun getFollowing(
        @Path("username") username: String
    ) : Call<ArrayList<User>>

    @GET(Constants.ENDPOINT_DETAIL_FOLLOWERS)
    @Headers("Authorization: token ${Constants.AUTH_TOKEN}")
    fun getFollowers(
        @Path("username") username: String
    ) : Call<ArrayList<User>>
}