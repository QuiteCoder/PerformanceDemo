package com.hpf.performance.hook;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;

public class ThreadMethodHook extends XC_MethodHook {
    private static final String TAG = "ThreadMethodHook";

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        Thread t = (Thread) param.thisObject;
        //t = thread:Thread[Thread-2,5,main]，这个mian是线程组名字，而不是main线程。
        //每一个ThreadGroup都可以包含一组的子线程和一组子线程组，在一个进程中线程组是以树形的方式存在，通常情况下根线程组是system线程组。
        //system线程组下是main线程组，默认情况下第一级应用自己的线程组是通过main线程组创建出来的。

        Log.i(TAG, "thread:" + t + ", started..");
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        Thread t = (Thread) param.thisObject;
        Log.i(TAG, "thread:" + t + ", exit..");
    }
}
