package com.example.assignment_icro_rinku.di

import com.example.assignment_icro_rinku.network.Services
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class NetworkModule {
    private  val HOST = "https://urlhaus-api.abuse.ch/v1/urls/recent/limit/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        // Retrofit object creation
        val okHttpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)



        return Retrofit.Builder().baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    @Singleton
    @Provides
    fun providesCountriesApi(retrofit: Retrofit): Services {
        return retrofit.create(Services::class.java)
    }

}