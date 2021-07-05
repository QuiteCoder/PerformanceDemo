package com.hpf.performance;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HookActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        ImageView iv = findViewById(R.id.iv_memoryleak2);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
        iv.setImageBitmap(bitmap);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {


                }
                System.out.println("Hook activity run");
            }
        });

        thread.run();
    }
}
