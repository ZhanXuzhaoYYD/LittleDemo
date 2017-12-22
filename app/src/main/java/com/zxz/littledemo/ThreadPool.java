package com.zxz.littledemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/19 20:43
 *     desc   :
 *     version:
 * </pre>
 */

public class ThreadPool {

    private final ExecutorService mExecutorService;

    private ThreadPool() {
        mExecutorService = Executors.newCachedThreadPool();
    }

    public static ThreadPool getInstance() {
        return SingletonHolder.sThreadPool;
    }

    private static final class SingletonHolder {
        private static final ThreadPool sThreadPool = new ThreadPool();
    }

    public Future<?> submit(Runnable task) {
        return mExecutorService.submit(task);
    }
}
