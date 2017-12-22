package com.zxz.littledemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zxz.littledemo.R;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/20 18:12
 *     desc   :
 *     version:
 * </pre>
 */

public class CustomView02 extends View {

    private Paint mPaint;
    private Bitmap mBitmap1;
    private Bitmap mBitmap2;

    public CustomView02(Context context) {
        this(context, null);
    }

    public CustomView02(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();

        Shader linearShader = new LinearGradient(
                0, 0,
                200, 0,
                Color.RED,
                Color.BLUE,
                Shader.TileMode.CLAMP);

        mBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.scenes);
        mBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_home_page_security);
        Shader bs1 = new BitmapShader(mBitmap1, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        Shader bs2 = new BitmapShader(mBitmap2, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        Shader cs = new ComposeShader(bs1, bs2, PorterDuff.Mode.SRC_OVER);

        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);

        mPaint.setShader(bs1);
        mPaint.setMaskFilter(blurMaskFilter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), mPaint);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 200, mPaint);
        canvas.drawBitmap(mBitmap1, 100, 100, mPaint);
    }
}
