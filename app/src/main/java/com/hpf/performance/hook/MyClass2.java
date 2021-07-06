package com.hpf.performance.hook;

import android.graphics.Bitmap;
import android.util.Log;

public class MyClass2 {
    private static String TAG = "MyClass2";

    public static void staticMethod1(String s) {
        Log.d(TAG,"staticMethod1");
    }

    public void noStaticMethod1() {
        Log.d(TAG,"noStaticMethod1");
    }

    public void setImageBitmap(Bitmap bitmap) {
        Log.d(TAG,"setImageBitmap");
    }

    public static int i(String s1, String s2) {
        Log.d(TAG,"staticMethod i");
        return 0;
    }
}
