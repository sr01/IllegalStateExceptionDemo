package com.rosiapps.illegalstateexceptiondemo

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var safeActions: SafeActivityActionsFacade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startALongRunningAsyncOperationAtTheEndShowFragment()
        }

        safeActions = SafeActivityActionsFacade()
    }

    override fun onResume() {
        super.onResume()
        safeActions.resume()
    }

    override fun onPause() {
        super.onPause()
        safeActions.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        safeActions.quit()
    }

    private fun startALongRunningAsyncOperationAtTheEndShowFragment() {
        safeActions.postDelayed(5000) {
            runOnUiThread {
                showFragment()
            }
        }
    }

    private fun showFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TestFragment(), "TestFragment")
            .commit()
    }
}
