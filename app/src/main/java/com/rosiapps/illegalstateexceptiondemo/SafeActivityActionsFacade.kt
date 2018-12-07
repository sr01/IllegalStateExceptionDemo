package com.rosiapps.illegalstateexceptiondemo

import android.os.HandlerThread
import android.util.Log

class SafeActivityActionsFacade {

    private val thread = HandlerThread("SafeActivityActionsThread")
    private val handler: PauseableHandler

    fun pause() {
        Log.d(TAG, "pause")
        handler.paused = true
    }

    fun resume() {
        Log.d(TAG, "resume")
        handler.paused = false
    }

    fun quit() {
        Log.d(TAG, "quit")
        thread.quit()
    }

    fun post(runnable: () -> Unit) {
        Log.d(TAG, "post")
        handler.post(runnable)
    }

    fun postDelayed(delayMillis: Long, runnable: () -> Unit) {
        Log.d(TAG, "postDelayed")
        handler.postDelayed(runnable, delayMillis)
    }

    init {
        thread.start()
        handler = PauseableHandler(thread.looper)
    }

    companion object {
        private const val TAG = "SR.SafeActivityActionsF"
    }
}