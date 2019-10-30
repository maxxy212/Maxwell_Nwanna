package com.itex.ceva;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

/**
 * Package com.itex.ceva in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(this);
    }
}
