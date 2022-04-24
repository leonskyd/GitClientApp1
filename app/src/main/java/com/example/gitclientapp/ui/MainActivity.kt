package com.example.gitclientapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gitclientapp.Contract
import com.example.gitclientapp.R

class MainActivity : AppCompatActivity(), Contract.Controller {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, LoginListFragment.newInstance())
                .commitNow()
        }
    }

    override fun openDetailScreen(login: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, UserFragment.newInstance(login))
            .commitNow()
    }

    override fun backToList() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, LoginListFragment.newInstance())
            .commitNow()
    }
}