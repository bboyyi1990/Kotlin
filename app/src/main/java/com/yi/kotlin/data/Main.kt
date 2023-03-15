package com.yi.kotlin.data

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * create by yi on 2023/3/10
 */
suspend fun main(args: Array<String>) {
    println("main method -> start ${currentThreadIs()}")
//    GlobalScope.cancel()
//    runBlocking {
    val launchHandler = GlobalScope.launch {
        println(" launch -> begin ${currentThreadIs()}")
        delay(1000)
//            Thread.sleep(1000)
        println(" launch -> finish ${currentThreadIs()}")
    }
    val asyncHandler = GlobalScope.async() {
        println(" async -> begin ${currentThreadIs()}")
        println(" async -> finish ${currentThreadIs()}")
        "1"
    }
//        launchHandler.join()
    println("async result is ${asyncHandler.await()} ${currentThreadIs()} ")
//    }
//    val runBlockHandler = runBlocking(Dispatchers.IO) {
//        println("runBlocking launch -> begin ${currentThreadIs()}")
////        delay(2000)
//        Thread.sleep(1000)
//        println("runBlocking launch -> finish ${currentThreadIs()}")
//    }

//    scopeLaunchHandler.invokeOnCompletion { }
//    scopeLaunchHandler.join()
//    runBlockHandler
//    asyncHandler

    Thread.sleep(5000)
    println("main method -> end ${currentThreadIs()}")
}

suspend fun currentThreadIs() =
    "| thread is ${Thread.currentThread().name} time is ${System.currentTimeMillis() / 1000}"