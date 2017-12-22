package com.zxz.littledemo;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/4 18:56
 *     desc   :
 *     version:
 * </pre>
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
