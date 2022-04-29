package com.example.gitclientapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.gitclientapp.data.RetrofitRepository
import com.example.gitclientapp.dependency.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app