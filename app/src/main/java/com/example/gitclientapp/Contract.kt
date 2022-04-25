package com.example.gitclientapp

import androidx.lifecycle.MutableLiveData
import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile
import io.reactivex.rxjava3.core.Single

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
            username:String
        ) : Single<UserProfile>
    }
    //VIEW
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    interface Controller {
        fun openDetailScreen(login: String)
        fun backToList()
    }
}