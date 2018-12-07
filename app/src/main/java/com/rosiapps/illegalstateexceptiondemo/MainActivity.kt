package com.rosiapps.illegalstateexceptiondemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val executor = Executors.newSingleThreadScheduledExecutor()

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
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
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
        Log.d(TAG, "showFragment")

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TestFragment(), "TestFragment")
            .commit()
    }

    companion object {
        private const val TAG = "SR.MainActivity"
    }
}
