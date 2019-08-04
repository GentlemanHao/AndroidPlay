package com.lbxtech.androidplay.utils.http

import java.util.concurrent.*

object ThreadPoolManager {

    private val workQueue = LinkedBlockingDeque<Runnable>()

    private val delayQueue = DelayQueue<HttpTask<*>>()

    private val threadPoolExecutor = ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS,
        ArrayBlockingQueue<Runnable>(4), RejectedExecutionHandler { runnable, _ ->
        addTask(runnable)
    })

    private val coreThread = Runnable {
        while (true) {
            threadPoolExecutor.execute(workQueue.take())
        }
    }

    private val delayThread = Runnable {
        while (true) {
            val httpTask = delayQueue.take()
            if (httpTask.retryCount < 3) {
                threadPoolExecutor.execute(httpTask)
                httpTask.retryCount++
            }
        }
    }

    init {
        threadPoolExecutor.execute(coreThread)
        threadPoolExecutor.execute(delayThread)
    }

    fun addTask(runnable: Runnable) {
        workQueue.add(runnable)
    }

    fun addDelayTask(task: HttpTask<*>) {
        task.setDelayTime(5000L)
        delayQueue.offer(task)
    }

}