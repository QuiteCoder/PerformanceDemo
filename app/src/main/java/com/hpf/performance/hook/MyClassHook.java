package com.hpf.performance.hook;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;

public class MyClassHook extends XC_MethodHook {
    private static final String TAG = "MyClassHook";
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        Log.d(TAG,"afterHookedMethod");
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        Log.d(TAG,"beforeHookedMethod");
    }
}
