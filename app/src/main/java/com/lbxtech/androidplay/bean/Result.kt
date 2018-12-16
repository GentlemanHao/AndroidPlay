package com.lbxtech.androidplay.bean

data class Result<T>(val errorCode: Int, val errorMsg: String, val data: T)