package com.rosiapps.illegalstateexceptiondemo

import android.os.Bundle
import android.os.HandlerThread
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var isStopped = true
    private val queue = LinkedBlockingQueue<Runnable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startALongRunningAsyncOperationAtTheEndShowFragment()
        }
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onStart() {
        isStopped = false
        Log.d(TAG, "onStart")
        super.onStart()

        if(queue.size > 0){
            Log.d(TAG, "onStart, queue size: ${queue.size}")
            while (queue.size > 0){
                queue.poll()?.run()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Log.d(TAG, "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        isStopped = true
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    private fun startALongRunningAsyncOperationAtTheEndShowFragment() {
        executor.schedule({
            runOnUiThread {
                showFragment()
            }
        }, 5, TimeUnit.SECONDS)
    }

    private fun showFragment() {
        Log.d(TAG, "showFragment, isStopped: $isStopped, isDestroyed: $isDestroyed")

        if (isDestroyed) {
            Log.d(TAG, "showFragment, skipped")
            return
        }

        if (isStopped) {
            queue.add(Runnable {
                showFragment()
            })
            Log.d(TAG, "showFragment, queued")
            return
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TestFragment(), "TestFragment")
            .commit()
    }

    companion object {
        private const val TAG = "SR.MainActivity"
    }
}
