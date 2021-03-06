package com.zxz.littledemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zxz.littledemo.R;
import com.zxz.littledemo.view.ScrollText;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private MainActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = MainActivity.this;

        launchNext();
    }

    private void launchNext() {
        startActivity(new Intent(this, ScrollTextActivity.class));
        finish();
    }

    @OnClick(R.id.image)
    public void onClick() {
        launchNext();
    }


}
