package com.zxz.littledemo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.util.Locale;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/22 11:12
 *     desc   :
 *     version:
 * </pre>
 */

public class ScrollText extends View {
    private Paint mPaint;
    private float mTextCenterX;
    private Rect mTextBounds = new Rect();
    private float mTextCenterY;
    private int mTextWidth;
    private int mTextHeight;
    private float mTextYShift;
    private int mViewWidth;
    private int mViewHeight;
    private int mStartNum = 0;
    private int mTargetNum = 0;
    private float mMovingSpace;
    private float mViewHeightTextHeightRatio = 2.0f;
    private boolean mIsDrawing;
    private int mRotateDuration = 3000;
    private ObjectAnimator mRotateAnim;
    private final int sCorrectShift = dp2px(1.5f);

    public ScrollText(Context context) {
        this(context, null);
    }

    public ScrollText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(72);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mTextHeight = measureTextHeight();
        mTextWidth = measureTextWidth();
    }

    private Rect measureTextSize() {
        String text = String.valueOf(mStartNum);
        mPaint.getTextBounds(text, 0, text.length(), mTextBounds);
        return mTextBounds;
    }

    private int measureTextWidth() {
        return measureTextSize().width();
    }

    private int measureTextHeight() {
        return measureTextSize().height();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mTextWidth = measureTextWidth();
        mTextHeight = measureTextHeight();
        mViewWidth = measureWidth(widthMeasureSpec, mTextWidth);
        mViewHeight = measureHeight(heightMeasureSpec, mTextHeight);
        setMeasuredDimension(mViewWidth, mViewHeight);
        mTextCenterX = getMeasuredWidth() / 2;
        mTextCenterY = getMeasuredHeight() / 2 + mTextHeight / 2;
        mMovingSpace = mViewHeight + mTextHeight;

//        if (mMovingSpace != 0 && !mIsDrawing) {
//            mIsDrawing = true;
//            startRotateAnim();
//        }
    }

    private int measureWidth(int measureSpec, int textWidth) {
        int mode = MeasureSpec.getMode(measureSpec);
        int val = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = val;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = textWidth;
                break;
        }
        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
        return result + getPaddingLeft() + getPaddingRight();
    }

    private int measureHeight(int measureSpec, int textHeight) {
        int mode = MeasureSpec.getMode(measureSpec);
        int val = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = val;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = (int) (textHeight * mViewHeightTextHeightRatio);
                break;
        }
        result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
        return result + getPaddingTop() + getPaddingBottom();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int direction = mTargetNum > mStartNum ? 1 : -1;
        float y1 = (mTextCenterY + direction * mTextYShift) % mMovingSpace;
        if (y1 < 0) {
            y1 += mMovingSpace;
        }
        if (y1 >= mMovingSpace) {
            y1 -= mMovingSpace;
        }
        float y2 = (mMovingSpace + direction * mTextYShift) % mMovingSpace;
        if (y2 < 0) {
            y2 += mMovingSpace;
        }
        if (y2 >= mMovingSpace) {
            y2 -= mMovingSpace;
        }
        int n1Circle = (int) ((mTextYShift + mMovingSpace * .5f) / mMovingSpace);
        int n1 = mStartNum + n1Circle * 2;
        int n2Circle = (int) (mTextYShift / mMovingSpace);
        int n2 = mStartNum + 1 + n2Circle * 2;
        System.out.println(String.format(Locale.US, "mMovingSpace: %4.1f,\t y1: %4.1f,\t y2: %4.1f,\t shift: %4.1f,\t ", mMovingSpace, y1, y2, mTextYShift));
        System.out.println("dp2px: " + sCorrectShift);
        canvas.drawText(String.valueOf(n1), mTextCenterX, y1 - sCorrectShift, mPaint);
        canvas.drawText(String.valueOf(n2), mTextCenterX, y2 - sCorrectShift, mPaint);
    }

    public float getTextYShift() {
        return mTextYShift;
    }

    public void setTextYShift(float textYShift) {
        mTextYShift = textYShift;
        invalidate();
    }

    private void startRotateAnim() {
        int numDif = Math.abs(mTargetNum - mStartNum);
        if (numDif == 0) {
            return;
        }
        if (mRotateAnim != null && mRotateAnim.isRunning()) {
            mRotateAnim.cancel();
        }
        mRotateAnim = ObjectAnimator.ofFloat(this, "textYShift", 0, mMovingSpace * 0.5f * numDif);
        mRotateAnim.setDuration(mRotateDuration);
        mRotateAnim.setInterpolator(new DecelerateInterpolator());
        mRotateAnim.start();
    }

//    @Override
//    public boolean performClick() {
//        startRotateAnim();
//        return super.performClick();
//    }

    public void setNumber(int targetNum) {
        setNumber(mTargetNum, targetNum);
    }

    public void setNumber(int startNum, int targetNum) {
        mStartNum = startNum;
        mTargetNum = targetNum;
        startRotateAnim();
    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpVal, 
                getResources().getDisplayMetrics());
    }
}
