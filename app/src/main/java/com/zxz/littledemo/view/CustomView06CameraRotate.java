package com.zxz.littledemo.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.zxz.littledemo.R;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/21 17:18
 *     desc   :
 *     version:
 * </pre>
 */

public class CustomView06CameraRotate extends View {
    private Paint mPaint = new Paint();
    private RectF mRectF = new RectF();
    private int mPadding = 180;
    private Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pandeng);
    private Rect mClipRect = new Rect();
    private Camera mCamera = new Camera();
    private int mParentWidth;
    private int mParentHeight;
    private Bitmap mScaledBitmap;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private int mRotateY = 0;
    private int mFixedRotateY = 0;
    private int mRotateZ = 0;

    public CustomView06CameraRotate(Context context) {
        this(context, null);
    }

    public CustomView06CameraRotate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mPaint.setAntiAlias(true);
        // set camera location
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        mCamera.setLocation(0,0,newZ);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mParentWidth = MeasureSpec.getSize(widthMeasureSpec);
        mParentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(mParentWidth, mParentHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mRectF.left = mPadding;
        mRectF.top = mPadding;
        mRectF.right = mParentWidth - mPadding;
        mRectF.bottom = mParentHeight - mPadding;

        if (mParentHeight != 0) {
            int dstWidth = mParentWidth / 2;
            int dstHeight = mParentHeight / 2;
            int min = Math.min(dstHeight, dstWidth);
            mScaledBitmap = Bitmap.createScaledBitmap(mBitmap, min, min, false);
            mBitmapWidth = mScaledBitmap.getWidth();
            mBitmapHeight = mScaledBitmap.getHeight();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int bitmapX = centerX - mBitmapWidth / 2;
        int bitmapY = centerY - mBitmapHeight / 2;

        // draw rotate part
        canvas.save();
        mCamera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-mRotateZ);
        mCamera.rotateY(mRotateY);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(0, -centerY, centerX, centerY);
        canvas.rotate(mRotateZ);
        canvas.translate(-centerX, -centerY);
        mCamera.restore();
        canvas.drawBitmap(mScaledBitmap, bitmapX, bitmapY, mPaint);
        canvas.restore();

        // draw flat part
        canvas.save();
        mCamera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-mRotateZ);
        mCamera.rotateY(mFixedRotateY);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(-centerX, -centerY, 0, centerY);
        canvas.rotate(mRotateZ);
        canvas.translate(-centerX, -centerY);
        mCamera.restore();
        canvas.drawBitmap(mScaledBitmap, bitmapX, bitmapY, mPaint);
        canvas.restore();
    }

    private void butterFly(Canvas canvas) {
        // draw left
        canvas.save();

        canvas.translate(mParentWidth / 2, mParentWidth / 2);
        mCamera.save();
        mCamera.rotateY(mRotateY);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.translate(-mParentWidth / 2, -mParentWidth / 2);

        mClipRect.left = mPadding;
        mClipRect.top = mPadding;
        mClipRect.right = mParentWidth / 2;
        mClipRect.bottom = mParentHeight - mPadding;
        canvas.clipRect(mClipRect);
        canvas.drawBitmap(mScaledBitmap, mPadding, mPadding, mPaint);
        canvas.restore();

        // draw right
        canvas.save();

        canvas.translate(mParentWidth / 2, mParentWidth / 2);
        mCamera.save();
        mCamera.rotateY(-mRotateY);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.translate(-mParentWidth / 2, -mParentWidth / 2);

        mClipRect.left = mParentWidth / 2;
        mClipRect.right = mParentWidth - mPadding;
        mClipRect.top = mPadding;
        mClipRect.bottom = mParentHeight - mPadding;
        canvas.clipRect(mClipRect);
        canvas.drawBitmap(mScaledBitmap, mPadding, mPadding, mPaint);
        canvas.restore();
    }

    public int getRotateY() {
        return mRotateY;
    }

    public void setRotateY(int rotateY) {
        mRotateY = rotateY;
        invalidate();
    }

    public int getRotateZ() {
        return mRotateZ;
    }

    public void setRotateZ(int rotateZ) {
        mRotateZ = rotateZ;
        invalidate();
    }

    public int getFixedRotateY() {
        return mFixedRotateY;
    }

    public void setFixedRotateY(int fixedRotateY) {
        mFixedRotateY = fixedRotateY;
        invalidate();
    }

    public void setRotateZWithAnim(int rotateZ) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, "rotateZ", 0, rotateZ);
        objectAnimator.setDuration(20000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.start();
    }
}
