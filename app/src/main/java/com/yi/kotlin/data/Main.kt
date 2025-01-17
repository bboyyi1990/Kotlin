package com.yi.kotlin.data

/**
 * create by yi on 2023/3/10
 */
fun main(args: Array<String>) {
    //26 Factors in an array
    val arrTwo = arrayOf(75, 325, 800, 375, 575, 300, 25, 925, 875)
    val _MAX = arrTwo.max()
    for (i in (2.._MAX)) {
        val filterArray = arrTwo.filter { it % i == 0 }
        if (filterArray.size == arrTwo.size) println("RESULT = $i")
    }
    //27 Palindrome
    val str = "formaxamfor"
    val array = arrayListOf<String>()
    str.forEachIndexed { index, c ->
        if (index == 0 || index == str.lastIndex)
        else {
            for (i in index.minus(1) downTo 0) {
                var left = str.substring(i, index)
                var right = str.substring(index.plus(1), index.plus(index - i + 1))
                if (left.reversed() != right) break
                else array.add(left + c + right)
            }
        }
    }
    var minLength = Int.MAX_VALUE
    array.forEach { if (it.length < minLength) minLength = it.length }
    val result = array.filter { it.length == minLength }
    println("RESULT = $array and the smallest is $result")
}

suspend fun currentThreadIs() =
    "| thread is ${Thread.currentThread().name} time is ${System.currentTimeMillis() / 1000}"