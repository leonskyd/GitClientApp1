package com.example.gitclientapp.data

import com.example.gitclientapp.Contract
import com.example.gitclientapp.domain.UserProfile

class Repository: Contract.RepositoryInterface {

    override fun provideData(): MutableList<UserProfile> {
        return arrayListOf(
            UserProfile("leonskyd","Denis Leonskii", "Finland",
                "https://avatars.githubusercontent.com/u/72574009?v=4",
                listOf("Android-Lessons", "App1", "Geekbrains")),
            UserProfile("madman", "Yuriy Prokopets","Ukraine",
                "https://avatars.githubusercontent.com/u/1180279?v=4",
                listOf("docker-webgrind","bchat","cryo")),
            UserProfile("kovalsky","Max Fetisov","Ukraine",
                "https://avatars.githubusercontent.com/u/19157953?v=4",
            listOf()),
            UserProfile("rocketman","Jim Mason", null,
                "https://avatars.githubusercontent.com/u/6860356?v=4",
                listOf("firefox-nimbus-theme", "cpulimit","gftp-1")),
            UserProfile("borhammere","Kirill Shalnov", "Nizhniy Novgorod",
                "https://avatars.githubusercontent.com/u/2115286?v=4",
                listOf("BottomSheetPickers","cardiojournal","ColorFragment")
            )
        )
    }
}