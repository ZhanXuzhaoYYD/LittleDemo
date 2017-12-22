package com.zxz.littledemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zxz.littledemo.R;

/**
 * <pre>
 *     author : ZhanXuzhao
 *     e-mail : zhanxuzhao@yyd.robo.com
 *     time   : 2017/12/20 17:53
 *     desc   :
 *     version:
 * </pre>
 */

public class CustomView01 extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mRect = new RectF(0, 0, 800, 800);
    private RectF mRect1 = new RectF(0, 0, 800, 800);
    private RectF mRect2 = new RectF(100, 100, 300, 300);
    private RectF mRect3 = new RectF(0, 0, 800, 800);
    private RectF mRect4 = new RectF(0, 0, 800, 800);
    private Path mPath = new Path();

    public CustomView01(Context context) {
        super(context);
    }

    public CustomView01(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        mPath.addArc(new RectF(200, 200, 400, 400), -225, 225);
        mPath.arcTo(new RectF(400, 200, 600, 400), -180, 225, false);
        mPath.lineTo(400, 542);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);

        mPaint.setColor(getResources().getColor(R.color.orange_1));
        canvas.drawRect(0, 0, 1000, 1000, mPaint);

        mPaint.setColor(getResources().getColor(R.color.orange_2));
        canvas.drawRect(0, 0, 900, 900, mPaint);

        mPaint.setColor(getResources().getColor(R.color.orange_3));
        canvas.drawRoundRect(mRect, 30, 40, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawArc(mRect1, 0, 150, false, mPaint);

//        mPaint.setStyle(Paint.Style.FILL);
//        mPath.lineTo(100, 100);
//        mPath.arcTo(mRect2, 0, 90, false);
//        mPath.close();
        canvas.drawPath(mPath, mPaint);

        mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size));
        canvas.drawText("12323123", 400,400,mPaint);


//        mPaint.setColor(Color.RED);
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(100, 100, 50, mPaint);
//
//        mPaint.setStrokeCap(Paint.Cap.ROUND);    //
//        canvas.drawLine(100, 100, 300, 100, mPaint);
    }
}
