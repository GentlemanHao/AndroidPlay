package com.lbxtech.androidplay.utils.eventbus

import android.os.Handler
import android.os.Looper
import java.lang.RuntimeException
import java.util.concurrent.Executors

object EventBus {

    private val eventMap by lazy { HashMap<Any, List<SubscribeMethod>>() }

    private val handler by lazy { Handler(Looper.getMainLooper()) }

    private val executorService by lazy { Executors.newCachedThreadPool() }

    fun register(context: Any) {
        val list = eventMap[context] ?: findSubscribeMethod(context)
        eventMap[context] = list
    }

    fun unRegister(context: Any) {
        eventMap.remove(context)
    }

    private fun findSubscribeMethod(context: Any): List<SubscribeMethod> {
        val list = ArrayList<SubscribeMethod>()
        var clazz: Class<*>? = context.javaClass

        while (clazz != null) {

            if (clazz.name.startsWith("java.") || clazz.name.startsWith("javax.") || clazz.name.startsWith("android.")) break

            val methods = clazz.methods

            for (method in methods) {
                val subscribe = method.getAnnotation(Subscribe::class.java) ?: continue
                val types = method.parameterTypes
                if (types.size != 1) {
                    throw RuntimeException("EventBus only allow one parameter")
                }
                list.add(SubscribeMethod(method, subscribe.threadMode, types[0]))
            }

            clazz = clazz.superclass
        }

        return list
    }

    fun post(type: Any) {
        val iterator = eventMap.keys.iterator()
        while (iterator.hasNext()) {
            val any = iterator.next()
            val list = eventMap[any] ?: continue

            for (method in list) {
                if (method.type.isAssignableFrom(type.javaClass)) {
                    when (method.threadMode) {
                        ThreadMode.MAIN -> {
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                invoke(method, any, type)
                            } else {
                                handler.post { invoke(method, any, type) }
                            }
                        }

                        ThreadMode.BG -> {
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                executorService.execute { invoke(method, any, type) }
                            } else {
                                invoke(method, any, type)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun invoke(method: SubscribeMethod, any: Any, type: Any) {
        method.method.invoke(any, type)
    }

}