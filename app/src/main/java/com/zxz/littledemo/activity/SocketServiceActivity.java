package com.zxz.littledemo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zxz.littledemo.R;
import com.zxz.littledemo.service.SocketService;

public class SocketServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_service);

        bindService(new Intent(this, SocketService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                SocketService.MyBinder myBinder = (SocketService.MyBinder) iBinder;
                SocketService service = myBinder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, 0);
    }
}
