package com.example.gitclientapp

import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile

class Contract {
    // DATA
    interface RepositoryInterface {
        fun provideData(): MutableList<UserProfile>
    }
    interface RetrofitRepositoryInterface {
        fun provideDataFromWeb(username:String)
    }
    //VIEW
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}