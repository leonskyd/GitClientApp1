package com.example.gitclientapp.ui.listFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitclientapp.data.Repository
import com.example.gitclientapp.data.RepositoryInterface
import com.example.gitclientapp.data.RetrofitRepositoryInterface
import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile

class LoginListViewModel (
    private var webRepository: RetrofitRepositoryInterface
        ) : ViewModel() {
    lateinit var usersListLiveData: MutableLiveData<List<UserProfile>>
    init {
        usersListLiveData = MutableLiveData()
    }
    fun getUsersLiveDataObserver(): MutableLiveData<List<UserProfile>> = usersListLiveData
    fun getUsersListFromApi(sinceNumber: Int) {
        val listSize = 10
        webRepository.provideUsersFromWeb(sinceNumber,listSize,usersListLiveData)

    }

    private var repository: RepositoryInterface = Repository()
    private val users: List<UserProfile> = loadData()
    fun getUsers(): List<UserProfile> = users

    fun loadData() = repository.provideData()

    }
