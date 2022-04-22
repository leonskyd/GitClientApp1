package com.example.gitclientapp

import androidx.lifecycle.MutableLiveData
import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile

class Contract  {
    // DATA
    interface RepositoryInterface {
        fun provideData(): MutableList<UserProfile>
    }
    //Retrofit
    interface RetrofitRepositoryInterface {
        fun provideReposFromWeb(
            username:String,
            liveData: MutableLiveData<List<GitRepoEntity>>
        )
        fun provideDetailsFromWeb(
            username:String,
            liveData: MutableLiveData<UserProfile>
        )
    }
    //VIEW
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}