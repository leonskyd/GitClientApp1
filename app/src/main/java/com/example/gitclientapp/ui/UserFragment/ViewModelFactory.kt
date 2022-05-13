package com.example.gitclientapp.ui.UserFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitclientapp.data.RetrofitRepositoryInterface
import com.example.gitclientapp.ui.listFragment.LoginListViewModel

class ViewModelFactory (private val repository: RetrofitRepositoryInterface)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginListViewModel::class.java))
            return LoginListViewModel(repository) as T
        else {
            return UserViewModel (repository) as T
        }
    }
}