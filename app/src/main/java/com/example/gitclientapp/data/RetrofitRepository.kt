package com.example.gitclientapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.gitclientapp.Contract
import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile
import io.reactivex.rxjava3.core.Single
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
                else {
                    Log.d("ERROR", "not Success")
                }
            }
            override fun onFailure(call: Call<List<GitRepoEntity>>, t: Throwable) {
                Log.d("ERROR", "Failed")
            }
        })
    }

    override fun provideDetailsFromWeb(
        username: String
    ): Single<UserProfile> {
        return Single.create { emitter ->
        api.userDetails(username).enqueue(object:Callback<UserProfile>{
            override fun onResponse(
                call: Call<UserProfile>,
                response: Response<UserProfile>
            ) {
                if (response.isSuccessful) {
                    emitter.onSuccess(response.body())
                }
            }
            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                emitter.onError(t)
            }
        })
    }

    }
}