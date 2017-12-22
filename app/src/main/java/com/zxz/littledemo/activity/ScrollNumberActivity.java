package com.zxz.littledemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zxz.littledemo.view.MultiScrollNumber;
import com.zxz.littledemo.R;
import com.zxz.littledemo.view.ScrollNumber;

public class ScrollNumberActivity extends AppCompatActivity {

    private ScrollNumber mScrollNumber;
    private int mNumber;
    private MultiScrollNumber mMultiScrollNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_number);

        mScrollNumber = (ScrollNumber) findViewById(R.id.scroll_number);
        mScrollNumber.setVelocity(1);
        mScrollNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mScrollNumber.setTargetNumber(++mNumber);
                mScrollNumber.setNumber(mNumber, ++mNumber, 0);
            }
        });

        mMultiScrollNumber = (MultiScrollNumber) findViewById(R.id.multi_scroll_number);
        mMultiScrollNumber.setNumber(10);
        mMultiScrollNumber.setScrollVelocity(1);
        mMultiScrollNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMultiScrollNumber.setNumber(10,11);
            }
        });

    }
}
