package com.mashup.tenSecond.di

import com.google.gson.GsonBuilder
import com.mashup.tenSecond.BuildConfig
import com.mashup.tenSecond.ViewModelFactory
import com.mashup.tenSecond.data.model.UserInstance
import com.mashup.tenSecond.data.repository.remote.ApiService
import com.mashup.tenSecond.data.repository.remote.ChatRemoteDataSource
import com.mashup.tenSecond.data.repository.ChatDataSource
import com.mashup.tenSecond.data.repository.Repository
import com.mashup.tenSecond.util.LogUtil
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


const val BASEURL = "http://ec2-54-180-102-205.ap-northeast-2.compute.amazonaws.com:5000"


val apiModules: Module = module {
    single {
        val gson = GsonBuilder().setLenient().create()

        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        LogUtil.e("retrofit header", "header Token : " + UserInstance.getUserToken())

        val headerInterceptor = Interceptor {
            val original = it.request()
            val request = original.newBuilder()
                .addHeader("Authorization", "Bearer ttttttooookkkkkennnnn")
                .addHeader("email", "userFour@hello.world")
//                .addHeader("Authorization", "bearer ${UserInstance.getUserToken()}")
//                .addHeader("email", UserInstance.getEmail())
                .method(original.method(), original.body())
                .build()
            it.proceed(request)
        }

        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(headerInterceptor)
                .build()
        Retrofit.Builder().apply {
            addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            addConverterFactory(GsonConverterFactory.create(gson))
            client(client)
            baseUrl(BASEURL)
        }.build().create(ApiService::class.java)
    }

    single {
        ChatRemoteDataSource(get())
    }

    single {
        ChatDataSource(get()) as Repository
    }

}

val modelFactoryModules: Module = module {
    single {
        ViewModelFactory(get(), androidApplication())
    }

}


val appModules = listOf(apiModules, modelFactoryModules)

