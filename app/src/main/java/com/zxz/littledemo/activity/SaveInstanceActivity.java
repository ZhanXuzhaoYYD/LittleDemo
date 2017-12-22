package com.zxz.littledemo.activity;

import android.app.ProgressDialog;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zxz.littledemo.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaveInstanceActivity extends AppCompatActivity {
    public static final String KEY_TIME = "time";
    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;
    @BindView(R.id.text)
    TextView mTextView;

    private int mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_instance);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            mTime = savedInstanceState.getInt(KEY_TIME);
        }
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      mSeekBar.setProgress(mTime);
                                      mTextView.setText(String.valueOf(mTime++));
                                  }
                              }
                );
            }
        }, 0, 1000);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_TIME, mTime);
        super.onSaveInstanceState(outState);
    }
}
