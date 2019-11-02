package com.itex.ceva.di;

import com.itex.ceva.BaseApplication;
import com.itex.ceva.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Package com.itex.ceva.di in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-11-02
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(BaseApplication application);
    void inject(MainActivity mainActivity);
}
