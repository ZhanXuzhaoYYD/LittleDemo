package com.zxz.littledemo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/21 15:10
 *     desc   :
 *     version:
 * </pre>
 */

public class CustomView06ProgressArc extends View {
    private int mProgress;
    private Paint mPaint = new Paint();
    private RectF mRectF = new RectF();
    private int mPadding = 50;

    public CustomView06ProgressArc(Context context) {
        this(context, null);
    }

    public CustomView06ProgressArc(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mPaint.setAntiAlias(true);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        int[] colors = {
                Color.parseColor("#2196F2"),
                Color.parseColor("#9C27B0"),
                Color.parseColor("#2196F2"),
        };
        Shader shader = new SweepGradient(300, 300, colors, null);
        mPaint.setShader(shader);
        mPaint.setStrokeWidth(30);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mRectF.left = mPadding;
        mRectF.top = mPadding;
        mRectF.right = parentWidth - mPadding;
        mRectF.bottom = parentHeight - mPadding;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(mRectF, 135, 270 * mProgress / 100, false, mPaint);
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public void setProgressWithAnim(int progress) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, "progress", 0, progress);
        objectAnimator.setInterpolator(new OvershootInterpolator());
        objectAnimator.setDuration(500);
        objectAnimator.start();
    }
}
