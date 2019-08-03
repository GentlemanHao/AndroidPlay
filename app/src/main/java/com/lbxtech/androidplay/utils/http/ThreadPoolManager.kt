package com.lbxtech.androidplay.utils.http

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.RejectedExecutionHandler
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object ThreadPoolManager {

    private val workQueue = LinkedBlockingDeque<Runnable>()

    private val threadPoolExecutor = ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS,
        ArrayBlockingQueue<Runnable>(4), RejectedExecutionHandler { runnable, _ ->
        addTask(runnable)
    })

    private val coreThread = Runnable {
        while (true) {
            threadPoolExecutor.execute(workQueue.take())
        }
    }

    init {
        threadPoolExecutor.execute(coreThread)
    }

    fun addTask(runnable: Runnable) {
        workQueue.add(runnable)
    }

}