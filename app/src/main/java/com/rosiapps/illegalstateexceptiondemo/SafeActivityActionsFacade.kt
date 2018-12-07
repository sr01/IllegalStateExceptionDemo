package com.rosiapps.illegalstateexceptiondemo

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.os.HandlerThread
import android.util.Log

class SafeActivityActionsFacade  : LifecycleObserver{

    private val thread = HandlerThread("SafeActivityActionsThread")
    private val handler: PauseableHandler

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        Log.d(TAG, "pause")
        handler.paused = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        Log.d(TAG, "resume")
        handler.paused = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
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