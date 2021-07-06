package com.hpf.performance;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.hpf.performance.hook.ImageHook;
import com.hpf.performance.hook.ThreadMethodHook;

import de.robv.android.xposed.DexposedBridge;
import de.robv.android.xposed.XC_MethodHook;

public class PerformanceApp extends Application {

    private static final String TAG = "PerformanceApp";
    private static PerformanceApp mApplication;

    public static Application getApplication() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        EpicHook();
    }

    /**
     * 能hook应用内和framework的类，静态和非静态方法都可以
     * 华为mate10 pro测试失败；小米10 Pro测试通过
     * */
    private void EpicHook() {
    /*DexposedBridge.hookAllConstructors(Thread.class, new XC_MethodHook() {
        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            super.afterHookedMethod(param);
            Thread thread = (Thread) param.thisObject;
            Class<?> clazz = thread.getClass();
            if (clazz != Thread.class) {
                Log.d(TAG, "found class extend Thread:" + clazz);
                DexposedBridge.findAndHookMethod(clazz, "run", new ThreadMethodHook());
            }
            Log.d(TAG, "Thread: " + thread.getName() + " class:" + thread.getClass() +  " is created.");
        }
    });*/
        DexposedBridge.findAndHookMethod(Thread.class, "run", new ThreadMethodHook());

        /*DexposedBridge.hookAllConstructors(ImageView.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                //DexposedBridge.findAndHookMethod(ImageView.class, "setImageBitmap", Bitmap.class, new ImageHook());
            }
        });*/
        DexposedBridge.findAndHookMethod(ImageView.class, "setImageBitmap", Bitmap.class, new ImageHook());
    }
}
