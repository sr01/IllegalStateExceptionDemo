package com.rosiapps.illegalstateexceptiondemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.rosiapps.illegalstateexceptiondemo.utils.threadInfo

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "${threadInfo()} onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        Log.d(TAG, "${threadInfo()} onResume")
        super.onResume()
    }

    override fun onStart() {
        Log.d(TAG, "${threadInfo()} onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d(TAG, "${threadInfo()} onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "${threadInfo()} onStop")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Log.d(TAG, "${threadInfo()} onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        Log.d(TAG, "${threadInfo()} onDestroy")
        super.onDestroy()
    }

    companion object {
        private const val TAG = "SR.BaseActivity"
    }
}