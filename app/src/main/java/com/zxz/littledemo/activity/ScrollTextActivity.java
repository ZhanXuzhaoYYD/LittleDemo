package com.zxz.littledemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zxz.littledemo.R;

import butterknife.ButterKnife;

public class ScrollTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_text);
        ButterKnife.bind(this);


    }
}
