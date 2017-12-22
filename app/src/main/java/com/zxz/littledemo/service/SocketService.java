package com.zxz.littledemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.RestrictTo;

import java.io.FileDescriptor;

public class SocketService extends Service {

    private MyBinder mMyBinder = new MyBinder();

    public SocketService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mMyBinder;
    }

    public void doSomething() {
        System.out.println("do something");
    }

    public class MyBinder extends Binder {
        public SocketService getService() {
            return SocketService.this;
        }
    }
}
