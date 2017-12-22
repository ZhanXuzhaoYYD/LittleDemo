package com.zxz.littledemo;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import com.blankj.utilcode.util.ConvertUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/19 18:37
 *     desc   :
 *     version:
 * </pre>
 */

public class PcmPlayer {
    private static final int SAMPLE_RATE_IN_HZ = 16000;
    private AudioTrack mTrack;
    private Future<?> mFuture;

    public PcmPlayer() {
    }

    public void play(final File file) {
        mFuture = ThreadPool.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    int minSize = AudioTrack.getMinBufferSize(
                            SAMPLE_RATE_IN_HZ,
                            AudioFormat.CHANNEL_OUT_MONO,
                            AudioFormat.ENCODING_PCM_16BIT);
                    mTrack = new AudioTrack(
                            AudioManager.STREAM_MUSIC,
                            SAMPLE_RATE_IN_HZ,
                            AudioFormat.CHANNEL_OUT_MONO,
                            AudioFormat.ENCODING_PCM_16BIT,
                            minSize,
                            AudioTrack.MODE_STREAM);
                    mTrack.play();
                    byte[] bytes = ConvertUtils.inputStream2Bytes(new FileInputStream(file));
                    mTrack.write(bytes, 0, bytes.length);
                    mTrack.pause();
                    mTrack.flush();
                    mTrack.release();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void stop() {
        if (mTrack != null && mTrack.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
            mTrack.pause();
            mTrack.flush();
            mTrack.release();
        }
        mFuture.cancel(true);
    }
}
