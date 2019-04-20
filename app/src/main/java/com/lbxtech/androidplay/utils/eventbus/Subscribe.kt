package com.lbxtech.androidplay.utils.eventbus

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Subscribe(val threadMode: ThreadMode = ThreadMode.MAIN)