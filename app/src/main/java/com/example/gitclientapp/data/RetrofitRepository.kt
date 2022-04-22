package com.example.gitclientapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.gitclientapp.Contract
import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response

const val BASE_URL = "https://api.github.com/"

class RetrofitRepository: Contract.RetrofitRepositoryInterface{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: GithubApi = retrofit.create(GithubApi::class.java)

    override fun provideReposFromWeb(
        username:String, liveData: MutableLiveData<List<GitRepoEntity>>) {
        api.listRepos(username).enqueue(object : Callback<List<GitRepoEntity>> {
            override fun onResponse(
                call: Call<List<GitRepoEntity>>,
                response: Response<List<GitRepoEntity>>
            ) {
                if (response.isSuccessful) {
                    liveData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<GitRepoEntity>>, t: Throwable) {
                Log.e("Repositories not shown !", t.message.toString())
            }
        })
    }

    override fun provideDetailsFromWeb(
        username: String,
        liveData: MutableLiveData<UserProfile>
    ) {
        api.userDetails(username).enqueue(object:Callback<UserProfile>{
            override fun onResponse(
                call: Call<UserProfile>,
                response: Response<UserProfile>
            ) {
                if (response.isSuccessful) {
                    liveData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                Log.e("Details not provided !", t.message.toString())
            }

        })
    }
}