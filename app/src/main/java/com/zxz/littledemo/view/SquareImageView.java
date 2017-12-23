package com.zxz.littledemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.zxz.littledemo.R;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/22 17:25
 *     desc   :
 *     version:
 * </pre>
 */

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint mPaint;
    private Rect mRect;
    private Bitmap mBitmap;

    public SquareImageView(Context context) {
        this(context, null);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mRect = new Rect();
        mRect.left = 0;
        mRect.top = 0;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic02);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int min = Math.min(measuredWidth, measuredHeight);
        setMeasuredDimension(min, min);
        mRect.right = min;
        mRect.bottom = min;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
