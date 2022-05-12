package com.example.gitclientapp.dependency

import android.content.Context
import com.example.gitclientapp.data.GithubApi
import com.example.gitclientapp.data.RetrofitRepository
import com.example.gitclientapp.data.RetrofitRepositoryInterface
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class DaggerModule(val context: Context) {

    @Singleton
    @Provides
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitRepo(api: GithubApi): RetrofitRepositoryInterface {
        return RetrofitRepository(api)
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideBaseUrl(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()

    }
}

/*val appModule = module {
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
}*/