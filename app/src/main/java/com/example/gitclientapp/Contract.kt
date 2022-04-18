package com.example.gitclientapp

import com.example.gitclientapp.domain.UserProfile

class Contract {
    // DATA
    interface RepositoryInterface {
        fun provideData(): MutableList<UserProfile>
    }
    //VIEW
    interface OnItemClickListener {
        fun onItemClick()
    }
}