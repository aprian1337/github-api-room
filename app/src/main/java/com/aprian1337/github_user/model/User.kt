package com.aprian1337.github_user.model

data class User(
    val id : Int,
    val login : String,
    val html_url : String,
    val avatar_url: String,
    val followers_url: String,
    val name: String,
    val company: String,
    val location: String,
    val following: Int,
    val followers: Int,
    val public_repos: Int
)