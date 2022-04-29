package com.example.gitclientapp.ui.listFragment

import androidx.lifecycle.ViewModel
import com.example.gitclientapp.data.Repository
import com.example.gitclientapp.data.RepositoryInterface
import com.example.gitclientapp.domain.UserProfile

class LoginListViewModel : ViewModel() {
    private var repository: RepositoryInterface = Repository()
    private val users: List<UserProfile> = loadData()

    fun getUsers(): List<UserProfile> = users

    fun loadData() = repository.provideData()

    }
