package com.example.gitclientapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitclientapp.Contract
import com.example.gitclientapp.data.RetrofitRepository
import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile

class UserViewModel(
    private var webRepository: Contract.RetrofitRepositoryInterface
    ) : ViewModel() {
    lateinit var liveDataList: MutableLiveData<List<GitRepoEntity>>
    lateinit var detailsLiveDataList: MutableLiveData<UserProfile>
    init {
        liveDataList = MutableLiveData()
        detailsLiveDataList = MutableLiveData()
    }
    fun getLiveDataObserver(): MutableLiveData<List<GitRepoEntity>> = liveDataList
    fun getdetailsLiveDataObserver(): MutableLiveData<UserProfile> = detailsLiveDataList

    fun makeCall(username: String) {
        webRepository.provideReposFromWeb(username,liveDataList)
        webRepository.provideDetailsFromWeb(username,detailsLiveDataList)
    }

}