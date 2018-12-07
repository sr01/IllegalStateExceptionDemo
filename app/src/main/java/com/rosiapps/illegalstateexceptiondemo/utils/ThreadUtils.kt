package com.rosiapps.illegalstateexceptiondemo.utils

fun threadInfo(): String = with(Thread.currentThread()) { "[$name:$id]" }
