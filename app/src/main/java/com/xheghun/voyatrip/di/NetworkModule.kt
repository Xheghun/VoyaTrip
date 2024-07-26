package com.xheghun.voyatrip.di

import com.google.gson.GsonBuilder
import com.xheghun.voyatrip.data.api.TripsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://voyatravel.free.beeceptor.com"

fun networkingModule() = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    } // client


    single { GsonBuilder().create() }


    single { GsonConverterFactory.create(get()) } // gson converter

    single {
        Retrofit.Builder()
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get<OkHttpClient>())
            .baseUrl(BASE_URL)
            .build()
    } // retrofit

    single { get<Retrofit>().create(TripsService::class.java) } // api service
}