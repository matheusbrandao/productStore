package com.matheusbrandao.productstore.di

import com.matheusbrandao.productstore.remote.interceptor.AuthInterceptor
import com.matheusbrandao.productstore.remote.service.ProductService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "BASE_URL"

val serviceModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideProductApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
    OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()

fun provideProductApi(retrofit: Retrofit): ProductService =
    retrofit.create(ProductService::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
