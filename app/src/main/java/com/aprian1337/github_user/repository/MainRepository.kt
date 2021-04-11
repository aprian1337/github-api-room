package com.aprian1337.github_user.repository

import com.aprian1337.github_user.data.api.ApiClient

class MainRepository constructor(private val apiService: ApiClient){
    fun getSearchUser(query: String) = apiService.api.getSearchUsers(query)
    fun getUser(username: String) = apiService.api.getUser(username)
    fun getFollowing(username: String) = apiService.api.getFollowing(username)
    fun getFollowers(username: String) = apiService.api.getFollowers(username)
}