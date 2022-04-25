package com.example.gitclientapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitclientapp.Contract
import com.example.gitclientapp.data.Repository
import com.example.gitclientapp.data.RetrofitRepository
import com.example.gitclientapp.domain.UserProfile

class LoginListViewModel : ViewModel() {
    private var repository: Contract.RepositoryInterface = Repository()
    private val users: List<UserProfile> = loadData()

    fun getUsers(): List<UserProfile> = users

    fun loadData() = repository.provideData()

    }
