package com.example.gitclientapp.data

import com.example.gitclientapp.domain.UserProfile

class Repository: RepositoryInterface {

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
interface RepositoryInterface {
    fun provideData(): MutableList<UserProfile>
}