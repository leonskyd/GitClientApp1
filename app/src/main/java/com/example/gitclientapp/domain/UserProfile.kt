package com.example.gitclientapp.domain

data class UserProfile(
    val login: String?,
    val name: String? = null,
    val location: String? = null,
    val avatar_url: String? = null
)
