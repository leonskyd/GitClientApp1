package com.example.gitclientapp.domain

data class UserProfile(
    val login: String?,
    val name: String? = null,
    val location: String? = null,
    val avatar: String? = null,
    val repos: List<String>? = null
)
