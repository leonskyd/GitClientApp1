package com.example.gitclientapp.data

import androidx.lifecycle.MutableLiveData
import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile
import io.reactivex.rxjava3.core.Single

interface RetrofitRepositoryInterface {

        fun provideReposFromWeb(
            username:String,
            liveData: MutableLiveData<List<GitRepoEntity>>
        )
        fun provideDetailsFromWeb(
            username:String
        ) : Single<UserProfile>
    }
