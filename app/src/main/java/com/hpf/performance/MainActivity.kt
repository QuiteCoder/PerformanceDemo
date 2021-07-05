package com.hpf.performance

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"

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


    }

    fun goToHookActivity(view: View) {
        val intent = Intent(this, HookActivity::class.java)
        startActivity(intent)
    }
}
