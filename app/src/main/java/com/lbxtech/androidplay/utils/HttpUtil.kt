package com.lbxtech.androidplay.utils

import android.os.Environment
import android.util.Log
import com.lbxtech.androidplay.api.ApiServer
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object HttpUtil {

    private val okHttpClient: OkHttpClient by lazy { initOkHttp() }

    val api: ApiServer by lazy { initRetrofit() }

    private fun initOkHttp(): OkHttpClient {
        Log.d("--wh--", "${Environment.getExternalStorageDirectory()}/AndroidPlayCache")
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .cache(Cache(File("${Environment.getExternalStorageDirectory()}/AndroidPlayCache/"), 10 * 1024 * 1024))
            //.addInterceptor(getOfflineCacheInterceptor())
            //.addNetworkInterceptor(getNetCacheInterceptor())
            .build()
    }

    private fun initRetrofit() = Retrofit.Builder()
        .baseUrl("http://wanandroid.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiServer::class.java)


    private fun getNetCacheInterceptor() = Interceptor { chain ->
        Log.d("--wh--", "getNetCacheInterceptor")
        val response = chain.proceed(chain.request())
        val cacheTime = 60
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$cacheTime")
            .removeHeader("Pragma")
            .build()
    }

    private fun getOfflineCacheInterceptor() = Interceptor {chain ->
        Log.d("--wh--", "getOfflineCacheInterceptor")
        var request = chain.request()
        if (!NetworkUtil.isNetworkConnected()) {
            Log.d("--wh--", "offline true")
            val cacheTime = 600
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$cacheTime")
                .build()
        }
        chain.proceed(request)
    }

}