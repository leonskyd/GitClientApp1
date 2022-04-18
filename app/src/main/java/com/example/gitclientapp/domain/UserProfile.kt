package com.example.gitclientapp.domain

data class UserProfile(
    val login: String?,
    val name: String?,
    val location: String?,
    val avatar: String?,
    val repos: List<String>?
)
