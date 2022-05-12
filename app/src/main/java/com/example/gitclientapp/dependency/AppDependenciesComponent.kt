package com.example.gitclientapp.dependency

import com.example.gitclientapp.ui.MainActivity
import com.example.gitclientapp.ui.UserFragment.UserFragment
import com.example.gitclientapp.ui.listFragment.LoginListFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules=[DaggerModule::class])

interface AppDependenciesComponent {
    fun inject(activity: MainActivity)
    fun inject(loginListFragment: LoginListFragment)
    fun inject(userFragment: UserFragment)
}