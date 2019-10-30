package com.itex.ceva.network;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseAndStringRequestListener;
import com.itex.ceva.BuildConfig;

import org.json.JSONObject;

import okhttp3.OkHttpClient;

/**
 * Package com.itex.ceva.network in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
public class Networking {

    private static final String TAG = Networking.class.getSimpleName();
    private final static String API_ENDPOINT = BuildConfig.BASE_URL;

    public static void getData(String endpoint,
                               OkHttpResponseAndStringRequestListener listener){
        //noinspection MalformedFormatString
        Log.d(TAG, String.format("Getting data from", API_ENDPOINT + endpoint));
        AndroidNetworking.get(API_ENDPOINT + endpoint)
                .setTag(endpoint)
                .setPriority(Priority.MEDIUM)
                .addHeaders("Content-Type", "text/plain")
                .build()
                .getAsOkHttpResponseAndString(listener);
    }

    public static void postData(String endpoint,
                                OkHttpResponseAndStringRequestListener listener) {
        Log.d("posting shit", API_ENDPOINT + endpoint);
        AndroidNetworking.post(API_ENDPOINT + endpoint)
                .setTag(endpoint)
                .setPriority(Priority.MEDIUM)
                .addHeaders("Content-Type", "text/plain")
                .build()
                .getAsOkHttpResponseAndString(listener);

    }
}
