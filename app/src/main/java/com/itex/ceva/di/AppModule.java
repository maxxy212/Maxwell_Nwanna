package com.itex.ceva.di;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.itex.ceva.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Package com.itex.ceva.di in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-11-02
 */
@Module
public class AppModule {

    private final BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideAppContext(){
        return application;
    }

    @Provides
    Resources getResources(Context context){
        return context.getResources();
    }

    //Adding toast irrespective of my existing toast util
    @Provides
    Toast toast(){
       return Toast.makeText(application.getApplicationContext(), "Finished", Toast.LENGTH_SHORT);
    }
}
