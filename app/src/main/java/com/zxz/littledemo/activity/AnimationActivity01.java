package com.zxz.littledemo.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;

import com.zxz.littledemo.R;
import com.zxz.littledemo.view.CustomView06CameraRotate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity01 extends Activity {

    public static final int DURATION = 500;
    public static final int STEP = 45;
    private int mRotateY = 45;
    @BindView(R.id.progress_view)
    CustomView06CameraRotate mCustomView;
    private AnimatorSet mSet1;
    private AnimatorSet mSet2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation01);
        ButterKnife.bind(this);

//        animate();

    }

    @OnClick(R.id.progress_view)
    public void onclick() {
        animate();
    }

    private void animate() {
        ObjectAnimator animator1 = ObjectAnimator.ofInt(mCustomView, "rotateY", 0, -mRotateY);
        animator1.setDuration(DURATION);

        ObjectAnimator animator2 = ObjectAnimator.ofInt(mCustomView, "rotateZ", 0, 360);
        animator2.setDuration(DURATION * 3);

        ObjectAnimator animator3 = ObjectAnimator.ofInt(mCustomView, "fixedRotateY", 0, mRotateY);
        animator3.setDuration(DURATION);
//        animator3.start();

        ObjectAnimator animator4 = ObjectAnimator.ofInt(mCustomView, "rotateY", -mRotateY, 0);
        animator4.setDuration(DURATION);

        ObjectAnimator animator5 = ObjectAnimator.ofInt(mCustomView, "fixedRotateY", mRotateY, 0);
        animator5.setDuration(DURATION);

        mSet1 = new AnimatorSet();
        mSet1.playSequentially(animator1, animator2, animator3);
        mSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mSet2.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        mSet1.start();

        mSet2 = new AnimatorSet();
        mSet2.playTogether(animator4, animator5);
        mSet2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mRotateY += STEP;
                animate();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

//    @OnClick({R.id.btn})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn:
////                startActivity(new Intent(AnimationActivity01.this, AnimationActivity02.class));
////                mCustomView.setRotateZWithAnim(360);
//                animate();
//                break;
//        }
//    }
}
