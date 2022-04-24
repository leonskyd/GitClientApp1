package com.example.gitclientapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.gitclientapp.data.RetrofitRepository

class App: Application() {
    val webRepository: Contract.RetrofitRepositoryInterface by lazy { RetrofitRepository() }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app