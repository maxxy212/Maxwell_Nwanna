package com.itex.ceva.network.calls;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseAndStringRequestListener;
import com.itex.ceva.R;
import com.itex.ceva.network.ApiCallHandler;
import com.itex.ceva.network.ApiReader;
import com.itex.ceva.network.Networking;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * Package com.itex.ceva.network.calls in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
public class CevaApiCall {

    private static final String TAG = CevaApiCall.class.getSimpleName();

    public static void getCevaCharacter(Context context, final ApiCallHandler callHandler){
        Networking.getData("", new OkHttpResponseAndStringRequestListener() {
            @Override
            public void onResponse(Response okHttpResponse, String response) {
                final ApiReader apiReader = new ApiReader(okHttpResponse);
                Log.d(TAG, "onResponse: "+apiReader.toString());
                if (apiReader.isSuccess()){
                    callHandler.success(response);
                }else {
                    callHandler.failed(context.getString(R.string.error), context.getString(R.string.no_response));
                }
            }

            @Override
            public void onError(ANError anError) {
                anError.printStackTrace();
                final ApiReader apiReader = new ApiReader(anError);
                Log.d(TAG, "onError: "+anError.getErrorCode());
                if (anError.getErrorCode() != 0){
                    callHandler.failed(context.getString(R.string.network_error), anError.getErrorDetail());
                }else {
                    String error = apiReader.getNetworkErrorMsg(context);
                    callHandler.failed(context.getString(R.string.network_error), error);
                }
            }
        });
    }
}
