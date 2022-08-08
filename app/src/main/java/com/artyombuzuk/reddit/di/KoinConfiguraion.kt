package com.artyombuzuk.reddit.di

import android.content.Context
import com.artyombuzuk.reddit.data.RedditApi
import com.artyombuzuk.reddit.data.RedditRepositoryImpl
import com.artyombuzuk.reddit.domain.RedditRepository
import com.artyombuzuk.reddit.domain.RedditUseCase
import com.artyombuzuk.reddit.domain.RedditUseCaseImpl
import com.artyombuzuk.reddit.view.viewmodel.MainListViewModel
import com.artyombuzuk.reddit.view.viewmodel.RedditPostDetailsViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.reddit.com/"

fun createKoinConfiguration(context: Context) {
    startKoin {
        androidContext(context)
        androidLogger(Level.ERROR)
        modules(
            listOf(
                createNetworkModule(),
                createRepositoryModule(),
                createUseCaseModule(),
                createViewModelModule()
            )
        )
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideApi(retrofit: Retrofit): RedditApi {
    return retrofit.create(RedditApi::class.java)
}

fun createNetworkModule() = module {
    factory { provideOkHttpClient()}
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }
}

fun createRepositoryModule() = module {
    single<RedditRepository> { RedditRepositoryImpl(get()) }
}

fun createUseCaseModule() = module {
    single<RedditUseCase> { RedditUseCaseImpl(get()) }
}

fun createViewModelModule() = module {
    viewModel { MainListViewModel(get()) }
    viewModel { RedditPostDetailsViewModel(get()) }
}