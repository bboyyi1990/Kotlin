package com.yi.kotlin.uitl

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * @author Yi
 * @date 2020/4/17
 */
class Main {
    companion object {

        /**
         * 阻塞式携程
         */
//        @JvmStatic
//        fun main(args: Array<String>) {
//            GlobalScope.launch {
//                println("before delay")
//                delay(1000L)
//                println("after delay")
//            }
//            println("execute main")
//            runBlocking {
//                println("run blocking before delay")
//                delay(2000L)
//                println("run blocking after delay")
//            }
//        }

        /**
         * block 桥接
         */
//        @JvmStatic
//        fun main(args: Array<String>) {
//            runBlocking {
//                println("enter run block ,thread is ${Thread.currentThread().name}")
//                val job = GlobalScope.launch {
//                    println("before delay ,thread is ${Thread.currentThread().name}")
//                    delay(1000L)
//                    println("after delay")
//                }
//
//                launch {
//                    println("launch block ,thread is ${Thread.currentThread().name}")
//                }.join()
//
//                println("run blocking before delay")
//                delay(2000L)
//                job.join()
//                println("run blocking after delay")
//                println("run blocking after join")
//
//                delay(2000L)
//                println("run blocking after join delay")
//            }
//        }

        /**
         * 调用挂起函数
         */
//        @JvmStatic
//        fun main(args: Array<String>) = runBlocking {
//            launch { doWorld() }
//            println("Hello,")
//        }
//
//        private suspend fun doWorld() {
//            delay(1000L)
//            println("World")
//        }

        /**
         * 取消携程执行
         */
//        @JvmStatic
//        fun main(args: Array<String>) = runBlocking {
//            var job = launch {
//                repeat(6) { i ->
//                    println("Time is ${Date().seconds} ,job: I'm sleeping $i ...")
//                    delay(500L)
//                }
//            }
//            delay(1300L) // 延迟一段时间
//            println("Time is ${Date().seconds} ,main: I'm tired of waiting!")
//            job.cancel() // 取消该作业
//            job.join() // 等待作业执行结束
//            println("Time is ${Date().seconds} ,main: Now I can quit.")
//        }

        /**
         * 使计算 代码可取消
         */
//        @JvmStatic
//        fun main(args: Array<String>) = runBlocking {
//            val startTime = System.currentTimeMillis()
//            val job = launch(Dispatchers.Default) {
//                var nextPrintTime = startTime
//                var i = 0
//                while (isActive) { // 可以被取消的计算循环
//                    // 每秒打印消息两次
//                    if (System.currentTimeMillis() >= nextPrintTime) {
//                        println("job: I'm sleeping ${i++} ...")
//                        nextPrintTime += 500L
//                    }
//                }
//            }
//            delay(1300L) // 等待一段时间
//            println("main: I'm tired of waiting!")
//            job.cancelAndJoin() // 取消该作业并等待它结束
//            println("main: Now I can quit.")
//        }

        /**
         * 运行不能取消的代码块
         */
//        @JvmStatic
//        fun main(args: Array<String>) = runBlocking {
//            val job = launch {
//                try {
//                    repeat(1000) {
//                        println("job: I'm sleeping $it ...")
//                        delay(500L)
//                    }
//                } finally {
//                    withContext(NonCancellable) {
//                        println("job: I'm run finally")
//                        delay(1000L)
//                        println("job: And I've just delayed for 1 sec because I'm non-cancellable")
//                    }
//                }
//            }
//            delay(1300)
//            println("main: I'm tired of waiting")
//            job.cancelAndJoin()
//            println("main: Now I can quite")
//
//        }

//        @JvmStatic
//        fun main(args: Array<String>) = runBlocking {
//            var job =
//                launch { println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}") }
//            var jobUnconfig = launch(Dispatchers.Unconfined) {
//                println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
//            }
//            var jobDefault = launch(Dispatchers.Default) { // 将会获取默认调度器
//                println("Default               : I'm working in thread ${Thread.currentThread().name}")
//            }
//            var ownerJon = launch(newSingleThreadContext("MyOwnerThread")) {
//                println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
//            }
//            ownerJon.start();
//            ownerJon.join()
//        }

        /**
         * 超时
         */
//        @JvmStatic
//        fun main(args: Array<String>) = runBlocking {
//            withTimeout(1300L){
//                repeat(100){
//                    println("I'm sleeping $it ...")
//                    delay(500L)
//                }
//            }
//        }

        /**
         * 超时 返回 null
         */
//        @JvmStatic
//        fun main(args: Array<String>) = runBlocking {
//            val result = withTimeoutOrNull(1300L) {
//                repeat(1000) { i ->
//                    println("I'm sleeping $i ...")
//                    delay(500L)
//                }
//                "Done"
//            }
//            println("Result is $result")
//        }

        /**
         * 组合挂起函数
         */
//        @JvmStatic
//        fun main(args: Array<String>) = runBlocking {
//            val time = measureTimeMillis {
//                val one = doSomethingUsefulOne()
//                val two = doSomethingUsefulTwo()
//                println("answer is = ${one + two} ")
//            }
//
//            println("calculate complete time is $time")
//        }

        /**
         * 懒启动挂起函数
         */
//        @JvmStatic
//        fun main(args: Array<String>) = runBlocking {
//            val time = measureTimeMillis {
//                val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
//                val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
//                one.start() // 启动第一个
//                two.start() // 启动第二个
//                println("The answer is ${one.await() + two.await()}")
//            }
//            //使用异步方法执行计算时间节约一半
//            println("Completed in $time ms")
//        }

        /**
         * 使用async 的结构化并发
         */
//        @JvmStatic
//        fun main(args: Array<String>) = runBlocking {
//            val time = measureTimeMillis {
//                println("The answer is ${concurrentSum()}")
//            }
//            println("Completed in $time ms")
//        }

//        private suspend fun concurrentSum(): Int = coroutineScope {
//            val one = async { doSomethingUsefulOne() }
//            val two = async { doSomethingUsefulTwo() }
//            one.await() + two.await()
//        }
//
//        private suspend fun doSomethingUsefulOne(): Int {
//            delay(1000)
//            return 13
//        }
//
//        private suspend fun doSomethingUsefulTwo(): Int {
//            delay(1000)
//            return 29
//        }

        /**
         * 取消始终通过协程的层次结构来进行传递
         */
//        @JvmStatic
//        fun main() = runBlocking {
//            failedConcurrentSum()
//        }

        @JvmStatic
        fun main(args: Array<String>) = runBlocking<Unit> {
            failedConcurrentSum()
        }

        private suspend fun failedConcurrentSum(): Int = coroutineScope {
            val one = async {
                try {
                    delay(Long.MAX_VALUE)
                    42
                } finally {
                    println("First child was cancelled")
                }
            }
            val two = async<Int> { throw  ArithmeticException() }
            one.await() + two.await()
        }

        private fun sumNumber(vararg numbers: Double): Double {
            var result = 0.0
            for (number in numbers) {
                result += number
            }
            return result
        }
    }


}