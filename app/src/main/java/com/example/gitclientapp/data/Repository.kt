package com.example.gitclientapp.data

import com.example.gitclientapp.Contract
import com.example.gitclientapp.domain.UserProfile

class Repository: Contract.RepositoryInterface {

    override fun provideData(): MutableList<UserProfile> {
        return arrayListOf(
            UserProfile("leonskyd"),
            UserProfile("madman"),
            UserProfile("kovalsky"),
            UserProfile("rocketman"),
            UserProfile("borhammere"),
            UserProfile("nikitin"),
            UserProfile("kingston"),
            UserProfile("berliner"),
            )
    }
}