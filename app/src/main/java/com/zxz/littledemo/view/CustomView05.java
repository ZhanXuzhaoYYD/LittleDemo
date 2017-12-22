package com.zxz.littledemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zxz.littledemo.R;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/21 9:41
 *     desc   :
 *     version:
 * </pre>
 */

public class CustomView05 extends View {
    Paint mPaint = new Paint();
    Camera mCamera = new Camera();
    Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic01);


    private int mLeft = 200;
    private int mTop = 200;
    private int mBitmapWidth;
    private int mBitmapHeight;

    public CustomView05(Context context) {
        this(context, null);
    }

    public CustomView05(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint.setAntiAlias(true);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        mCamera.save();

//        canvas.rotate(-45, mLeft, mTop);
//        canvas.clipRect(new RectF(mLeft, mTop, mLeft + 200, mTop + 300));
//        canvas.scale(2,1);
//        canvas.skew(0, 0.5f);
        int centerX = mLeft + mBitmapWidth / 2;
        int centerY = mTop + mBitmapHeight / 2;
        canvas.translate(centerX, centerY);
        mCamera.rotateX(30);
        mCamera.applyToCanvas(canvas);
        canvas.translate(-centerX, -centerY);
        mCamera.restore();

        canvas.drawBitmap(mBitmap, mLeft, mTop, mPaint);
        canvas.restore();

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
    }


}
