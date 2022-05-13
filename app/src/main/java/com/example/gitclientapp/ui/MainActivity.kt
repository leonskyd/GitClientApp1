package com.example.gitclientapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gitclientapp.R
import com.example.gitclientapp.ui.UserFragment.Controller
import com.example.gitclientapp.ui.UserFragment.UserFragment
import com.example.gitclientapp.ui.listFragment.LoginListFragment


class MainActivity : AppCompatActivity(), Controller {
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
        //supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, LoginListFragment.newInstance())
            .commitNow()

    }
}