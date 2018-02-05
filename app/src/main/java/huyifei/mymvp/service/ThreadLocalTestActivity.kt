package huyifei.mymvp.service

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import huyifei.mymvp.R
import kotlinx.android.synthetic.main.activity_thread_local_test.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


abstract class ThreadLocalTestActivity : AppCompatActivity() {

    private val mThreadLocal = ThreadLocal<String>()
    internal abstract var mExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_local_test)
        mThreadLocal.set("MainThread")

        mExecutor = Executors.newFixedThreadPool(2)


        Log.e(TAG, "main: " + mThreadLocal.get())

        run1.setOnClickListener { mExecutor.execute(Runnable1()) }

        run2.setOnClickListener { mExecutor.execute(Runnable2()) }
    }


    internal inner class Runnable1 : Runnable {

        override fun run() {
            mThreadLocal.set("Runnable-1")
            Log.e(TAG, "run1: " + mThreadLocal.get())
        }
    }

    internal inner class Runnable2 : Runnable {

        override fun run() {
            mThreadLocal.set("Runnable-2")
            Log.e(TAG, "run2: " + mThreadLocal.get())
        }
    }

    companion object {
        private val TAG = "ThreadLocalTestActivity"
    }
}
