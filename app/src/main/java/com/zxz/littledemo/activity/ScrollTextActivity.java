package com.zxz.littledemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zxz.littledemo.R;
import com.zxz.littledemo.view.ScrollText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollTextActivity extends AppCompatActivity {
    @BindView(R.id.scroll_text)
    ScrollText mScrollText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_text);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.scroll_text)
    public void onclick() {
        mScrollText.setNumber(0, 100);
    }
}
