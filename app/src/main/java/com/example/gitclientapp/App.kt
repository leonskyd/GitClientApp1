package com.example.gitclientapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.example.gitclientapp.dependency.AppDependenciesComponent
import com.example.gitclientapp.dependency.DaggerAppDependenciesComponent
import com.example.gitclientapp.dependency.DaggerModule


class App: Application() {
   companion object  {
        lateinit var instance: App
    }
    lateinit var appDependenciesComponent: AppDependenciesComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
            appDependenciesComponent = DaggerAppDependenciesComponent
            .builder()
            .daggerModule(DaggerModule(this))
            .build()
    }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app