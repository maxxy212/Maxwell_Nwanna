package com.itex.ceva;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.itex.ceva.di.AppComponent;
import com.itex.ceva.di.AppModule;
import com.itex.ceva.di.DaggerAppComponent;

/**
 * Package com.itex.ceva in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
public class BaseApplication extends Application {

    AppComponent appComponent;

    private static final String TAG = BaseApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
