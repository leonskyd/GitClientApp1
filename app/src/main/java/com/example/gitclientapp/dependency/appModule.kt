package com.example.gitclientapp.dependency

import com.example.gitclientapp.data.GithubApi
import com.example.gitclientapp.data.RetrofitRepository
import com.example.gitclientapp.data.RetrofitRepositoryInterface
import com.example.gitclientapp.ui.UserFragment.UserViewModel
import com.example.gitclientapp.ui.listFragment.LoginListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single(named("api_url")) {"https://api.github.com/"}
    single<RetrofitRepositoryInterface>{RetrofitRepository(get())}
    single<GithubApi> {get<Retrofit>().create(GithubApi::class.java)}
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(named("api_url")))
            .addConverterFactory(get())
            .build()}
    //factories
    factory<Converter.Factory>{ GsonConverterFactory.create()}
    //ViewModel
    viewModel {UserViewModel(get())}
    viewModel { LoginListViewModel(get()) }
}