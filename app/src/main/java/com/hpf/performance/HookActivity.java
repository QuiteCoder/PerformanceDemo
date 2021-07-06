package com.hpf.performance;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hpf.performance.hook.ImageHook;
import com.hpf.performance.hook.MyClass;
import com.hpf.performance.hook.MyClass2;
import com.hpf.performance.hook.MyClassHook;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

import de.robv.android.xposed.DexposedBridge;
import de.robv.android.xposed.XC_MethodHook;
import lab.galaxy.yahfa.HookMain;

public class HookActivity extends AppCompatActivity {
    boolean beforeCalled = false;
    AtomicBoolean mb = new AtomicBoolean(true);
    boolean afterCalled = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        Log.i("hook", "aaaaaaa");
        ImageView iv = findViewById(R.id.iv_memoryleak2);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
        iv.setImageBitmap(bitmap);

    }

    /**
     * 只能hook系统的静态方法。
     * 小米10PRO测试失败，闪退；华为mate10 pro测试通过
     *
     * */
    private void yahfaHook() {
        try {
            //取出系统方法
            Class logClass = Log.class;
            Method target = logClass.getDeclaredMethod("i", String.class, String.class);
            //取出创建的i方法
            Class mclass = HookActivity.this.getClass();//新创的“i”方法的类。这里我是放到HookActivity中的
            Method hook = mclass.getDeclaredMethod("i", String.class, String.class);


            //hook应用内的类失败
            //Class myClassClass = MyClass.class;
            //Method target = myClassClass.getDeclaredMethod("noStaticMethod1");
            //Method target = myClassClass.getDeclaredMethod("i",String.class,String.class);

            //Class mclass = MyClass2.class;
            //Method hook = mclass.getDeclaredMethod("noStaticMethod1");
            //Method hook = mclass.getDeclaredMethod("i",String.class, String.class);

            HookMain.hook(target, hook);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean predicate() {
        return beforeCalled && afterCalled;
    }


    public void checkHook(View view) {
        System.out.println("checkHook");
        Log.i("hook", "after hook message");
    }

    public void Hook(View view) {
        yahfaHook();
    }

    public static int i(String s1,String s2){
        System.out.println("hook successful！");
        return 0;
    }

}
