package com.example.gitclientapp.data

import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user")user:String?)
            : Call<List<GitRepoEntity>>

    @GET("users/{user}")
    fun userDetails(@Path("user")user:String?)
    : Call<UserProfile>

    @GET("users")
    fun getUsersList(
        @Query("since")since: Int?,
        @Query("per_page")size: Int?
    ): Call<List<UserProfile>>
    //"users?since={since}&per_page={per_page}"
}