package com.hpf.performance

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.Choreographer
import android.view.Choreographer.FrameCallback
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.hpf.performance.utils.LogUtils


class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"
    private var mStartFrameTime :Long = 0
    private var mStartFChoreographerrameTime :Long = 0
    private val MONITOR_INTERVAL = 160L //单次计算FPS使用160毫秒
    private val MAX_INTERVAL = 1000L
    private val MONITOR_INTERVAL_NANOS :Long = MONITOR_INTERVAL * 1000L * 1000L
    private var mFrameCount :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.iv_memoryleak)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.images)
        imageView.setImageBitmap(bitmap)

        Thread{
            var i = 0
            while (true) {
                System.out.println("i = ${i}")
                Thread.sleep(100)
                i++
                if (i > 10) break
            }
        }.start()
        System.out.println("onCreate end")

    }

    private var mMyFrameCallback :FrameCallback = object :FrameCallback {
        override fun doFrame(frameTimeNanos: Long) {
            val interval: Long = (frameTimeNanos - mStartFrameTime) / 1000 / 1000
            mStartFrameTime = frameTimeNanos
            LogUtils.i("fps : ${1000 / interval}")
            Choreographer.getInstance().postFrameCallback(this)
        }
    }

    private fun getFps() {
        Choreographer.getInstance().postFrameCallback(mMyFrameCallback)
    }

    private fun removeFpsCallback() {
        Choreographer.getInstance().removeFrameCallback(mMyFrameCallback)
    }

    fun goToHookActivity(view: View) {
        val intent = Intent(this, HookActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        getFps()
    }

    override fun onStop() {
        super.onStop()
        removeFpsCallback()
    }


}
