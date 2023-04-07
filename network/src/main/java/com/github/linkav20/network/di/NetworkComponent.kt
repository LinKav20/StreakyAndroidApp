package com.github.linkav20.network.di

import com.github.linkav20.network.BuildConfig
import com.github.linkav20.network.api.Api
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface NetworkComponent {
    fun api(): Api
}

@Module
abstract class NetworkModule {
    companion object {
        private const val base_url = "http://localhost:8080/"

        @Provides
        @Singleton
        fun provideApi(): Api =
            Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
                        .writeTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES)
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level =
                                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                else HttpLoggingInterceptor.Level.NONE
                        })
                        .build()
                )
                .build()
                .create(Api::class.java)
    }
}
