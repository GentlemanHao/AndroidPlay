package com.lbxtech.androidplay.api

import com.lbxtech.androidplay.bean.Banner
import com.lbxtech.androidplay.bean.Result
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiServer {
    @GET("banner/json")
    fun getBanner(): Observable<Result<List<Banner>>>
}