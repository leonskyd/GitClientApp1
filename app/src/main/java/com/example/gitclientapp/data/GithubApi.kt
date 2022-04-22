package com.example.gitclientapp.data

import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user")user:String?)
            : Call<List<GitRepoEntity>>

    @GET("users/{user}")
    fun userDetails(@Path("user")user:String?)
    : Call<UserProfile>


}