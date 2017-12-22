package com.zxz.littledemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/21 9:41
 *     desc   :
 *     version:
 * </pre>
 */

public class CustomView03 extends View {
    Paint mPaint = new Paint();
    private Rect mRect;
    private Path mPath;
    private RectF mRectF;
    private int mRectFLeft;
    private int mRectFTop;
    private int mRectFWidth;
    private int mRectFHeight;

    public CustomView03(Context context) {
        this(context, null);
    }

    public CustomView03(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint.setTextSize(50);
        mPaint.setStyle(Paint.Style.STROKE);
        mRect = new Rect();
        mPath = new Path();

        mRectFLeft = 200;
        mRectFTop = 200;
        mRectFWidth = 800;
        mRectFHeight = 800;
        mRectF = new RectF(mRectFLeft, mRectFTop, mRectFLeft + mRectFWidth, mRectFTop + mRectFHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.addArc(mRectF, -180, 180);
        canvas.drawPath(mPath, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setTextAlign(Paint.Align.CENTER);
        String text = "James Robert Hello world";

        mPaint.getTextBounds(text, 0, text.length(), mRect);
        float textWidth = mPaint.measureText(text);
        canvas.drawTextOnPath(text, mPath, (float) ((mRectFWidth* Math.PI / 2 - textWidth) / 2), 0, mPaint);
        canvas.drawText(text, 0, 300, mPaint);


    }
}
