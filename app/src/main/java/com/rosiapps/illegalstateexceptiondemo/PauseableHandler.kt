package com.rosiapps.illegalstateexceptiondemo

import android.os.Handler
import android.os.Looper
import android.os.Message
import java.util.concurrent.LinkedBlockingQueue

class PauseableHandler(looper: Looper) : Handler(looper) {

    private val queue = LinkedBlockingQueue<Message>()

    var paused = false
        set(value) {
            field = value
            if (!value) {
                while (queue.size > 0) {
                    sendMessage(queue.poll())
                }
            }
        }

    override fun dispatchMessage(msg: Message?) {
        msg?.let {
            when {
                paused -> queue.add(it.copy())
                else -> super.dispatchMessage(msg)
            }
        }
    }

    override fun handleMessage(msg: Message?) {
        msg?.let {
            when {
                paused -> queue.add(it.copy())
                else -> it.callback?.run()
            }
        }
    }
}

private fun Message.copy(): Message? = Message.obtain(this)
