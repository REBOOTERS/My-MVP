package com.engineer.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 06-23-2019
 */


fun main() {
    GlobalScope.launch {
        delay(1000)
        println("B")
    }
    println("A")
    runBlocking {
        delay(2000)
    }
    amazing()
}

fun amazing() = runBlocking {
    GlobalScope.launch {
        delay(1000)
        println("2")
    }
    println("1")
    delay(2000)
}

