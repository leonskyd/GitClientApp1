package com.example.gitclientapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitclientapp.Contract
import com.example.gitclientapp.data.RetrofitRepository
import com.example.gitclientapp.domain.GitRepoEntity
import com.example.gitclientapp.domain.UserProfile
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class UserViewModel(
    private var webRepository: Contract.RetrofitRepositoryInterface
    ) : ViewModel() {
    lateinit var liveDataList: MutableLiveData<List<GitRepoEntity>>
    lateinit var detailsLiveDataList: MutableLiveData<UserProfile>
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    init {
        liveDataList = MutableLiveData()
        detailsLiveDataList = MutableLiveData()
    }
    fun getLiveDataObserver(): MutableLiveData<List<GitRepoEntity>> = liveDataList
    fun getdetailsLiveDataObserver(): MutableLiveData<UserProfile> = detailsLiveDataList

    fun makeCall(username: String) {
        webRepository.provideReposFromWeb(username,liveDataList)
        compositeDisposable.add(
            webRepository
                .provideDetailsFromWeb(username)
                .subscribeBy{
                    detailsLiveDataList.postValue(it)
                }

        )

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}