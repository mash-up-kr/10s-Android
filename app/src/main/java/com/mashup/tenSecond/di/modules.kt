package com.mashup.tenSecond.di

import com.google.gson.GsonBuilder
import com.mashup.tenSecond.BuildConfig
import com.mashup.tenSecond.data.model.UserInstance
import com.mashup.tenSecond.data.repository.ApiService
import com.mashup.tenSecond.data.repository.NetworkRemote
import com.mashup.tenSecond.data.repository.RemoteRepository
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


const val BASEURL = "http://ec2-54-180-102-205.ap-northeast-2.compute.amazonaws.com"


val apiModules: Module = module {
    single {
        val gson = GsonBuilder().setLenient().create()

        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val headerInterceptor = Interceptor {
            val original = it.request()
            val request = original.newBuilder()
                .addHeader("Authorization", UserInstance.getUserToken())
                .addHeader("email", UserInstance.getEmail())
                .method(original.method(), original.body())
                .build()
            it.proceed(request)
        }

        val client = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(headerInterceptor).build()
        Retrofit.Builder().apply {
            addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            addConverterFactory(GsonConverterFactory.create(gson))
            client(client)
            baseUrl(BASEURL)
        }.build().create(ApiService::class.java)
    }

    single {
        NetworkRemote(get())
    }

    single {
        RemoteRepository(get())
    }

}


val appModules = listOf(apiModules)

