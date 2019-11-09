package com.lbxtech.androidplay.api

import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.bean.HomeData
import com.lbxtech.androidplay.bean.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServer {
    @GET("banner/json")
    fun getBanner(): Observable<Result<List<Banner>>>

    @GET("article/list/{page}/json")
    fun getHomeList(@Path("page") page: Int): Observable<Result<HomeData>>
}